package com.gpdi.hqplus.pay.controller.alipay;

import com.gpdi.hqplus.pay.entity.AlipayParam;
import com.gpdi.hqplus.pay.service.IAlipayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author: zake
 * @Description:
 * @Date: Created in 19:00 2019-07-01
 **/
@Controller
@Api(tags = "支付宝")
@Slf4j
@RequestMapping("/v1/app/payment")
public class AlipayController {

    @Autowired
    private IAlipayService IAlipayService;

    @ApiOperation(value = "支付宝支付接口", notes = "支付宝支付接口")
    @RequestMapping(value = "/alipay/order", method = RequestMethod.POST)
    public String alipay(@Valid AlipayParam alipayParam, BindingResult result) {
        return IAlipayService.alipay(alipayParam);
    }

    @ApiOperation(value = "支付宝同步回调接口", notes = "支付宝同步回调接口")
    @RequestMapping(value = "/alipay/getSynchronous" , method = RequestMethod.POST)
    public String alipayReturnUrlInfo(HttpServletRequest request) {

        return IAlipayService.synchronous(request);
    }

    @ApiOperation(value = "支付宝异步回调接口", notes = "支付宝异步回调接口")
    @RequestMapping(value = "/alipay/getNotify", method = RequestMethod.POST)
    public void alipayNotifyUrlInfo(HttpServletRequest request, HttpServletResponse response) {
        IAlipayService.notify(request, response);
    }

}
