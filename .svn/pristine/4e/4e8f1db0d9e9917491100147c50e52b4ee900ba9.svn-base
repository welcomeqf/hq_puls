package com.gpdi.hqplus.base.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author: zake
 * @Description:
 * @Date: Created in 17:40 2019-07-16
 **/
@Component
public class AckReceiverListener {

    /**
     * channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
     *  channel.basicNack 与channel.basicReject 的区别是basicReject 一次只能拒绝一条消息
     *  deliveryTag:该消息的index
     * requeue：被拒绝的是否重新入队列
     *
     * channel.basicNack(message.getMessageProperties().getDeliveryTag(),false, true);
     * deliveryTag:该消息的index
     * multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
     * requeue：被拒绝的是否重新入队列
     * @param content
     * @param channel
     * @param message
     */
    @RabbitHandler
    @RabbitListener(queues = "existQueue")
    public void process(String content, Channel channel, Message message) {
        try {
            System.out.println("########### message:" + message);
            // 业务处理成功后调用，消息会被确认消费
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            // 业务处理失败后调用
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("########### Receiver Msg:" + content);
    }
}
