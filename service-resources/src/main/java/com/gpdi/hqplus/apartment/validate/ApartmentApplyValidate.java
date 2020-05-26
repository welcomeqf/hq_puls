package com.gpdi.hqplus.apartment.validate;

import com.gpdi.hqplus.apartment.entity.ApartmentApply;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;

/**
 * @author: lianghb
 * @create: 2019-07-21 15:02
 **/
public class ApartmentApplyValidate {
    private ApartmentApplyValidate() {
    }

    public static void checkApply(ApartmentApply apply) {
        if (apply == null) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "请求不能为空");
        }
        if (apply.getSeriesId() == null) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "系列不能为空");
        }
        if (apply.getRoomId() == null) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "房型不能为空");
        }
        if (apply.getUserName() == null) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "用户名不能为空");
        }
        if (apply.getUserConnect() == null) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "联系方式不能为空");
        }
        if (apply.getApplyDate() == null) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "预约时间不能为空");
        }
    }
}
