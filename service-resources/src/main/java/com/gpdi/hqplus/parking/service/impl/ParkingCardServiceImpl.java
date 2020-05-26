package com.gpdi.hqplus.parking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.common.constants.BusinessCode;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.entity.UserContext;
import com.gpdi.hqplus.common.entity.UserInfo;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.manager.RedisService;
import com.gpdi.hqplus.common.util.*;
import com.gpdi.hqplus.enterprise.entity.Enterprise;
import com.gpdi.hqplus.enterprise.service.IEnterpriseService;
import com.gpdi.hqplus.parking.constants.ParkingConstants;
import com.gpdi.hqplus.parking.entity.vo.ParkingCardVO;
import com.gpdi.hqplus.parking.service.ParkingCardService;
import com.gpdi.hqplus.resources.constants.ResourceType;
import com.gpdi.hqplus.resources.entity.Book;
import com.gpdi.hqplus.resources.service.impl.BookServiceImpl;
import com.gpdi.hqplus.resources.validate.CheckEmptyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description 停车场月卡相关业务
 * @Author wzr
 * @CreateDate 2019-07-01
 * @Time 15:35
 */
@SuppressWarnings("AlibabaRemoveCommentedCode")
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ParkingCardServiceImpl extends BookServiceImpl implements ParkingCardService {

    @Autowired
    IdGenerator idGenerator;

    @Autowired
    DateUtil dateUtil;

    @Autowired
    RedisService redisService;

    @Autowired
    IEnterpriseService enterpriseService;

    /**
     * 返回所有月卡列表
     * @return
     */
    @Override
    public List<ParkingCardVO> listParkingCardByUser() {

        UserInfo userInfo = UserContext.get();

        List<Book> books = baseMapper.selectList(new QueryWrapper<Book>()
                .lambda()
                .eq(Book::getBusinessCode, BusinessCode.PROPERTY_CAR_PARK)
                .eq(Book::getType, ResourceType.PARKING_CARD_TYPE)
                .eq(Book::getUserId,userInfo.getId())
                .eq(Book::getStatus, Book.STATUS_NORMAL)
                .orderByDesc(Book::getCreateTime)
        );

        if (CollectionUtil.isEmpty(books)){
            log.warn("查询停车场月卡记录为空，userId="+userInfo.getId());
            return CollectionUtil.createArrayList();
        }

        List<ParkingCardVO> parkingCards = CollectionUtil.createArrayList();

        for (Book book : books){
            ParkingCardVO parkingCard = book2parkingCard(book);
            parkingCards.add(parkingCard);
        }
        return parkingCards;
    }

    /**
     * 月卡续费
     *
     * @param bookId 月卡id
     * @param timePeriod 单位为月
     */
    @Override
    public void renewalParkingCard(Long bookId, int timePeriod) {
        ParkingCardVO parkingCard = getParkingCardById(bookId);
        String flowStatus = parkingCard.getFlowStatus();
        //当前记录处于待缴费状态，则改为正常缴费状态
        if (flowStatus.equals(ParkingConstants.PARKING_CARD_STATUS_PASS)) {
            parkingCard.setFlowStatus(ParkingConstants.PARKING_CARD_STATUS_NORMAL);
            flowStatus = parkingCard.getFlowStatus();
        }
        //当前记录处于正常缴费状态，进行续费
        if (flowStatus.equals(ParkingConstants.PARKING_CARD_STATUS_NORMAL)){
            LocalDate expiredTime = parkingCard.getExpireTime();
            //如果月卡过期时间比当前时间还靠前，则认为数据出错
            if (expiredTime.isBefore(LocalDate.now())){
                log.error("月卡过期时间获取错误！id="+bookId);
                throw new ApplicationException(ErrorCode.UNKNOWN_ERROR);
            }
            //往后续费指定月数
            parkingCard.setExpireTime(expiredTime.plusMonths(timePeriod));
            Book book = parkingCard2book(parkingCard);
            int i = baseMapper.updateById(book);
            if (i==0){
                log.error("续费失败");
                throw new ApplicationException(ErrorCode.SERVICE_ERROR, "续费失败，请重试");
            }
        }else {
            log.error("状态异常，无法续费，当前状态为： "+flowStatus);
            throw new ApplicationException(ErrorCode.SERVICE_ERROR,"当前月卡未处于待缴费或正常续费状态中，无法续费");
        }
    }

    /**
     * 根据id查询停车场月卡
     *
     * @param bookId 月卡id
     * @return
     */
    @Override
    public ParkingCardVO getParkingCardById(Long bookId) {
        /*Object result = redisService.get(bookId + "getParkingCardById");
        if (result!=null){
           String data = result.toString();
            log.debug("get from redis-->");
            log.debug("key-->"+bookId + "getParkingCardById");
            log.debug("value-->"+data);
            return JsonUtil.json2Bean(data, ParkingCardVO.class);
        }*/
        Book book = baseMapper.selectById(bookId);
        if (book==null){
            log.error("停车场月卡查询错误！id="+bookId);
            throw new ApplicationException(ErrorCode.UNKNOWN_ERROR);
        }
        ParkingCardVO parkingCardVO = book2parkingCard(book);
        //检测是否过期，过期则更新状态和数据库
        if(parkingCardVO.getExpireTime().isBefore(LocalDate.now())){
            parkingCardVO.setFlowStatus(ParkingConstants.PARKING_CARD_STATUS_OVERDUE);
            baseMapper.updateById(parkingCard2book(parkingCardVO));
        }
        //暂时去掉缓存
        /*redisService.set(bookId + "getParkingCardById", JsonUtil.bean2JsonString(parkingCardVO));
        log.debug("set to redis-->");
        log.debug("key-->"+bookId+"getParkingCardById");
        log.debug("value-->"+JsonUtil.bean2JsonString(parkingCardVO));*/
        return parkingCardVO;
    }

    /**
     * 预约停车场月卡
     *
     * @param parkingCard
     */
    @Override
    public void bookParkingCard(ParkingCardVO parkingCard) {
        String[] params = {"name","phone","companyId","carNumber"};
        CheckEmptyUtil.check(JsonUtil.bean2Map(parkingCard),params);

        //后台预先写好的值在这里插入
        parkingCard.setFlowStatus(ParkingConstants.PARKING_CARD_STATUS_AUDITING);

        //设置参数
        Book book = parkingCard2book(parkingCard);

        book.setBusinessCode(BusinessCode.PROPERTY_CAR_PARK);
        book.setId(idGenerator.getNumberId());
        book.setStatus(Book.STATUS_NORMAL);
        book.setType(ResourceType.PARKING_CARD_TYPE);
        //新增时流程状态为审核中
        //不信任输入数据，只取当前用户信息
        UserInfo userInfo = UserContext.get();
        book.setUserId(userInfo.getId());
        //申请时间设为当前时间
        book.setApplyTime(LocalDateTime.now());
        //转为book类型
        int insert = baseMapper.insert(book);
        if (insert!=1){
            log.error("预定停车场月卡失败");
            throw new ApplicationException(ErrorCode.SERVICE_ERROR, "提交申请失败，请重试");
        }
    }

    /**
     * 分页查询停车场月卡预约记录
     *根据ui判断只能查到审核中、审核通过带续费和审核未通过的记录,但目前似乎做不到
     * @param page 分页参数
     * @return
     */
    @Override
    public Page listParkingCardByUserAndPage(Page page) {
        UserInfo userInfo = UserContext.get();
        baseMapper.selectPage(page,new QueryWrapper<Book>()
        .lambda()
        .eq(Book::getBusinessCode, BusinessCode.PROPERTY_CAR_PARK)
        .eq(Book::getStatus, Book.STATUS_NORMAL)
        .eq(Book::getUserId,userInfo.getId())
        .orderByDesc(Book::getCreateTime)
        );

        List records = page.getRecords();
        if (CollectionUtil.isNotEmpty(records)){
            List<ParkingCardVO> recordsNew = CollectionUtil.createArrayList();
            for (Object record:records){
                ParkingCardVO parkingCard = book2parkingCard((Book) record);
                //查询的时候会校验是否过期并更新数据
                if (parkingCard.getExpireTime().isBefore(LocalDate.now())){
                    parkingCard.setFlowStatus(ParkingConstants.PARKING_CARD_STATUS_OVERDUE);
                    baseMapper.updateById(parkingCard2book(parkingCard));
                }
                recordsNew.add(parkingCard);
            }
            page.setRecords(recordsNew);
        }
        return page;
    }

    /**
     * 根据预约id删除停车场预约记录（取消预约）
     *
     * @param bookId 预约id
     */
    @Override
    public void deleteParkingCardById(Long bookId) {
            int delete = baseMapper.delete(new QueryWrapper<Book>()
                    .lambda()
                    .eq(Book::getId, bookId)
            );
            if(delete==0){
                log.error("删除预约记录失败");
                throw new ApplicationException(ErrorCode.SERVICE_ERROR, "取消预约失败");
            }
            redisService.del(bookId+"getParkingCardById");
    }

    /**
     * 停车场月卡审核通过。
     * @param bookId
     */
    @Override
    public void passParkingCard(Long bookId) {
        ParkingCardVO parkingCard = getParkingCardById(bookId);
        parkingCard.setFlowStatus(ParkingConstants.PARKING_CARD_STATUS_PASS);
        //暂时初始过期时间设为七天后。
        // FIXME: 2019/7/9 可能是先续费一定时间才可以通过审核，目前暂时不明确
        if (parkingCard.getExpireTime()==null){
            parkingCard.setExpireTime(LocalDate.now().plusDays(7));
        }
        baseMapper.updateById(parkingCard2book(parkingCard));
        redisService.del(bookId+"getParkingCardById");
    }

    /**
     * 停车场月卡审核不通过
     * 锁同上
     * @param bookId
     */
    @Override
    public void rejectParkingCard(Long bookId) {
        ParkingCardVO parkingCard = getParkingCardById(bookId);
        boolean flag = false;
        //获取锁
        while (!flag){
            flag = redisService.getLock(bookId.toString());
        }
        parkingCard.setFlowStatus(ParkingConstants.PARKING_CARD_STATUS_FAIL);
        baseMapper.updateById(parkingCard2book(parkingCard));
        redisService.del(bookId+"getParkingCardById");
        //操作完成，删除锁
        redisService.deleteLock(bookId.toString());
    }

    /**
     * 展示所有停车场月卡
     *
     * @return
     */
    @Override
    public List<ParkingCardVO> listParkingCard() {
        UserInfo userInfo = UserContext.get();
        //查询book表
        List<Book> books = baseMapper.selectList(new QueryWrapper<Book>()
                .lambda()
                .eq(Book::getBusinessCode, BusinessCode.PROPERTY_CAR_PARK)
                .eq(Book::getStatus, Book.STATUS_NORMAL)
                .orderByDesc(Book::getCreateTime)
        );

        if (CollectionUtil.isEmpty(books)){
            log.debug("查询停车场月卡记录为空，userId="+userInfo.getId());
            return CollectionUtil.createArrayList();
        }

        //将book记录转为parkingCard记录
        List<ParkingCardVO> parkingCards = CollectionUtil.createArrayList();

        for (Book book : books){
            ParkingCardVO parkingCard = book2parkingCard(book);
            //查询的时候会校验是否过期
            if (parkingCard.getExpireTime().isBefore(LocalDate.now())){
                parkingCard.setFlowStatus(ParkingConstants.PARKING_CARD_STATUS_OVERDUE);
            }
            parkingCards.add(parkingCard);
        }
        return parkingCards;
    }

    /**
     * 分页展示所有停车场月卡
     *
     * @param page
     * @return
     */
    @Override
    public Page listParkingCardByPage(Page page) {
        //查询book表
        baseMapper.selectPage(page,new QueryWrapper<Book>()
                .lambda()
                .eq(Book::getBusinessCode, BusinessCode.PROPERTY_CAR_PARK)
                .eq(Book::getStatus, Book.STATUS_NORMAL)
                .orderByDesc(Book::getCreateTime)
        );
        List records = page.getRecords();
        if (CollectionUtil.isNotEmpty(records)){
            //将book记录转为parkingCard记录
            List<ParkingCardVO> recordsNew = CollectionUtil.createArrayList();
            for (Object record:records){
                ParkingCardVO parkingCard = book2parkingCard((Book) record);
                recordsNew.add(parkingCard);
            }
            page.setRecords(recordsNew);
        }
        return page;
    }

    /**
     * 将book转为parkingCardVO类型
     * @param book
     * @return
     */
    private ParkingCardVO book2parkingCard(Book book){
        //复制通用字段
        ParkingCardVO parkingCard = new ParkingCardVO();
        BeanUtils.copyProperties(book,parkingCard);
        String extendVal = book.getExtendVal();
        if (StringUtil.isEmpty(extendVal)){
            //因为其中包含必填字段，若此字段为空则数据一定出错
            log.error("获取停车场月卡记录extendVal错误，id="+book.getId());
            throw new ApplicationException(ErrorCode.SERVICE_ERROR);
        }
        ParkingCardVO extendVO = JsonUtil.json2Bean(extendVal, ParkingCardVO.class);
        //从扩展字段中读取其他值
        parkingCard.setCompanyId(extendVO.getCompanyId());
        parkingCard.setCarNumber(extendVO.getCarNumber());
        parkingCard.setFlowStatus(extendVO.getFlowStatus());
        parkingCard.setName(extendVO.getName());
        parkingCard.setPhone(extendVO.getPhone());
        parkingCard.setCompanyName(extendVO.getCompanyName());
        parkingCard.setExpireTime(extendVO.getExpireTime());
        return parkingCard;
    }

    /**
     * 将parkingCardVO转为book类型
     * @param parkingCard
     * @return
     */
    private Book parkingCard2book(ParkingCardVO parkingCard){
        //注意：parkingCardVO的部分字段是存放在book.extendVal内的

        //复制通用字段
        Book book = new Book();
        BeanUtils.copyProperties(parkingCard,book);

        //读取扩展字段所需字段
        String flowStatus = parkingCard.getFlowStatus();
        Long companyId = parkingCard.getCompanyId();
        String carNumber = parkingCard.getCarNumber();
        String name = parkingCard.getName();
        String phone = parkingCard.getPhone();
        LocalDate expireTime = parkingCard.getExpireTime();
        String companyName = parkingCard.getCompanyName();

        //取出足够参数后初始化parkingCard进行参数填充
        parkingCard = new ParkingCardVO();

        parkingCard.setFlowStatus(flowStatus);
        parkingCard.setCompanyId(companyId);
        parkingCard.setCarNumber(carNumber);
        parkingCard.setName(name);
        parkingCard.setPhone(phone);

        //公司名称不是必填项，如果公司名称为空，则去根据公司id（必填）查询
        if (StringUtil.isEmpty(companyName)){
            Enterprise enterprise = enterpriseService.getById(companyId);
            parkingCard.setCompanyName(enterprise.getName());
        }else {
            parkingCard.setCompanyName(companyName);
        }
        parkingCard.setExpireTime(expireTime);
        book.setExtendVal(JsonUtil.bean2JsonString(parkingCard));

        return book;
    }
}
