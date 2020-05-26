package com.gpdi.hqplus.common.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zake
 * @Description:
 * @Date: Created in 16:37 2019-07-15
 **/
@Configuration
public class RabbitConfig {
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback( new SendConfirmCallback());
        rabbitTemplate.setReturnCallback( new SendReturnCallback());
        return rabbitTemplate;
    }
}
