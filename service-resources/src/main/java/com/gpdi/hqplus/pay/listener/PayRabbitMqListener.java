package com.gpdi.hqplus.pay.listener;

import com.gpdi.hqplus.common.util.ExceptionUtil;
import com.gpdi.hqplus.pay.entity.WechatPayVo;
import com.gpdi.hqplus.pay.service.IWeChatPayService;
import com.gpdi.hqplus.pay.service.constans.PayRabbitMqConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zake
 * @Description:
 * @Date: Created in 11:34 2019-07-14
 **/
@Slf4j
@Component
public class PayRabbitMqListener {

    @Autowired
    private IWeChatPayService payService;

    @RabbitListener(queues = PayRabbitMqConfig.WECHAT_FOR_PREPARE_ID)
    @RabbitHandler
    private void toWechatPrepareId(WechatPayVo vo){
        try {
//            payService.createOrder(vo);
        } catch (Exception e) {
            log.error("process callback fail:{}", ExceptionUtil.getMassage(e));
        }
    }

    @RabbitListener(queues = PayRabbitMqConfig.WECHAT_FOR_CALLBACK)
    @RabbitHandler
    private void toCallBack(HashMap map){
        try {
            payService.callBack(map);
        } catch (Exception e) {
            log.error("process callback fail:{}", ExceptionUtil.getMassage(e));
        }
    }
}
