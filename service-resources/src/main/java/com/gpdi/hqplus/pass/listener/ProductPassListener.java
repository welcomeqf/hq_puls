package com.gpdi.hqplus.pass.listener;

import com.gpdi.hqplus.common.constants.BusinessCode;
import com.gpdi.hqplus.common.constants.RabbitMqQueue;
import com.gpdi.hqplus.common.constants.RedisConstants;
import com.gpdi.hqplus.common.entity.ProcessCallbackBO;
import com.gpdi.hqplus.common.entity.UserContext;
import com.gpdi.hqplus.common.entity.UserInfo;
import com.gpdi.hqplus.common.manager.RedisService;
import com.gpdi.hqplus.common.util.ExceptionUtil;
import com.gpdi.hqplus.pass.constans.ProductPassRabbitMqQueue;
import com.gpdi.hqplus.pass.service.IProductPassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author: zake
 * @Description:
 * @Date: Created in 15:22 2019-07-08
 **/

@Slf4j
@Component
public class ProductPassListener {

    @Autowired
    private RedisService redisService;
    @Autowired
    private IProductPassService passService;

    private final String businessCode = BusinessCode.PROPERTY_PASS;

    @RabbitListener(queues = ProductPassRabbitMqQueue.PROCESS_START_PASS_REVIEW)
    @RabbitHandler
    public void processStart(ProcessCallbackBO bo) {
        try {
            // 业务代码匹配
            if (!businessCode.equals(bo.getBusinessCode())) {
                return;
            }

            UserInfo userInfo = (UserInfo) redisService.get(RedisConstants.USER_INFO + bo.getUserRedisKey());
            userInfo.setProjectCode(bo.getProjectCode());
            UserContext.set(userInfo);

            passService.update(bo);
        } catch (Exception e) {
            log.error("process callback fail:{}", ExceptionUtil.getMassage(e));
        }
    }



    @RabbitListener(queues = RabbitMqQueue.PRODUCT_PASS_SUCCESS)
    @RabbitHandler
    public void productPassSuccess(HashMap map) {
        try {
//            passService.handUpdate(map);
        } catch (Exception e) {
            log.error("process callback fail:{}", ExceptionUtil.getMassage(e));
        }
    }

    @RabbitListener(queues = RabbitMqQueue.PRODUCT_PASS_FAIL)
    @RabbitHandler
    public void productPassFail(String productId) {
        try {
//            passService.handFail(productId);
        } catch (Exception e) {
            log.error("process callback fail:{}", ExceptionUtil.getMassage(e));
        }
    }
}
