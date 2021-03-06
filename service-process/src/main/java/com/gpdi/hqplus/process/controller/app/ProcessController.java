package com.gpdi.hqplus.process.controller.app;

import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.constants.RabbitMqQueue;
import com.gpdi.hqplus.common.entity.UserContext;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.manager.MQService;
import com.gpdi.hqplus.common.manager.RedisService;
import com.gpdi.hqplus.process.service.IProcessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.task.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * App端接口，包括ios和安卓
 * 如需要区分ios和安卓，可在uri中继续加以区分
 *
 * @author: lianghb
 * @create: 2019-06-03 14:36
 **/
@Api
@Slf4j
@RestController
@RequestMapping("/v1/app/process")
public class ProcessController {
    @Autowired
    private IProcessService processService;

    @Autowired
    private MQService mqService;

    /**
     * 拾取并完成一个任务
     *
     * @param taskId
     * @return
     */
    @ApiOperation(value = "拾取并完成一个任务", notes = "拾取并完成一个任务")
    @ApiImplicitParam(name = "taskId", value = "任务 id", required = true, dataType = "String", paramType = "path")
    @RequestMapping("/claimAndComplete")
    public String claimAndComplete(String taskId) {
        processService.claimAndComplete(taskId);
        return "ok";
    }


    /**
     * 获取任务列表
     *
     * @param startIndex
     * @param endIndex
     * @return
     */
    @ApiOperation(value = "hello world", notes = "根据url的name来获取hello world信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startIndex", value = "开始索引", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "endIndex", value = "结束索引", required = true, dataType = "String", paramType = "path")
    })
    @PostMapping("/pageTasks")
    public Page<Task> pageTasks(int startIndex, int endIndex) {
        return processService.tasks(startIndex, endIndex);
    }


    //========================================物业放行=============================================

    /**
     * 物业放行审批
     *
     * @param productPassId
     * @return
     */
    @ApiOperation(value = "物业放行审批", notes = "物业放行审批")
    @ApiImplicitParam(name = "productPassId", value = "物业放行审批id", required = true, dataType = "String", paramType = "path")
    @RequestMapping("/productPass")
    public String productPass(String productPassId) {
        HashMap map = new HashMap(16);
        String userName = UserContext.get().getUserName();
        map.put("productId",productPassId);
        map.put("userName",userName);
//        boolean b = mqService.sendObject(RabbitMqQueue.PRODUCT_PASS_SUCCESS, map);
//        if (!b){
//            throw new ApplicationException(ErrorCode.SERVICE_ERROR,"物业放行审批失败,请稍后再试");
//        }
        return "ok";
    }
}
