package com.gpdi.hqplus.pay.service.constans;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zake
 * @Description:
 * @Date: Created in 10:42 2019-07-14
 **/
@Configuration
public class PayRabbitMqConfig {
    /**
     * 微信支付生成预付单号
     */
    public static final String WECHAT_FOR_PREPARE_ID = "wechat_for_prepare_id";

    /**
     * 微信支付生成预付单号
     */
    public static final String WECHAT_FOR_CALLBACK= "wechat_for_callback";

    /**
     * 创建生成微信预付单号队列
     *
     * @return
     */
    @Bean
    public Queue weChatPrepareId() {
        return new Queue(WECHAT_FOR_PREPARE_ID);
    }

    /**
     * 创建微信回调队列
     *
     * @return
     */
    @Bean
    public Queue weChatCallback() {
        return new Queue(WECHAT_FOR_CALLBACK);
    }

}
