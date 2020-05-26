package com.gpdi.hqplus.complaint.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.entity.UserContext;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.manager.MQService;
import com.gpdi.hqplus.common.manager.RedisService;
import com.gpdi.hqplus.common.util.IdGenerator;
import com.gpdi.hqplus.complaint.cnastants.ComplaintHandleEnum;
import com.gpdi.hqplus.complaint.cnastants.ComplaintTypeEnum;
import com.gpdi.hqplus.complaint.entity.Complaint;
import com.gpdi.hqplus.complaint.entity.query.ComplaintHistoryQuery;
import com.gpdi.hqplus.complaint.entity.query.ComplaintQuery;
import com.gpdi.hqplus.complaint.entity.vo.ImagesVo;
import com.gpdi.hqplus.complaint.mapper.ComplaintMapper;
import com.gpdi.hqplus.complaint.service.IComplaintService;
import com.gpdi.hqplus.complaint.util.ComplaintEnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Wrapper;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 意见反馈实现类
 * @Author qf
 * @Date 2019/7/11
 * @Version 1.0
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ComplaintServiceImpl extends ServiceImpl<ComplaintMapper, Complaint> implements IComplaintService {

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private MQService mqService;

    @Autowired
    private RedisService redisService;

    private final String REGISTER_LOCK = "REGISTER_LOCK::";

    /**
     * 提交申请的意见
     * @param query
     */
    @Override
    public Complaint apply(ComplaintQuery query) {
        //获取分布式锁
        String lockKey = REGISTER_LOCK + query.getMessage();
        boolean lock = redisService.getLock(lockKey, 5);
        if (!lock) {
            throw new ApplicationException(ErrorCode.SERVICE_ERROR,"网络繁忙");
        }

        try {
            //设置属性
            query.setId(idGenerator.getNumberId());
            query.setUserId(UserContext.get().getId());
            query.setStatus(ComplaintHandleEnum.APPLY.getStatus());
            query.setCreateTime(LocalDateTime.now());
            query.setUpdateTime(LocalDateTime.now());

            ImagesVo imagesVo = new ImagesVo();
            imagesVo.setTid(query.getTid());
            //存储图片id
            query.setImages(imagesVo.toString());

            //添加数据
            boolean insert = query.insert();
            if (!insert) {
                log.error("insert complaint handle apply fail");
                throw new ApplicationException(ErrorCode.SERVICE_ERROR, "提交失败，请稍后再试！");
            }

            Complaint complaint = baseMapper.selectById(query.getId());
            if (complaint == null) {
                log.error("return complaint apply fail.");
                throw new ApplicationException(ErrorCode.SERVICE_ERROR,"意见申请返回的数据查询失败");
            }
            return complaint;
        } finally {
            redisService.deleteLock(lockKey);
        }

//        ProcessStartBO bo = new ProcessStartBO();
//        bo.setBusinessCode(BusinessCode.OTHER_COMPLAINT);
//        bo.setBusinessKey(query.getId());
//        bo.setCallbackQueueName(ComplaintRabbitMqQueue.QUEUE_COMPLAINT_HANDLE_BUSINESS);
//        bo.setDefinitionKey(ProcessDefinitionEnum.COMPLAINT.getKey());
//        bo.setName("意见反馈申请");
//        bo.setProjectCode(UserContext.get().getProjectCode());
//        bo.setSecurityContext(SecurityContextHolder.getContext());
//        bo.setUserRedisKey(UserContext.get().getRedisKey());
//        bo.setProcessPointCode(query.getStatus());
//
//        boolean sendObject = mqService.sendObject(RabbitMqQueue.PROCESS_START, bo);
//
//        if (!sendObject) {
//            log.error("apply mq send fail.");
//            throw new ApplicationException(ErrorCode.SERVICE_ERROR,"申请失败，请稍后再试~");
//        }
    }

    /**
     * 通过id查询建议反馈的记录
     * @param id
     * @return
     */
    @Override
    public Complaint queryById(Long id) {
        Complaint complaint = baseMapper.selectById(id);

        return complaint;
    }

    /**
     * 根据id修改状态
     * @param complaint
     */
    @Override
    public boolean updateStatusById(Complaint complaint) {
        int i = baseMapper.updateById(complaint);
        if (i <= 0) {
            log.error("complaint update status from id fail.");
            throw new ApplicationException(ErrorCode.SERVICE_ERROR,"修改状态失败~");
        }
        return true;
    }

    /**
     * 查询该用户所有的历史流程记录
     * @return
     */
    @Override
    public List<ComplaintHistoryQuery> listHistory() {
        Long id = UserContext.get().getId();
        LambdaQueryWrapper<Complaint> queryWrapper = new QueryWrapper<Complaint>().lambda()
                .eq(Complaint::getUserId,id).eq(Complaint::getStatus,"finish");
        List<Complaint> complaintList = baseMapper.selectList(queryWrapper);
        if (complaintList.size() == 0) {
            log.error("query all history from uid fail.");
            throw new ApplicationException(ErrorCode.SERVICE_ERROR,"查询该用户的所有流程记录失败");
        }

        //装配结果返回
        List<ComplaintHistoryQuery> queryList = new ArrayList<>();
        for (Complaint complaint : complaintList) {
            ComplaintHistoryQuery query = new ComplaintHistoryQuery();

            query.setType(complaint.getType());
            query.setApplyTime(complaint.getCreateTime());
            query.setDealTime(complaint.getUpdateTime());
            query.setImages(complaint.getImages());
            query.setMessage(complaint.getMessage());
            query.setUserConnect(complaint.getUserConnect());
            query.setUserName(complaint.getUserName());

            ComplaintHandleEnum complaintHandleEnum = ComplaintEnumUtil.getByStatus(complaint.getStatus());
            String msg = complaintHandleEnum.getMsg();
            query.setResult(msg);

            query.setUid(id);
            queryList.add(query);
        }


        return queryList;
    }

    /**
     * 查看用户正在进行中的记录
     * @return
     */
    @Override
    public List<ComplaintHistoryQuery> listProcess() {
        Long id = UserContext.get().getId();
        LambdaQueryWrapper<Complaint> queryWrapper = new QueryWrapper<Complaint>().lambda()
                .eq(Complaint::getUserId,id).ne(Complaint::getStatus,"finish");

        List<Complaint> complaintList = baseMapper.selectList(queryWrapper);
        if (complaintList.size() == 0) {
            log.error("query all history from uid fail.");
            throw new ApplicationException(ErrorCode.SERVICE_ERROR,"查询该用户的所有流程记录失败");
        }

        //装配结果返回
        List<ComplaintHistoryQuery> queryList = new ArrayList<>();
        for (Complaint complaint : complaintList) {
            ComplaintHistoryQuery query = new ComplaintHistoryQuery();

            query.setApplyTime(complaint.getCreateTime());
            query.setType(complaint.getType());
            query.setDealTime(complaint.getUpdateTime());
            query.setImages(complaint.getImages());
            query.setMessage(complaint.getMessage());
            query.setUserConnect(complaint.getUserConnect());
            query.setUserName(complaint.getUserName());

            ComplaintHandleEnum complaintHandleEnum = ComplaintEnumUtil.getByStatus(complaint.getStatus());
            String msg = complaintHandleEnum.getMsg();
            query.setResult(msg);

            query.setUid(id);
            queryList.add(query);
        }
        return queryList;
    }

}
