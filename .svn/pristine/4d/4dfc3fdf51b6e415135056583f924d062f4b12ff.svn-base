package com.gpdi.hqplus.process.service.impl;

import com.gpdi.hqplus.process.config.SecurityUtil;
import com.gpdi.hqplus.process.entity.bo.ComplaintBO;
import com.gpdi.hqplus.process.service.IComplaintService;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.runtime.TaskRuntime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComplaintServiceImplTest {

    @Autowired
    IComplaintService complaintService;
    @Autowired
    SecurityUtil securityUtil;
    @Autowired
    TaskRuntime taskRuntime;

    @Test
    public void startTask() {
        securityUtil.logInAs("other");

        ComplaintBO bo = new ComplaintBO();
        bo.setId(28396l);

        complaintService.startTask(bo);


        securityUtil.logInAs("salaboy");

        List<Task> tasks =
                complaintService.listMyTask();
        System.out.println(tasks.size());
        for (Task task : tasks) {
            System.out.println(task);
            complaintService.claimAndCompleteTask(task.getId());
        }
        System.out.println("**************************************************");
        securityUtil.logInAs("ryandawsonuk");
        List<Task> tasks1 = complaintService.listMyTask();
        for (Task task : tasks1) {
            System.out.println(task);
            complaintService.claimAndCompleteTask(task.getId());
        }

    }

    @Test
    public void listMyTask(){
        securityUtil.logInAs("salaboy");

        List<Task> tasks =
                complaintService.listMyTask();
        System.out.println(tasks.size());
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    @Test
    public void completeTask() {

        securityUtil.logInAs("system");


    }
}