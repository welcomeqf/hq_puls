package com.gpdi.hqplus.message.util;

import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author: lianghb
 * @create: 2019-06-29 23:53
 **/
@Slf4j
@Component
public class TencentSmsUtil {

    /**
     * 短信应用SDK AppID
     */
    private int appId = 1400155188;

    /**
     * 短信应用SDK AppKey
     */
    private String appKey = "599e0bbfc0bb38d3392812c0d43ca74f";

    /**
     * 短信模板ID
     */
    private int templateId = 227821;

    /**
     * 签名(绑定的，无法更改)
     */
    private String smsSign = "二师时光机";


    /**
     * 发送短信
     *
     * @param phoneNumber 目标手机号码
     * @param msg         需要发送的信息
     * @return
     */
    public boolean sendMassage(String phoneNumber, String msg) {
        try {
            // 短信内容
            String[] params = {msg};

            // SDK AppID、SDK AppKey
            SmsSingleSender ssender = new SmsSingleSender(appId, appKey);

            // 发送短信
            // 签名参数未提供或者为空时，会使用默认签名发送短信
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber,
                    templateId, params, smsSign, "", "");

            //打印结果
            log.debug("Send Message to {}:{}", phoneNumber, result.errMsg);
            return true;
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
        return false;
    }
}

