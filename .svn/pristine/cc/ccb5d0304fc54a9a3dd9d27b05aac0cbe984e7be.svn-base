package com.gpdi.hqplus.pass.constans;
import com.gpdi.hqplus.common.constants.RabbitMqQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author: zake
 * @Description: 消息队列
 * @Date: Created in 15:39 2019-07-08
 **/
@Configuration
@Slf4j
public class ProductPassRabbitMqQueue {

    /**
     * 物品放行审批
     */
    public static final String PROCESS_START_PASS_REVIEW = "process_start_pass_review_test";


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
     * RabbitMQ:物品放行回调
     *
     * @return
     */
    @Bean
    public Queue productPassStart() {
        log.info("process_start_pass_review"+"创建新的消息队列");
        return new Queue(PROCESS_START_PASS_REVIEW);
    }

    /**
     * RabbitMQ:流程启动OFFICE
     *
     * @return
     */
    @Bean
    public Queue productApartmentStart() {

        return new Queue(RabbitMqQueue.PROCESS_START_ADDRESS);
    }

    /**
     * RabbitMQ:物品放行审核() 启动activiti之后需要删除
     *
     * @return
     */
    @Bean
    public Queue productPassSuccess() {
        return new Queue(RabbitMqQueue.PRODUCT_PASS_SUCCESS);
    }

    /**
     * RabbitMQ:物品放行审核失败() 启动activiti之后需要删除
     *
     * @return
     */
    @Bean
    public Queue productPassFail() {
        return new Queue(RabbitMqQueue.PRODUCT_PASS_FAIL);
    }

}
