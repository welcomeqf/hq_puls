package com.gpdi.hqplus.process.listener;

import com.gpdi.hqplus.common.constants.RabbitMqQueue;
import com.gpdi.hqplus.common.constants.RedisConstants;
import com.gpdi.hqplus.common.entity.ProcessStartBO;
import com.gpdi.hqplus.common.entity.UserContext;
import com.gpdi.hqplus.common.entity.UserInfo;
import com.gpdi.hqplus.common.manager.RedisService;
import com.gpdi.hqplus.common.util.ExceptionUtil;
import com.gpdi.hqplus.process.service.IProcessService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author: lianghb
 * @create: 2019-06-30 12:37
 **/
@Slf4j
@Component
public class ProcessStartListener {
    @Autowired
    private IProcessService processService;
    @Autowired
    private RedisService redisService;

    @RabbitListener(queues = RabbitMqQueue.PROCESS_START)
    @RabbitHandler
    public void processStart(ProcessStartBO bo) {
        try {
            UserInfo userInfo = (UserInfo) redisService.get(RedisConstants.USER_INFO + bo.getUserRedisKey());
            UserContext.set(userInfo);
            SecurityContextHolder.setContext(bo.getSecurityContext());

            processService.start(bo);
            log.info("process start success:{}", bo);
        } catch (Exception e) {
            log.error("process start fail:{}", ExceptionUtil.getMassage(e));
        }
    }


    @RabbitListener(queues = RabbitMqQueue.PROCESS_START_ADDRESS)
    @RabbitHandler
    public void processStartOffice(ProcessStartBO bo) {
        try {
            UserInfo userInfo = (UserInfo) redisService.get(RedisConstants.USER_INFO + bo.getUserRedisKey());
            UserContext.set(userInfo);
            SecurityContextHolder.setContext(bo.getSecurityContext());

            processService.start(bo);
            log.info("process address start success:{}", bo);
        } catch (Exception e) {
            log.error("process address start fail:{}", ExceptionUtil.getMassage(e));
        }
    }



}
