package com.gpdi.hqplus.user.validate;

import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.StringUtil;
import com.gpdi.hqplus.user.entity.query.RegisterQuery;

/**
 * @author: lianghb
 * @create: 2019-07-02 10:27
 **/
public class UserValidate {
    private UserValidate() {
    }

    public static void checkRegister(RegisterQuery query) {
        checkAdd(query);

        boolean checkCode = StringUtil.isBlank(query.getSmsCode());
        if (checkCode) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "验证码不能为空");
        }
    }

    public static void checkAdd(RegisterQuery query) {
        if (query == null) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "请输入用户数据");
        }

        boolean checkPhone = StringUtil.regPhone(query.getPhone());
        if (!checkPhone) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "请输入正确的手机号码");
        }

        boolean checkUserName = StringUtil.isBlank(query.getUserName());
        if (checkUserName) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "请输入姓名");
        }
    }
}
