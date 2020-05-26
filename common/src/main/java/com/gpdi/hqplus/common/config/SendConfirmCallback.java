package com.gpdi.hqplus.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;



/**
 * @author: zake
 * @Description: 消息发送到交换机监听类
 * @Date: Created in 15:17 2019-07-15
 **/
@Component
@Slf4j
public class SendConfirmCallback implements RabbitTemplate.ConfirmCallback {



    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
        if (ack) {
            log.info("Success... 消息成功发送到交换机! correlationData:{}", correlationData);
        } else {
            log.info("Fail... 消息发送到交换机失败! correlationData:{}", correlationData);
        }
    }
}
