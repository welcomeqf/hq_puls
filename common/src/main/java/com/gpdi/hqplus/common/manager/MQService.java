package com.gpdi.hqplus.common.manager;

/**
 * @ClassName:IRabbitService
 * @Description:rabbitmq业务接口
 * @author:lianghb
 * @date:2019/1/31 13:38
 **/
public interface MQService {
    /**
     * 发送消息(默认直连交换机)
     * @param queueName 队列的名字
     * @param obj
     */
    boolean sendObject(String queueName, Object obj);

    /**
     * 发送消息(扇形交换机)
     * @param exchangeName 交换机的名字
     * @param obj
     */
    boolean sendMessage(String exchangeName,Object obj);

    /**
     * 发送消息(订阅交换机)
     * @param exchangeName 交换机的名字
     * @param queueName 队列的名字
     * @param obj
     */
    boolean sendTopicMessage(String exchangeName,String queueName, Object obj);
}
