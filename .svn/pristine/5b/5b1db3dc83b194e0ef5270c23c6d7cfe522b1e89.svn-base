package com.gpdi.hqplus.water.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.entity.UserContext;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.BeanPowerHelper;
import com.gpdi.hqplus.common.util.CollectionUtil;
import com.gpdi.hqplus.common.util.IdGenerator;
import com.gpdi.hqplus.common.util.StringUtil;
import com.gpdi.hqplus.resources.entity.PropertyApply;
import com.gpdi.hqplus.resources.service.impl.PropertyApplyServiceImpl;
import com.gpdi.hqplus.water.constants.WaterApplyCode;
import com.gpdi.hqplus.water.entity.query.PropertyAppApplyQuery;
import com.gpdi.hqplus.water.entity.query.PropertyApplyQuery;
import com.gpdi.hqplus.water.entity.query.PropertyAuditQuery;
import com.gpdi.hqplus.water.entity.vo.PropertyApplyVO;
import com.gpdi.hqplus.water.service.IWaterApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 物业申请表 服务实现类
 * </p>
 *
 * @author liuJiaHui
 * @since 2019-07-17
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class WaterApplyServiceImpl extends PropertyApplyServiceImpl implements IWaterApplyService {

    @Autowired
    private IdGenerator idGenerator ;

    /**
     * <p>
     * 分页获取水电开通申请记录
     * </p>
     *
     * @return
     */
    @Override
    public Page<PropertyApplyVO> getPage(PropertyApplyQuery query) {

        Page<PropertyApply> propertyApplyPage = new Page<>();
        propertyApplyPage.setCurrent(query.getCurrent());
        propertyApplyPage.setSize(query.getSize()) ;

        LambdaQueryWrapper<PropertyApply> queryWrapper = new QueryWrapper<PropertyApply>().lambda()
                .orderByDesc(PropertyApply::getCreateTime) ;

        if (StringUtil.isNotBlank(query.getUserName())) {
            queryWrapper.like(PropertyApply::getUserName, query.getUserName()) ;
        }
        if (StringUtil.isNotBlank(query.getUserConnect())) {
            queryWrapper.like(PropertyApply::getUserConnect, query.getUserConnect()) ;
        }

        baseMapper.selectPage(propertyApplyPage, queryWrapper) ;

        List<PropertyApply> records = propertyApplyPage.getRecords() ;
        List<PropertyApplyVO> result = CollectionUtil.createArrayList(records.size()) ;

        for (PropertyApply record : records) {

            result.add(getWaterList(record)) ;

        }

        return BeanPowerHelper.mapPage(propertyApplyPage, result) ;

    }

    /**
     * <p>
     * 水电开通确认
     * </p>
     */
    @Override
    public void insertResult(PropertyAuditQuery query) {

        PropertyApply propertyApply = baseMapper.selectOne(new QueryWrapper<PropertyApply>()
        .lambda()
        .eq(PropertyApply::getId, query.getId())) ;

        if (propertyApply == null){
            log.error("该用户信息不存在物业申请表中");
            throw new ApplicationException(ErrorCode.RESOURCES_NOT_FIND,"resources is not find");
        }

        propertyApply.setFeedback(query.getFeedback()) ;
        propertyApply.setStatus(query.getStatus()) ;
        propertyApply.setUpdateTime(LocalDateTime.now()) ;

        boolean b = propertyApply.updateById();

        if (!b) {
            log.error("水电开通状态确认失败");
        }

    }

    /**
     * <p>
     * 水电开通申请
     * </p>
     */
    @Override
    public void apply(PropertyAppApplyQuery query) {

        PropertyApply propertyApply = baseMapper.selectOne(new QueryWrapper<PropertyApply>()
                .lambda()
                .eq(PropertyApply::getUserId, UserContext.get().getId()));

        if (propertyApply != null) {
            throw new ApplicationException(ErrorCode.RESOURCES_EXISTING,"该用户已申请过，不能重复申请");
        }

        PropertyApply propertyApply2 = baseMapper.selectOne(new QueryWrapper<PropertyApply>()
                .lambda()
                .eq(PropertyApply::getAddress, query.getAddress()));

        if (propertyApply2 != null) {
            throw new ApplicationException(ErrorCode.RESOURCES_EXISTING,"该单元楼地址已申请过，不能重复申请");
        }

        PropertyApply apply = new PropertyApply()
                .setId(idGenerator.getNumberId())
                .setUserId(UserContext.get().getId())
                .setUserName(query.getUserName())
                .setUserConnect(query.getUserConnect())
                .setAddress(query.getAddress())
                .setUserMessage(query.getUserMessage())
                .setCreateTime(LocalDateTime.now())
                .setStatus(WaterApplyCode.APPLY_START) ;

        boolean insert = apply.insert();

        if (!insert) {
            log.error("insert water error");
            throw new ApplicationException(ErrorCode.SERVICE_ERROR,"提交申请失败，请稍后再试") ;
        }

    }

    /**
     * <p>
     * app水电开通申请记录
     * </p>
     */
    @Override
    public Page<PropertyApplyVO> getList(PropertyApplyQuery query) {

        Page<PropertyApply> page = new Page<>() ;
        page.setCurrent(query.getCurrent()) ;
        page.setSize(query.getCurrent()) ;

        LambdaQueryWrapper<PropertyApply> queryWrapper = new QueryWrapper<PropertyApply>()
                .lambda()
                .eq(PropertyApply::getUserId, UserContext.get().getId())
                .orderByDesc(PropertyApply::getCreateTime) ;

        baseMapper.selectPage(page,queryWrapper) ;

        List<PropertyApply> records = page.getRecords();
        List<PropertyApplyVO> result = CollectionUtil.createArrayList(records.size());

        for (PropertyApply record : records) {

            result.add(getWaterList(record)) ;

        }

        return BeanPowerHelper.mapPage(page,result);

    }

    private PropertyApplyVO getWaterList(PropertyApply record) {

        PropertyApplyVO vo = new PropertyApplyVO() ;
        BeanUtils.copyProperties(record,vo);

        return vo ;

    }

}
