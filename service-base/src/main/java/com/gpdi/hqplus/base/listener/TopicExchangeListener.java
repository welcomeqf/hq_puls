package com.gpdi.hqplus.base.listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: zake
 * @Description:
 * @Date: Created in 02:08 2019-07-05
 **/
@Slf4j
@Component
public class TopicExchangeListener {


    @RabbitListener(queues = "#{autoDeleteQueOne.name}")
    public void processStartExchange(String msg) {
        log.info(msg);
    }


    @RabbitListener(queues = "#{autoDeleteQueTWO.name}")
    public void processStartExchangeN(String msg) {
        log.info(msg);
    }
}
