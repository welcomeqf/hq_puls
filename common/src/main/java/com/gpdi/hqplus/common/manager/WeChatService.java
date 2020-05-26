package com.gpdi.hqplus.common.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 微信对接业务
 *
 * @author: lianghb
 * @create: 2019-06-13 11:47
 **/
@Slf4j
@Component
public class WeChatService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * code2Session 请求url
     * <p>
     * grant_type:授权类型，此处只需填写 authorization_code
     * <p>
     * 返回值：
     * openid	    string	    用户唯一标识
     * session_key	string	    会话密钥
     * unionid	    string	    用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
     * errcode	    number	    错误码
     * errmsg	    string	    错误信息
     * <p>
     * errcode 的合法值：
     * -1	    系统繁忙，此时请开发者稍候再试
     * 0	    请求成功
     * 40029	code 无效
     * 45011	频率限制，每个用户每分钟100次
     * <p>
     * 详情请见：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html
     */
    private String code2SessionUrl = "https://api.weixin.qq.com/sns/jscode2session?appid={1}&secret={2}&js_code={3}&grant_type=authorization_code";
    /**
     * 小程序 appId
     */
    private String appId = "";
    /**
     * 小程序 appSecret
     */
    private String secret = "";

    /**
     * 登录凭证校验。通过 wx.login 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程。更多使用方法详见 小程序登录。
     * wx.login https://developers.weixin.qq.com/miniprogram/dev/api/open-api/login/wx.login.html
     * 小程序登录 https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/login.html
     *
     * @param jsCode 登录时获取的 code
     */
    public void code2Session(String jsCode) {
        ResponseEntity<String> entity = restTemplate.getForEntity(code2SessionUrl, String.class, appId, secret, jsCode);
        String body = entity.getBody();
    }
}
