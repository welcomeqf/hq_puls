package com.gpdi.hqplus.common.util;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

/**
 * @author: zake
 * @Description:
 * @Date: Created in 14:55 2019-07-16
 **/
public class ExpirationMessagePostProcessor implements MessagePostProcessor {
    //毫秒
    private final Long ttl;

    public ExpirationMessagePostProcessor(Long ttl) {
        this.ttl = ttl;
    }

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        /**
         *  设置per-message的失效时间
         */
        message.getMessageProperties()
                .setExpiration(ttl.toString());
        return message;
    }
}
