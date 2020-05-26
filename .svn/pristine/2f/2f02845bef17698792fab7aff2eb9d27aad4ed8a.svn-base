package com.gpdi.hqplus.base.listener;

import com.gpdi.hqplus.common.constants.RabbitMqQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: zake
 * 交换机模式的监听器
 * @create: 2019-06-30 12:37
 **/
@Slf4j
@Component
public class ProcessExchangeListener {


    @RabbitHandler
    @RabbitListener(queues = RabbitMqQueue.PROCESS_START_EXCHANGE)
    public void processStartExchange(String msg) {
        log.info(msg);
        log.info("开始消费流程启动消息1");
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitMqQueue.PROCESS_CALLBACK_EXCHANGE)
    public void processCallbackExchange(String msg) {
        log.info(msg);
        log.info("开始消费流程回调消息2");
    }
}
