package com.gpdi.hqplus.push.util;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author ：lianghb
 * @date ：Created in 2019/4/2 10:24
 */
@Component
public class JiGuangPushUtil {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Value("${push.appKey}")
    private String appKey;
    @Value("${push.masterSecret}")
    private String masterSecret;

    public PushPayload buildPushObjectAndroid(List<String> alias, String alert, String type) {
        return PushPayload.newBuilder()
                //指定要推送的平台,all代表当前应用配置了所有平台,也可以传安卓等具体平台
                .setPlatform(Platform.android())
                //指定推送的对象,all代表所有人,也可以指定以及设置成功的tag或alias或该应用客户端调用接口获取到的registration id
                .setAudience(Audience.registrationId(alias))
                //jpush通知,android的由jpush直接下发,ios的由apns服务器下发,Winphones的由mpns下发
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .addExtra("type", type)
                                .setAlert(alert)
                                .build())
                        .build())
                .setOptions(Options.newBuilder()
                        //true-推送生产环境 false-推送开发环境（测试使用参数）
                        .setApnsProduction(false)
                        //消息在JPush服务器的失效时间（测试使用参数）
                        .setTimeToLive(90)
                        .build())
                .build();
    }


    public PushPayload buildPushObjectIOS(List<String> alias, String alert, String type) {
        return PushPayload.newBuilder()
                //指定要推送的平台,all代表当前应用配置了所有平台,也可以传安卓等具体平台
                .setPlatform(Platform.ios())
                //指定推送的对象,all代表所有人,也可以指定以及设置成功的tag或alias或该应用客户端调用接口获取到的registration id
                .setAudience(Audience.registrationId(alias))
                //jpush通知,android的由jpush直接下发,ios的由apns服务器下发,Winphones的由mpns下发
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .addExtra("type", type)
                                .setAlert(alert)
                                .build())
                        .build())
                .setOptions(Options.newBuilder()
                        //true-推送生产环境 false-推送开发环境（测试使用参数）
                        .setApnsProduction(true)
                        //消息在JPush服务器的失效时间（测试使用参数）
                        .setTimeToLive(90)
                        .build())
                .build();
    }

    /**
     * 极光推送方法
     */
    public PushResult pushIOS(List<String> alias, String alert, String type) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, clientConfig);
        PushPayload payload = buildPushObjectIOS(alias, alert, type);
        log.info("进行ios推送");
        return getResult(jpushClient, payload);
    }

    public PushResult pushAndroid(List<String> alias, String alert, String type) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, clientConfig);
        PushPayload payload = buildPushObjectAndroid(alias, alert, type);
        log.info("进行android推送");
        return getResult(jpushClient, payload);
    }

    public PushResult getResult(JPushClient jPushClient, PushPayload payload) {
        try {
            return jPushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            log.error("Connection error. Should retry later. ", e);
            return null;
        } catch (APIRequestException e) {
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
            log.info("Msg ID: " + e.getMsgId());
            return null;
        }
    }
}


