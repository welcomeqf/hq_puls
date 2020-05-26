package com.gpdi.hqplus.pay.controller.wechat;

import com.gpdi.hqplus.common.manager.MQService;
import com.gpdi.hqplus.pay.service.IWeChatPayService;

import com.gpdi.hqplus.pay.service.constans.PayRabbitMqConfig;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author: zake
 * @Description:
 * @Date: Created in 10:51 2019-07-01
 **/

@Controller
@Slf4j
@RequestMapping("/v1/app/payment")
public class PayCallBackController {


    @Autowired
    private MQService mqService;

    @ApiOperation(value = "微信回调接口", notes = "微信回调接口")
    @RequestMapping(value = "/wxpay/notify", method = {RequestMethod.POST, RequestMethod.GET})
    public String wxNotify(HttpServletRequest request, HttpServletResponse response) {
        log.info("微信回调接口" + "===============");
        try {
            HashMap map = new HashMap(16);
            map.put("request", request);
            map.put("response", response);
            mqService.sendObject(PayRabbitMqConfig.WECHAT_FOR_CALLBACK, map);
        } catch (Exception e) {
            response.setHeader("Content-type", "application/xml");
            return "<xml>\n" +
                    "  <return_code><![CDATA[FAIL]]></return_code>\n" +
                    "  <return_msg><![CDATA[]]></return_msg>\n" +
                    "</xml>";
        }
        return "success";
    }
}
