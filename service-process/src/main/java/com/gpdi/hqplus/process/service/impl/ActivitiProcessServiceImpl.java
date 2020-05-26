package com.gpdi.hqplus.process.service.impl;

import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.entity.ProcessCallbackBO;
import com.gpdi.hqplus.common.entity.ProcessStartBO;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.BeanPowerHelper;
import com.gpdi.hqplus.common.util.JsonUtil;
import com.gpdi.hqplus.common.manager.impl.RabbitMqServiceImpl;
import com.gpdi.hqplus.process.service.IProcessService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: lianghb
 * @create: 2019-06-26 22:40
 **/
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ActivitiProcessServiceImpl implements IProcessService {
    @Autowired
    private ProcessRuntime processRuntime;
    @Autowired
    private TaskRuntime taskRuntime;
    @Autowired
    private TaskService taskService;

    @Autowired
    private RabbitMqServiceImpl rabbitMqService;

    private final String VARIABLE_PROCESS_START_NAME = "processStartBO";

    /**
     * 创建一个组任务
     *
     * @param processKey
     */
    @Override
    public void create(String processKey) {
        taskRuntime.create(
                TaskPayloadBuilder
                        .create()
                        .withFormKey(processKey)
                        .withName("First Team Task")
                        .withDescription("This is something really important")
                        .withCandidateGroup("groupName")
                        .withPriority(10)
                        .build());
    }

    /**
     * 启动一个任务
     *
     * @param bo
     */
    @Override
    public void start(ProcessStartBO bo) {
        ProcessInstance processInstance = processRuntime.start(
                ProcessPayloadBuilder
                        .start()
                        .withProcessDefinitionKey(bo.getDefinitionKey())
                        .withBusinessKey(bo.getBusinessKey().toString())
                        .withName(bo.getName())
                        .withVariables(bo.getVariables())
                        .withVariable(VARIABLE_PROCESS_START_NAME, bo)
                        .build());
        ProcessCallbackBO callbackBO = BeanPowerHelper.mapCompleteOverrider(bo, ProcessCallbackBO.class);
        callbackBO.setTaskId(processInstance.getId());
//        rabbitMqService.sendObject(bo.getCallbackQueueName(), callbackBO);
        log.info("> start process:{}", processInstance);
    }

    /**
     * 拾取一个任务
     *
     * @param taskId
     */
    @Override
    public void claim(String taskId) {
        try {
            Task task = taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(taskId).build());
            log.info("> process claim:" + task);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new ApplicationException(ErrorCode.SERVICE_ERROR, "任务已被他人拾取");
        }
    }

    /**
     * 完成一个任务
     *
     * @param taskId
     */
    @Override
    public void complete(String taskId) {
        try {
            Object variable = taskService.getVariable(taskId, VARIABLE_PROCESS_START_NAME);
            Task task = taskRuntime.complete(TaskPayloadBuilder.complete()
                    .withTaskId(taskId)
                    .build());

            ProcessStartBO startBO = JsonUtil.json2Bean(variable.toString(), ProcessStartBO.class);
            ProcessCallbackBO callbackBO = BeanPowerHelper.mapCompleteOverrider(startBO, ProcessCallbackBO.class);

            callbackBO.setProcessPointCode(task.getDescription());
//            rabbitMqService.sendObject(startBO.getCallbackQueueName(), callbackBO);
            log.info("> process complete:" + task);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new ApplicationException(ErrorCode.SERVICE_ERROR, "任务已被他人完成");
        }
    }

    @Override
    public void claimAndComplete(String taskId) {
        claim(taskId);
        complete(taskId);
    }

    /**
     * 根据 id 查询任务
     *
     * @param taskId
     * @return
     */
    @Override
    public Task task(String taskId) {
        Task task = taskRuntime.task(taskId);
        return task;
    }

    /**
     * 分页查询本人任务
     *
     * @param start
     * @param end
     * @return
     */
    @Override
    public Page<Task> tasks(int start, int end) {
        Page<Task> tasks = taskRuntime.tasks(Pageable.of(start, end));
        return tasks;
    }
}
