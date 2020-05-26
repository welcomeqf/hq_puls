package com.gpdi.hqplus.common.validate;

import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.StringUtil;

/**
 * 字符串验证类
 *
 * @author: lianghb
 * @create: 2019-06-04 11:28
 **/
public class StringValidate {

    public static final String REG_EMAIL= "/^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$/";

    private StringValidate() {
    }

    /**
     * 字符串非空校验
     *
     * @param val 值
     * @param msg 错误信息
     */
    public static void requireNotBlank(String val, String msg) {
        boolean result = StringUtil.isNotBlank(val);
        if (result) {
            return;
        }

        throw new ApplicationException(ErrorCode.SERVICE_ERROR, msg);
    }
}
