package com.gpdi.hqplus.bill.util;

import com.gpdi.hqplus.bill.constants.PayTypeEnum;

/**
 * @author: lianghb
 * @create: 2019-07-09 16:08
 **/
public class PayTypeUtil {

    public static PayTypeEnum getByCode(String code) {
        PayTypeEnum[] values = PayTypeEnum.values();
        for (PayTypeEnum value : values) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
