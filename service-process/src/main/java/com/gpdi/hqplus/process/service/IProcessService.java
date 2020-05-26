package com.gpdi.hqplus.process.service;


import com.gpdi.hqplus.common.entity.ProcessStartBO;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.task.model.Task;

import java.util.Map;

/**
 * 工作流接口
 *
 * @author: lianghb
 * @create: 2019-06-26 22:40
 **/
public interface IProcessService {

    /**
     * 创建一个流程
     *
     * @param processKey
     */
    void create(String processKey);

    /**
     * 启动一个任务
     *
     * @param bo
     */
    void start(ProcessStartBO bo);

    /**
     * 拾取一个任务
     *
     * @param taskId
     */
    void claim(String taskId);

    /**
     * 完成一个任务
     *
     * @param taskId
     */
    void complete(String taskId);

    /**
     * 拾取并完成一个任务
     *
     * @param taskId
     */
    void claimAndComplete(String taskId);

    /**
     * 根据 id 获取任务
     *
     * @param taskId
     * @return
     */
    Task task(String taskId);

    /**
     * 分页获取任务
     *
     * @param start
     * @param end
     * @return
     */
    Page<Task> tasks(int start, int end);
}
