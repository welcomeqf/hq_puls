package com.gpdi.hqplus.complaint.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.entity.ProcessCallbackBO;
import com.gpdi.hqplus.common.entity.UserContext;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.StringUtil;
import com.gpdi.hqplus.complaint.cnastants.ComplaintHandleEnum;
import com.gpdi.hqplus.complaint.entity.Complaint;
import com.gpdi.hqplus.complaint.entity.query.ComplaintPageQuery;
import com.gpdi.hqplus.complaint.entity.query.TypeComplaintManageQuery;
import com.gpdi.hqplus.complaint.entity.vo.ComplaintAdminVo;
import com.gpdi.hqplus.complaint.entity.vo.ComplaintFeedbackVo;
import com.gpdi.hqplus.complaint.mapper.ComplaintAdminMapper;
import com.gpdi.hqplus.complaint.service.IComplaintAdminService;
import com.gpdi.hqplus.complaint.service.IComplaintService;
import com.gpdi.hqplus.complaint.service.ITypeComplaintService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @Author qf
 * @Date 2019/7/12
 * @Version 1.0
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ComplaintAdminServiceImpl extends ServiceImpl<ComplaintAdminMapper, Complaint> implements IComplaintAdminService {

    @Autowired
    private IComplaintService complaintService;

    @Autowired
    private ITypeComplaintService typeComplaintService;

    /**
     * 更改流程
     * @param bo
     */
    @Override
    public Long updateStatus(ProcessCallbackBO bo) {
        //根据Id查询意见记录
        Complaint query = complaintService.queryById(bo.getBusinessKey());

        if (query == null) {
            log.error("complaint query fail from update status.");
            throw new ApplicationException(ErrorCode.SERVICE_ERROR,"查询单条流程记录有误,请稍后再试");
        }

        //设置流程的更改
        String processPointCode = bo.getProcessPointCode();
//        List<LocalDateTime> startList = new ArrayList();
//        List<LocalDateTime> endList = new ArrayList();
        long days = 0;
        Integer limitDay = 0;
        if (StringUtil.isNotBlank(processPointCode)) {
            if (ComplaintHandleEnum.APPLY.getStatus().equals(processPointCode)) {
                query.setStatus(ComplaintHandleEnum.HANDLE.getStatus());
                LocalDateTime startTime = LocalDateTime.now();
                //获得限制时间,分类类别
                Map<String, Object> variables = bo.getVariables();
                Long fid = (Long) variables.get("fid");
                TypeComplaintManageQuery ty = typeComplaintService.queryTypeById(fid);
                limitDay = ty.getLimitDay();

                String name = ty.getName();
                //设置业务类型
                query.setBusinessType(name);
                //更新时间
                query.setUpdateTime(startTime);
                //获取部门编号存入数据库中
                String deptCode = (String) variables.get("deptCode");
                query.setBusinessDept(deptCode);
            } else if (ComplaintHandleEnum.HANDLE.getStatus().equals(processPointCode)) {
                query.setStatus(ComplaintHandleEnum.FINISH.getStatus());
                LocalDateTime endTime = LocalDateTime.now();
                LocalDateTime startTime = query.getUpdateTime();
                Duration duration = Duration.between(startTime,endTime);
                days = duration.toDays();
                //设置最后处理时间
                query.setUpdateTime(endTime);
            } else if (ComplaintHandleEnum.FINISH.getStatus().equals(processPointCode)) {
                query.setStatus(ComplaintHandleEnum.CANCEL.getStatus());
            }
        }

        String taskId = bo.getTaskId();
        if (StringUtil.isNotBlank(taskId)) {
            query.setTaskId(taskId);
        }

        String overtime = null;
        if (limitDay.longValue() < days && limitDay != 0) {
            overtime = "已超时";
        } else {
            overtime = "未超时";
        }
        query.setOvertime(overtime);

        //修改数据库的状态
        boolean result = complaintService.updateStatusById(query);
        if (!result) {
            log.error("complaint update status fail.");
            throw new ApplicationException(ErrorCode.SERVICE_ERROR,"更新状态失败~");
        }
        return days;

    }

    /**
     * 管理员删除状态
     * <p>返回处理人id</p>
     * @param complaintAdminVo
     * @return
     */
    @Override
    public Long dateleStatus(ComplaintAdminVo complaintAdminVo) {
        int result = baseMapper.deleteById(complaintAdminVo.getId());
        if (result <= 0) {
            log.error("admin delete complaint fail.");
            throw new ApplicationException(ErrorCode.SERVICE_ERROR,"删除失败，请稍后再试.");
        }

        return complaintAdminVo.getDealUserId();
    }

    /**
     * 管理员取消状态
     * <p>直接反馈内容表示处理完毕</p>
     * @param complaintFeedbackVo
     */
    @Override
    public void feed(ComplaintFeedbackVo complaintFeedbackVo) {
        //根据id找到审核的这条记录
        Complaint complaint = complaintService.queryById(complaintFeedbackVo.getId());

        if (complaint == null) {
            log.error("admin complaint feed to query fail");
            throw new ApplicationException(ErrorCode.SERVICE_ERROR, "管理员反馈：查询记录失败");
        }

        //将状态改为完成的状态
        complaint.setStatus(ComplaintHandleEnum.FINISH.getStatus());
        complaint.setDealUserId(UserContext.get().getId());
        complaint.setFeedBack(complaintFeedbackVo.getFeedBack());

        //修改数据
        boolean result = complaint.updateById();
        if (!result){
            log.error("admin complaint cancel status fail.");
            throw new ApplicationException(ErrorCode.SERVICE_ERROR,"取消失败，请稍后再试~");
        }

    }

    /**
     * 分页查看所有流程的历史记录
     * @return
     */
    @Override
    public Page<Complaint> listPageHistory(ComplaintPageQuery complaintPage) {
        Page<Complaint> page = new Page<>();

        page.setCurrent(complaintPage.getCurrent());
        page.setSize(complaintPage.getSize());

        LambdaQueryWrapper<Complaint> queryWrapper = new QueryWrapper<Complaint>().lambda()
                .orderByDesc(Complaint::getUpdateTime);

        //高级条件查询
        if (StringUtil.isNotBlank(complaintPage.getUserName())) {
            queryWrapper.like(Complaint::getUserName, complaintPage.getUserName());
        }

        if (StringUtil.isNotBlank(complaintPage.getUserConnect())) {
            queryWrapper.eq(Complaint::getUserConnect, complaintPage.getUserConnect());
        }

        if (StringUtil.isNotBlank(complaintPage.getType())) {
            queryWrapper.eq(Complaint::getType, complaintPage.getType());
        }

        if (StringUtil.isNotBlank(complaintPage.getStatus())) {
            queryWrapper.eq(Complaint::getStatus, complaintPage.getStatus());
        }

        if (complaintPage.getCreateTime() != null) {
            queryWrapper.gt(Complaint::getCreateTime, complaintPage.getCreateTime());
        }

        if (complaintPage.getUpdateTime() != null) {
            queryWrapper.le(Complaint::getUpdateTime, complaintPage.getUpdateTime());
        }

        if (StringUtil.isNotBlank(complaintPage.getOvertime())) {
            queryWrapper.eq(Complaint::getOvertime, complaintPage.getOvertime());
        }

        if (StringUtil.isNotBlank(complaintPage.getBusinessType())) {
            queryWrapper.eq(Complaint::getBusinessType, complaintPage.getBusinessType());
        }

        if (StringUtil.isNotBlank(complaintPage.getBusinessDept())) {
            queryWrapper.eq(Complaint::getBusinessDept, complaintPage.getBusinessDept());
        }
        baseMapper.selectPage(page,queryWrapper);

        return page;
    }
}
