package com.gpdi.hqplus.pay.service;

import com.gpdi.hqplus.pay.entity.AlipayParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: zake
 * @Description:
 * @Date: Created in 20:50 2019-07-01
 **/
public interface IAlipayService {
    /**
      * 付款
      * @param alipayParam 付款参数
      * @return 付款返回值
      */
    String alipay(AlipayParam alipayParam);

    /**
      * 付款同步返回地址
      * @param request
      * @return
      */

    String synchronous(HttpServletRequest request);

    /**
      * 付款异步通知调用地址
      * @param request 新增参数
      * @return 新增返回值
      */
    void notify(HttpServletRequest request, HttpServletResponse response);

    /**
      * 统一收单线下交易查询 (暂时不返回 等与前端沟通)
      * @param
      * @return
      */
    String queryDeal();

    /**
      * 统一收单交易关闭
        用于交易创建后，用户在一定时间内未进行支付，可调用该接口直接将未付款的交易进行关闭。
      * @param
      * @return
      */
    String orderClose();


    /**
     * 查询对账单下载地址
     * @return url 是支付宝提供下载对账单的地址
     */
    String downloadQuery(String billType, String billDate);

}
