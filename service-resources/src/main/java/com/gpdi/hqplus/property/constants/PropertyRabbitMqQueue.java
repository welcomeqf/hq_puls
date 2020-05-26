package com.gpdi.hqplus.property.constants;

import com.gpdi.hqplus.common.constants.RabbitMqQueue;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

/**
 * 消息队列
 * @author: lianghb
 * @create: 2019-07-04 14:10
 **/
public class PropertyRabbitMqQueue {
    /**
     * 物业报修
     */
    public static final String QUEUE_PROPERTY_MAINTAIN_BUSINESS = "queue_property_maintain_business";


}
