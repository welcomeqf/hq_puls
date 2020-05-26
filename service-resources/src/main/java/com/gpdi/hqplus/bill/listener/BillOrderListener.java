package com.gpdi.hqplus.bill.listener;

import com.gpdi.hqplus.common.constants.BusinessCode;
import com.gpdi.hqplus.common.constants.RedisConstants;
import com.gpdi.hqplus.common.entity.ProcessCallbackBO;
import com.gpdi.hqplus.common.entity.UserContext;
import com.gpdi.hqplus.common.entity.UserInfo;
import com.gpdi.hqplus.common.manager.RedisService;
import com.gpdi.hqplus.common.util.ExceptionUtil;
import com.gpdi.hqplus.property.constants.PropertyRabbitMqQueue;
import com.gpdi.hqplus.property.service.IPropertyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 账单监听者
 * @author: lianghb
 * @create: 2019-07-04 10:15
 **/
@Slf4j
@Component
public class BillOrderListener {
    @Autowired
    private RedisService redisService;
    @Autowired
    private IPropertyService propertyService;

    private final String businessCode = BusinessCode.PROPERTY_MAINTENANCE;

    @RabbitListener(queues = PropertyRabbitMqQueue.QUEUE_PROPERTY_MAINTAIN_BUSINESS)
    public void processStart(ProcessCallbackBO bo) {
        try {
            // 业务代码匹配
            if (!businessCode.equals(bo.getBusinessCode())) {
                return;
            }

            UserInfo userInfo = (UserInfo) redisService.get(RedisConstants.USER_INFO + bo.getUserRedisKey());
            userInfo.setProjectCode(bo.getProjectCode());
            UserContext.set(userInfo);

            propertyService.update(bo);
        } catch (Exception e) {
            log.error("process callback fail:{}", ExceptionUtil.getMassage(e));
        }
    }
}
