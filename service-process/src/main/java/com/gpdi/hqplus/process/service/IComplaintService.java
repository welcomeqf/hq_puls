package com.gpdi.hqplus.process.service;

import com.gpdi.hqplus.process.entity.Complaint;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gpdi.hqplus.process.entity.bo.ComplaintBO;
import org.activiti.api.task.model.Task;

import java.util.List;

/**
 * <p>
 * 投诉建议表 服务类
 * </p>
 *
 * @author lianghb
 * @since 2019-06-27
 */
public interface IComplaintService extends IService<Complaint> {
    void startTask(ComplaintBO bo);

    void completeTask(String taskId);

    List<Task> listMyTask();

    void claim(String taskId);

    void claimAndCompleteTask(String taskId);
}
