package com.gpdi.hqplus.common.manager.impl;

import com.gpdi.hqplus.common.manager.MQService;
import com.gpdi.hqplus.common.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: lianghb
 * @create: 2019-06-30 11:35
 **/
@Slf4j
@Service
public class RabbitMqServiceImpl implements MQService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public boolean sendObject(String queueName, Object obj) {
        try {
            rabbitTemplate.convertAndSend(queueName,obj);
            return true;
        }catch (Exception e){
            log.error(ExceptionUtil.getMassage(e));
        }

        return false;
    }

    @Override
    public boolean sendMessage(String exchangeName,Object message) {
        try {
            log.info("消息发送到交换机");
            rabbitTemplate.convertAndSend(exchangeName,"",message);
            return true;
        }catch (Exception e){
            log.error(ExceptionUtil.getMassage(e));
        }

        return false;
    }

    @Override
    public boolean sendTopicMessage(String exchangeName, String queueName, Object obj) {
        try {
            log.info("订阅模式消息发送到交换机");
            rabbitTemplate.convertAndSend(exchangeName,queueName,obj);
            return true;
        }catch (Exception e){
            log.error(ExceptionUtil.getMassage(e));
        }
        return false;
    }
}
