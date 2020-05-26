package com.gpdi.hqplus.process.config;

import com.gpdi.hqplus.common.constants.RabbitMqQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: lianghb
 * 创建rabbitmq队列
 * @create: 2019-07-03 18:07
 **/
@Configuration
@Slf4j
public class RabbitQueueConfig {
    /**
     * RabbitMQ:流程启动
     *
     * @return
     */
    @Bean
    public Queue processStart() {

        return new Queue(RabbitMqQueue.PROCESS_START);
    }


    /**
     * RabbitMQ:流程启动 之后要删除
     *
     * @return
     */
    @Bean
    public Queue productApartmentStart() {

        return new Queue(RabbitMqQueue.PROCESS_START_ADDRESS);
    }


    /**
     * RabbitMQ:流程回调
     *
     * @return
     */
    @Bean
    public Queue processCallback() {
        return new Queue(RabbitMqQueue.PROCESS_CALLBACK);
    }


}
