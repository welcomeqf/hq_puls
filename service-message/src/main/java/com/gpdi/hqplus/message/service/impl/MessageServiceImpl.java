package com.gpdi.hqplus.message.service.impl;

import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.manager.RedisService;
import com.gpdi.hqplus.common.util.StringUtil;
import com.gpdi.hqplus.message.service.MessageService;
import com.gpdi.hqplus.message.util.TencentSmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: lianghb
 * @create: 2019-07-02 15:06
 **/
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private TencentSmsUtil tencentSmsUtil;

    @Override
    public void sendMessage(String prefix, String phone, String code) {
        boolean set = redisService.set(prefix + phone, code, 1800);
        if (!set) {
            log.error("set redis val fail");
            throw new ApplicationException(ErrorCode.SERVICE_ERROR, "发送失败");
        }

        boolean sendMassage = tencentSmsUtil.sendMassage(phone, code);
        if (!sendMassage) {
            log.error("send message fail");
            throw new ApplicationException(ErrorCode.SERVICE_ERROR, "发送失败");
        }
    }

    @Override
    public void sendMessage(String prefix, String phone) {
        sendMessage(prefix, phone, StringUtil.getRandomInteger(6));
    }

    @Override
    public boolean checkCode(String prefix, String phone, String code) {
        String sources = (String) redisService.get(prefix + phone);
        return sources != null && sources.equals(code);
    }
}
