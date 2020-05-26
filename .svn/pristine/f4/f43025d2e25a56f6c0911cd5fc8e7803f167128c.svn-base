package com.gpdi.hqplus.common.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 短信发送接口
 *
 * @author: lianghb
 * @create: 2019-06-10 23:05
 **/
@Slf4j
@Component
public class SmsService {

    @Autowired
    private RedisService redisService;

    /**
     * 验证手机验证码
     *
     * @param prefix redisKey 前缀，从RedisConstants常量类中取
     * @param phone  手机号码
     * @param code   用户输入的验证码
     * @return
     */
    public boolean checkPhoneCode(String prefix, String phone, String code) {
        String sources = (String) redisService.get(prefix + phone);
        return sources != null && sources.equals(code);
    }
}
