package com.gpdi.hqplus.pay.util;

import com.gpdi.hqplus.property.constants.PropertyMaintainEnum;

/**
 * 枚举工具类
 *
 * @author: lianghb
 * @create: 2019-07-04 10:13
 **/
public class PropertyEnumUtil {

    public static PropertyMaintainEnum getByStatus(String val) {
        PropertyMaintainEnum[] values = PropertyMaintainEnum.values();
        for (PropertyMaintainEnum value : values) {
            if (value.getStatus().equals(val)) {
                return value;
            }
        }
        return null;
    }
}
