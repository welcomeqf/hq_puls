package com.gpdi.hqplus.pay.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.gpdi.hqplus.pay.entity.AlipayParam;
import com.gpdi.hqplus.pay.service.IAlipayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author: zake
 * @Description:
 * @Date: Created in 20:51 2019-07-01
 **/
@SuppressWarnings("AlibabaRemoveCommentedCode")
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class IAlipayServiceImpl implements IAlipayService {
    @Value("${alipay.aliReturnUrl}")
    private String aliReturnUrl;

    @Value("${alipay.aliNotifyUrl}")
    private String aliNotifyUrl;

    @Value("${alipay.timeExpress}")
    private String timeExpress;

    @Value("${alipay.url}")
    private String url;

    @Value("${alipay.productNo}")
    private String productNo;

    @Value("${alipay.appid}")
    private String appid;

    @Value("${alipay.privateKey}")
    private String privateKey;

    @Value("${alipay.aliPublicKey}")
    private String aliPublicKey;

    @Value("${alipay.signType}")
    private String signType;

    @Value("${alipay.charset}")
    private String charset;

    @Value("${alipay.format}")
    private String format;
    /**
     * 支付成功标识
     */
    public static final String TRADE_SUCCESS = "TRADE_SUCCESS";
    /**
     * 交易关闭
     */
    public static final String TRADE_CLOSED = "TRADE_CLOSED";


    @Override
    public String alipay(AlipayParam alipayParam) {
        //订单号
        String merchantOrderNo = "";
        //公共校验参数 多加的一个公共参数校验 在异步通知的时候 与request中取出的进行对比
        String urlEncodeOrderNum = "";

        try {
            urlEncodeOrderNum = URLEncoder.encode(merchantOrderNo, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        AlipayClient alipayClient = new DefaultAlipayClient(url, appid
                , privateKey, format, charset
                , aliPublicKey, signType);
        //创建Alipay支付请求对象
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setReturnUrl(aliReturnUrl);
        request.setNotifyUrl(aliNotifyUrl);
        AlipayTradeAppPayModel payModel = new AlipayTradeAppPayModel();
        payModel.setBody("我是测试数据");
        payModel.setSubject("App支付测试java");
        payModel.setOutTradeNo(merchantOrderNo);
        payModel.setTimeoutExpress(timeExpress);
        payModel.setTotalAmount("0.01");
        payModel.setProductCode(productNo);
        payModel.setPassbackParams(urlEncodeOrderNum);
        //设置参数
        request.setBizContent(JSON.toJSONString(payModel));
        String result = "";
        try {
            //调用sdk
            result = alipayClient.pageExecute(request).getBody();
            return result;
        } catch (Exception e) {
            log.info("支付请求发送失败");
            return null;
        }
    }

    @Override
    public String synchronous(HttpServletRequest request){
        getAilpayResponse(request);
        //商户订单号
        String outTradeNo = request.getParameter("out_trade_no");
        //记录日志
        log.info("支付成功, 进入同步通知接口...");


        return null;
    }

    @Override
    public void notify(HttpServletRequest request, HttpServletResponse response) {
        Map params = getAilpayResponse(request);
        //appId
        String appId = request.getParameter("app_id");
        //商户订单号
        String outTradeNo = request.getParameter("out_trade_no");
        log.info(outTradeNo);
        //交易转态
        String status = request.getParameter("trade_status");


        String encodeOrderNum = null;

        try {

            encodeOrderNum = URLDecoder.decode(request.getParameter("passback_params"), "UTF-8");
            log.info("encodeOrderNum is [encodeOrderNum={}]", encodeOrderNum);


            //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
            //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey,              String charset, String sign_type)
            boolean flag = AlipaySignature.rsaCheckV1(params, aliPublicKey, charset, signType);
            log.info("flag is [flag={}]", flag);
            if (flag) {
                //通过验证
                log.info("status: {}", status);
                if (status.equals(TRADE_SUCCESS)) {
                    //判断订单号与公共校验参数是否一致
                    if (outTradeNo.equals(encodeOrderNum) == false || appid.equals(appId)) {
                        log.info("vail failure");
                        //更新记账单

                        response.getOutputStream().print("failure");
                        return;
                    }
                    //交易成功的业务逻辑


                    response.getOutputStream().print("success");
                    return;
                } else if (status.equals(TRADE_CLOSED)) {
                    //交易关闭(更新记账单)

                } else {
                    //更新记账单

                    response.getOutputStream().print("failure");
                    return;
                }
            }else{
                //签名校验失败更新记账单
                response.getOutputStream().print("failure");
                return;
            }
            log.info("encodeOrderNum is  [encodeOrderNum={}]", encodeOrderNum);
            //更新

            response.getOutputStream().print("failure");
            return;
        } catch (AlipayApiException e) {
            log.info(e.getErrMsg());
            throw new RuntimeException("调用支付宝接口异常");
        }catch (UnsupportedEncodingException e){
            log.info(e.getMessage());
            throw new RuntimeException("URLDecoder发生异常");
        } catch (IOException e){
            log.info(e.getMessage());
            throw new RuntimeException("IO发生异常");
        }
    }

    @Override
    public String queryDeal() {

        AlipayClient alipayClient = new DefaultAlipayClient(url, appid
                , privateKey, format, charset
                , aliPublicKey, signType);
        //订单号
        String merchantOrderNo = "";
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        //支付宝交易流水号 与 订单号二选一 优先选择交易流水号
        model.setTradeNo("");
        model.setOutTradeNo(merchantOrderNo);
        //设置参数
        request.setBizContent(JSON.toJSONString(model));
        String result = "";
        try {
            //调用sdk
            result = alipayClient.pageExecute(request).getBody();
            return result;
        } catch (Exception e) {
            log.info("查询订单请求失败");
            return null;
        }
    }

    @Override
    public String orderClose() {
        AlipayClient alipayClient = new DefaultAlipayClient(url, appid
                , privateKey, format, charset
                , aliPublicKey, signType);
        //订单号
        String merchantOrderNo = "";

        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        AlipayTradeCloseModel closeModel = new AlipayTradeCloseModel();
        //支付宝交易流水号 与 订单号二选一 优先选择交易流水号
        closeModel.setTradeNo("");
        closeModel.setOutTradeNo(merchantOrderNo);
        //设置参数
        request.setBizContent(JSON.toJSONString(closeModel));
        try {
            //调用sdk
            AlipayTradeCloseResponse response = alipayClient.execute(request);
            if (response.isSuccess()){
                return "订单关闭成功";
            }else {
                return "订单关闭失败";
            }

        } catch (Exception e) {
            log.info("关闭订单请求发送失败");
            return null;
        }
    }



    /**
     * bill_type 账单类型，商户通过接口或商户经开放平台授权后其所属服务商通过接口可以获取以下账单类型：trade、signcustomer；
     * trade指商户基于支付宝交易收单的业务账单；
     * signcustomer是指基于商户支付宝余额收入及支出等资金变动的帐务账单；
     *
     * bill_date 账单时间：日账单格式为yyyy-MM-dd，月账单格式为yyyy-MM。2016-04-05
     * @return
     */
    @Override
    public String downloadQuery(String billType, String billDate) {
        AlipayClient alipayClient = new DefaultAlipayClient(url, appid
                , privateKey, format, charset
                , aliPublicKey, signType);
        AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
        AlipayDataDataserviceBillDownloadurlQueryModel queryModel = new AlipayDataDataserviceBillDownloadurlQueryModel();
        queryModel.setBillType(billType);
        queryModel.setBillDate(billDate);
        //设置参数
        request.setBizContent(JSON.toJSONString(queryModel));
        String result = "";
        try {
            //调用sdk
            AlipayDataDataserviceBillDownloadurlQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()){
                String downloadUrl = response.getBillDownloadUrl();
                return downloadUrl;
            }
            return response.getSubMsg();
        } catch (Exception e) {
            log.info("下载对账单接口发送失败");
            throw new RuntimeException("下载对账单接口发送失败");
        }

    }

    private Map getAilpayResponse(HttpServletRequest request){
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        log.info("params is [params={}]", params);
        return params;
    }

}
