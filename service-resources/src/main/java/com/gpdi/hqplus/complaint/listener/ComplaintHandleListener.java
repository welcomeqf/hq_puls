package com.gpdi.hqplus.complaint.listener;

import com.gpdi.hqplus.common.constants.BusinessCode;
import com.gpdi.hqplus.common.constants.RedisConstants;
import com.gpdi.hqplus.common.entity.ProcessCallbackBO;
import com.gpdi.hqplus.common.entity.UserContext;
import com.gpdi.hqplus.common.entity.UserInfo;
import com.gpdi.hqplus.common.manager.RedisService;
import com.gpdi.hqplus.common.util.ExceptionUtil;
import com.gpdi.hqplus.complaint.cnastants.ComplaintRabbitMqQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @Author qf
 * @Date 2019/7/12
 * @Version 1.0
 */
@Slf4j
@Component
public class ComplaintHandleListener {

    @Autowired
    private RedisService redisService;

    public static String businessCode = BusinessCode.OTHER_COMPLAINT;

    @RabbitListener(queues = ComplaintRabbitMqQueue.QUEUE_COMPLAINT_HANDLE_BUSINESS)
    @RabbitHandler
    public void processStart(ProcessCallbackBO bo) {

        UserInfo userInfo = null;

        try {
            if (!businessCode.equals(bo.getBusinessCode())) {
                return;
            }

            userInfo = (UserInfo) redisService.get(RedisConstants.USER_INFO + bo.getUserRedisKey());
            userInfo.setProjectCode(bo.getProjectCode());
            UserContext.set(userInfo);



        } catch (Exception e) {
            log.error("process callback fail:{}", ExceptionUtil.getMassage(e));
        }
    }


}
