package com.gpdi.hqplus.message.service;

import com.gpdi.hqplus.message.util.TencentSmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 短信接口
 *
 * @author: lianghb
 * @create: 2019-07-02 15:03
 **/
public interface MessageService {
    /**
     * 发送短信
     * @param phone
     * @param code
     */
    void sendMessage(String prefix, String phone, String code);

    /**
     * 发送短信
     * @param phone
     */
    void sendMessage(String prefix, String phone);

    /**
     * 验证短信
     * @param prefix
     * @param phone
     * @param code
     */
    boolean checkCode(String prefix,String phone,String code);
}
