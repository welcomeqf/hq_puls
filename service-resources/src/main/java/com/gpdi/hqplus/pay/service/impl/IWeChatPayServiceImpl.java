package com.gpdi.hqplus.pay.service.impl;

import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.entity.UserContext;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.manager.RedisService;
import com.gpdi.hqplus.common.util.IdGenerator;
import com.gpdi.hqplus.common.validate.StringValidate;
import com.gpdi.hqplus.order.constants.OrderStatusEnum;
import com.gpdi.hqplus.order.entity.ProductOrder;
import com.gpdi.hqplus.order.service.IProductOrderService;
import com.gpdi.hqplus.pay.constans.PayStatusEnum;
import com.gpdi.hqplus.pay.entity.PaymentLog;
import com.gpdi.hqplus.pay.entity.WechatCreatePrepay;
import com.gpdi.hqplus.pay.entity.WechatPayVo;
import com.gpdi.hqplus.pay.entity.WepayBill;

import com.gpdi.hqplus.pay.service.IPaymentLogService;
import com.gpdi.hqplus.pay.service.IWeChatPayService;
import com.gpdi.hqplus.pay.service.IWepayBillService;
import com.gpdi.hqplus.pay.util.WxUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @author: zake
 * @Description:
 * @Date: Created in 10:31 2019-07-01
 **/
@SuppressWarnings("AlibabaRemoveCommentedCode")
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class IWeChatPayServiceImpl implements IWeChatPayService {

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private IWepayBillService billService;

    @Autowired
    private IPaymentLogService logService;

    @Autowired
    private IProductOrderService orderService;


    @Value("${wxpay.appid}")
    private String appId;

    @Value("${wxpay.mchid}")
    private String mchId;

    @Value("${wxpay.notify_url}")
    private String notifyUrl;

    @Value("${wxpay.key}")
    private String key;

    /**
     * 支付成功标识
     */
    public static final String Search_SUCCESS = "SUCCESS";
//    /**
//     * 支付失败标识
//     */
//    public static final String TRADE_CLOSED = "TRADE_CLOSED";
    /**
     * 下单 API 地址
     */
    private String placeUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * 查询订单 API 地址
     */
    private String searchUrl = "https://api.mch.weixin.qq.com/pay/orderquery";

    /**
     * 关闭订单 API 地址
     */
    private String closeUrl = "https://api.mch.weixin.qq.com/pay/closeorder";

    /**
     * 下载对账单 API 地址
     */
    private String downloadUrl = "https://api.mch.weixin.qq.com/pay/downloadbill";

    /**
     * 设备号 默认 "WEB"
     */
    private static final String deviceInfo = "WEB";

    /**
     * 交易类型
     */
    private static final String tradeType = "APP";

    /**
     * 分布式锁
     */
    private static final String WECHAT_PAY_REGISTER_LOCK = "WECHAT_PAY_REGISTER_LOCK";

    @Autowired
    private WxUtils wxUtils;

    @Autowired
    private RedisService redisService;

    @Override
    public String createOrder(WechatPayVo vo) throws IOException {
        // 获取分布式锁
        String lockKey = WECHAT_PAY_REGISTER_LOCK + vo.getOrderId();
        boolean lock = redisService.getLock(lockKey, 60);
        if (!lock) {
            throw new ApplicationException(ErrorCode.SERVICE_ERROR, "网络忙，请稍后再试");
        }
        try {
            String orderId = vo.getOrderId();
            BigDecimal price = getPrice(orderId);
            String body = vo.getBody();
            String ipAddress = vo.getIpAddress();
            SortedMap<String, Object> parameters = new TreeMap<String, Object>();
            parameters.put("appid", appId);
            parameters.put("mch_id", mchId);
            // 设备号 默认 "WEB"
            parameters.put("device_info", deviceInfo);
            parameters.put("body", body);
            // 32 位随机字符串
            parameters.put("nonce_str", wxUtils.gen32RandomString());
            //回调地址
            parameters.put("notify_url", notifyUrl);
            //商户订单号
            parameters.put("out_trade_no", orderId);
            //总金额
            parameters.put("total_fee", price.multiply(BigDecimal.valueOf(100)).intValue());
            // 测试时，将支付金额设置为 1 分钱
//            parameters.put("total_fee", 1);
            //终端ip
            parameters.put("spbill_create_ip", ipAddress);
            //交易类型
            parameters.put("trade_type", tradeType);
            // sign 必须在最后
            parameters.put("sign", wxUtils.createSign(parameters, key));
            WechatCreatePrepay prepay = new WechatCreatePrepay();
            long id = idGenerator.getNumberId();
            prepay.setId(id);
            prepay.setStatus("normal");
            prepay.setCreateTime(LocalDateTime.now());
            prepay.setAppId(appId);
            prepay.setMchId(mchId);
            prepay.setDeviceInfo("WEB");
            prepay.setBody(body);
            prepay.setNotifyUrl(notifyUrl);
            prepay.setSpbillCreateIp(ipAddress);
            prepay.setTradeType("APP");
            boolean prepayIn = prepay.insert();
            if (!prepayIn) {
                throw new RuntimeException("生成微信预付单插入失败");
            }
            // 执行 HTTP 请求，获取接收的字符串（一段 XML）
            String result = wxUtils.executeHttpPost(placeUrl, parameters);
            Map map = wxUtils.createSign2(result, key);
            String prepayid = (String) map.get("prepayid");
            PaymentLog log = new PaymentLog();
            log.setId(idGenerator.getNumberId());
            log.setStatus(PayStatusEnum.PAY_STATUS_PAYMENT.getStatus());
            log.setCreateTime(LocalDateTime.now());
            log.setUserId(UserContext.get().getId());
            log.setOrderId(orderId);
            log.setProductDescribe(body);
            log.setPrepareId(prepayid);
            log.setWechatPrepayId(id);
            log.setAmount(price);
            boolean insert = log.insert();
            if (!insert) {
                throw new RuntimeException("插入支付数据失败");
            }
            return prepayid;
        } finally {
            redisService.deleteLock(lockKey);
        }
    }


    @Override
    public String callBack(HashMap hashMap) throws Exception {
        HttpServletResponse response = (HttpServletResponse) hashMap.get("response");
        HttpServletRequest request = (HttpServletRequest) hashMap.get("request");
        // 预先设定返回的 response 类型为 xml
        response.setHeader("Content-type", "application/xml");
        // 读取参数，解析Xml为map
        Map<String, String> map = wxUtils.transferXmlToMap(wxUtils.readRequest(request));
        // 转换为有序 map，判断签名是否正确
        boolean isSignSuccess = wxUtils.checkSign(new TreeMap<String, Object>(map), key);
        if (isSignSuccess) {
            // 签名校验成功，说明是微信服务器发出的数据
            String orderId = map.get("out_trade_no");
            // 判断该订单是否已经被接收处理过
            PaymentLog log = logService.searctStatus(orderId);
            if (log == null) {
                return success();
            }
            log.setStatus(PayStatusEnum.PAY_STATUS_SUCCESS.getStatus());
            log.setUpdateTime(LocalDateTime.now());
            log.setApplyTime(LocalDateTime.now());
            return success();
        } else {
            // 签名校验失败（可能不是微信服务器发出的数据）
            return fail();
        }
    }

    @Override
    public Map searchOrder(String orderId) throws IOException {
        String result = getResponseResult(orderId, searchUrl);
        SortedMap<String, String> map = new TreeMap<>(wxUtils.transferXmlToMap(result));
        Map app = new HashMap();
        String returnCode = map.get("return_code");
        if (!returnCode.equals(Search_SUCCESS)) {
            String returnMsg = map.get("return_msg");
            app.put("returnMsg", returnMsg);
            return app;
        }

        String resultCode = map.get("result_code");
        if (!resultCode.equals(Search_SUCCESS)) {
            String errCodeDes = map.get("err_code_des");
            String errCode = map.get("err_code");
            app.put("errCodeDes", errCodeDes);
            app.put("errCode", errCode);
            return app;
        }

        String openid = map.get("openid");
        String tradeType = map.get("trade_type");
        String tradeState = map.get("trade_state");
        String bankType = map.get("bank_type");
        int totalFee = Integer.parseInt(map.get("total_fee"));
        int cashFee = Integer.parseInt(map.get("cash_fee"));
        String transactionId = map.get("transaction_id");
        String outTradeNo = map.get("out_trade_no");
        String timeEnd = map.get("time_end");
        String tradeStateDesc = map.get("trade_state_desc");
        app.put("returnCode", returnCode);
        app.put("resultCode", resultCode);
        app.put("openid", openid);
        app.put("tradeType", tradeType);
        app.put("tradeState", tradeState);
        app.put("bankType", bankType);
        app.put("totalFee", totalFee);
        app.put("cashFee", cashFee);
        app.put("transactionId", transactionId);
        app.put("outTradeNo", outTradeNo);
        app.put("timeEnd", timeEnd);
        app.put("tradeStateDesc", tradeStateDesc);
        return app;
    }


    @Override
    public Map closeOrder(String orderId) throws IOException {
        String result = getResponseResult(orderId, closeUrl);
        SortedMap<String, String> map = new TreeMap<>(wxUtils.transferXmlToMap(result));
        Map app = new HashMap();
        String returnCode = map.get("return_code");
        if (!returnCode.equals(Search_SUCCESS)) {
            String returnMsg = map.get("return_msg");
            app.put("returnMsg", returnMsg);
            return app;
        }

        app.put("appId", map.get("appid"));
        app.put("mchId", map.get("mch_id"));
        app.put("nonceStr", map.get("nonce_str"));
        app.put("sign", map.get("sign"));
        app.put("resultCode", map.get("result_code"));
        app.put("resultMsg", map.get("result_msg"));
        app.put("errCode", map.get("err_code"));
        app.put("errCodeDes", map.get("err_code_des"));

        return app;
    }


    @Override
    public Map downloadBill(String billDate) throws IOException {
        SortedMap<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("appid", appId);
        parameters.put("mch_id", mchId);
        // 32 位随机字符串
        String randomString = wxUtils.gen32RandomString();
        parameters.put("nonce_str", randomString);
        parameters.put("bill_date", billDate);
        /**
         * bill_type
         * ALL，返回当日所有订单信息，默认值
         *
         * SUCCESS，返回当日成功支付的订单
         *
         * REFUND，返回当日退款订单
         *
         * RECHARGE_REFUND，返回当日充值退款订单
         *
         */
        parameters.put("bill_type", "ALL");

        // sign 必须在最后
        String sign = wxUtils.createSign(parameters, key);
        parameters.put("sign", sign);
        // 执行 HTTP 请求，获取接收的字符串（一段 XML）
        String result = wxUtils.executeHttpPost(downloadUrl, parameters);

        String tradeMsg = result.substring(result.indexOf("`"));
        tradeMsg = tradeMsg.replaceAll("%,`0.01,`0.00,`", "%,");
        tradeMsg = tradeMsg.replaceAll("%,`0.00,`0.01,`", "%,");
        //总汇信息
        String sumMag = tradeMsg.substring(tradeMsg.lastIndexOf("%"), tradeMsg.length());
        tradeMsg = tradeMsg.substring(0, tradeMsg.lastIndexOf("%") + 1);
        tradeMsg = tradeMsg.replace("`", "");
        // 根据%来区分
        String[] tradeArray = tradeMsg.split("%");

        for (String tradeDetailInfo : tradeArray) {
            if (tradeDetailInfo.substring(0, 1).equals(",")) {
                tradeDetailInfo = tradeDetailInfo.substring(1);
            }
            String[] tradeDetailArray = tradeDetailInfo.split(",");
            WepayBill bill = new WepayBill();
            bill.setId(idGenerator.getNumberId());
            bill.setTransDate(tradeDetailArray[0]);
            bill.setCommonId(tradeDetailArray[1]);
            bill.setBusinessNo(tradeDetailArray[2]);
            bill.setChildBusinessNo(tradeDetailArray[3]);
            bill.setEquipmengNo(tradeDetailArray[4]);
            bill.setWxOrderNo(tradeDetailArray[5]);
            bill.setBusinessOrderNo(tradeDetailArray[6]);
            bill.setUserIdentity(tradeDetailArray[7]);
            bill.setTransType(tradeDetailArray[8]);
            bill.setTransStatus(tradeDetailArray[9]);
            bill.setPaymentBank(tradeDetailArray[10]);
            bill.setCurrency(tradeDetailArray[11]);
            bill.setTotalAmount(strToBigDecimal(tradeDetailArray[12]));
            bill.setRedEnvelopesAmount(strToBigDecimal(tradeDetailArray[13]));
            bill.setWxRefundNo(tradeDetailArray[14]);
            bill.setBusinessRefundNo(tradeDetailArray[15]);
            bill.setRefundAmount(strToBigDecimal(tradeDetailArray[16]));
            bill.setRedEnvelopeRefundAmount(strToBigDecimal(tradeDetailArray[17]));
            bill.setRefundType(tradeDetailArray[18]);
            bill.setRefundStatus(tradeDetailArray[19]);
            bill.setBusinessName(tradeDetailArray[20]);
            bill.setBusinessData(tradeDetailArray[21]);
            bill.setFee(strToBigDecimal(tradeDetailArray[22]));
            bill.setRate(strToBigDecimal(tradeDetailArray[23]));
            Date date = new Date();
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
            bill.setCreateTime(localDateTime);
            //将对账单的数据存入数据库
            billService.save(bill);
        }
        return null;
    }

    /**
     * 查询订单和关闭订单的返回结果
     *
     * @param orderId
     * @return
     */
    private String getResponseResult(String orderId, String url) throws IOException {
        SortedMap<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("appid", appId);
        parameters.put("mch_id", mchId);
        //商户订单号
        parameters.put("out_trade_no", orderId);
        // 32 位随机字符串
        String randomString = wxUtils.gen32RandomString();
        parameters.put("nonce_str", randomString);
        // sign 必须在最后
        String sign = wxUtils.createSign(parameters, key);
        parameters.put("sign", sign);
        // 执行 HTTP 请求，获取接收的字符串（一段 XML）
        String result = wxUtils.executeHttpPost(url, parameters);
        return result;
    }

    String fail() {
        return "<xml>\n" +
                "  <return_code><![CDATA[FAIL]]></return_code>\n" +
                "  <return_msg><![CDATA[]]></return_msg>\n" +
                "</xml>";
    }

    String success() {
        return "<xml>\n" +
                "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "  <return_msg><![CDATA[OK]]></return_msg>\n" +
                "</xml>";
    }

    /**
     * string 转成bigdecimal
     *
     * @param str
     * @return
     */
    private BigDecimal strToBigDecimal(String str) {
        BigDecimal bigDecimal = new BigDecimal(str);
        bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bigDecimal;
    }

    /**
     * 获取订单的金额
     *
     * @return
     */
    private BigDecimal getPrice(String orderId) {
        ProductOrder order = orderService.getByOrderCode(orderId);
        if (order == null) {
            throw new RuntimeException("订单不存在");
        }
        String status = order.getStatus();
        StringValidate.requireNotBlank(status, "订单状态不能为空");
        if (OrderStatusEnum.CREATE.getCode().equals(status)) {
            BigDecimal amount = order.getAmount();
            return amount;
        } else {
            throw new RuntimeException("订单状态错误");
        }
    }
}
