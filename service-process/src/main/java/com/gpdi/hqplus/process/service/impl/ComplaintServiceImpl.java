package com.gpdi.hqplus.process.service.impl;

import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.constants.ProcessDefinitionEnum;
import com.gpdi.hqplus.process.entity.Complaint;
import com.gpdi.hqplus.process.entity.bo.ComplaintBO;
import com.gpdi.hqplus.process.mapper.ComplaintMapper;
import com.gpdi.hqplus.process.service.IComplaintService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.process.service.IProcessService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.task.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 投诉建议表 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-06-27
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ComplaintServiceImpl extends ServiceImpl<ComplaintMapper, Complaint> implements IComplaintService {

    @Autowired
    private IProcessService processService;

    @Override
    public void startTask(ComplaintBO bo) {
        ProcessDefinitionEnum complaint = ProcessDefinitionEnum.COMPLAINT;
        //processService.start(complaint.getKey(), bo.getId().toString(), complaint.getName(), null);
    }

    @Override
    public void completeTask(String taskId) {
        Task task = processService.task(taskId);
        if (task == null) {
            throw new ApplicationException(ErrorCode.SERVICE_ERROR, "任务不存在");
        }

//        String businessKey = task.getBusinessKey();
//        Complaint complaint = baseMapper.selectById(Long.parseLong(businessKey));
//        if (complaint == null) {
//            //throw new ApplicationException(ErrorCode.SERVICE_ERROR, "任务数据不存在");
//        }

        String description = task.getDescription();
        processService.complete(taskId);
    }

    @Override
    public List<Task> listMyTask() {
        Page<Task> tasks = processService.tasks(0, 10);
        return tasks.getContent();
    }

    @Override
    public void claim(String taskId) {
        processService.claim(taskId);
    }

    @Override
    public void claimAndCompleteTask(String taskId) {
        processService.claimAndComplete(taskId);
    }
}
