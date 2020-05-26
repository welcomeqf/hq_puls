package com.gpdi.hqplus.process.service.impl;

import com.gpdi.hqplus.process.config.SecurityUtil;
import com.gpdi.hqplus.process.service.IProcessService;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.runtime.TaskRuntime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivitiProcessServiceImplTest {

    @Autowired
    private ProcessRuntime processRuntime;
    @Autowired
    private TaskRuntime taskRuntime;

    @Autowired
    IProcessService processService;

    @Autowired
    private SecurityUtil securityUtil;

    @Test
    public void test(){
        securityUtil.logInAs("system");

        Page<ProcessDefinition> processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));
        System.out.println(processDefinitionPage.getContent().size());
        for (ProcessDefinition processDefinition : processDefinitionPage.getContent()) {
            System.out.println(processDefinition);
        }
    }

    @Test
    public void test3(){
        securityUtil.logInAs("other");

        ProcessInstance processInstance = processRuntime
                .start(ProcessPayloadBuilder
                        .start()
                        .withProcessDefinitionKey("test02")
                        .withVariable("content", "66625")
                        .build());
        System.out.println(processInstance);// bd9baf64-98b5-11e9-870e-b287108dead2

        securityUtil.logInAs("erdemedeiros");
        Page<Task> tasks = taskRuntime.tasks(Pageable.of(0, 10));
        System.out.println(tasks.getContent().size());
        for (Task task : tasks.getContent()) {
            System.out.println(task);
        }
    }

    @Test
    public void test33(){
        securityUtil.logInAs("erdemedeiros");
        Page<Task> tasks = taskRuntime.tasks(Pageable.of(0, 10));
        System.out.println(tasks.getContent().size());
        for (Task task : tasks.getContent()) {
            System.out.println(task);
        }

        Task task = taskRuntime.task("bd9baf64-98b5-11e9-870e-b287108dead2");
        System.out.println(task);
    }
}