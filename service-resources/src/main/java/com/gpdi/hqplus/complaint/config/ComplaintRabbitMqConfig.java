package com.gpdi.hqplus.complaint.config;

import com.gpdi.hqplus.complaint.cnastants.ComplaintRabbitMqQueue;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mq的回调队列
 * @Author qf
 * @Date 2019/7/11
 * @Version 1.0
 */
@Configuration
public class ComplaintRabbitMqConfig {

    @Bean
    public Queue ComplaintMqCallback(){
        return new Queue(ComplaintRabbitMqQueue.QUEUE_COMPLAINT_HANDLE_BUSINESS);
    }


}
