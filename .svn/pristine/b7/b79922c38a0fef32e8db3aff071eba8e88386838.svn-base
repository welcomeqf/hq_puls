package com.gpdi.hqplus.pass.util;

import com.gpdi.hqplus.pass.constans.ProductPassEnum;


/**
 * 枚举工具类
 *
 * @author: lianghb
 * @create: 2019-07-04 10:13
 **/
public class ProductPassEnumUtil {

    public static ProductPassEnum getByStatus(String val) {
        ProductPassEnum[] values = ProductPassEnum.values();
        for (ProductPassEnum value : values) {
            if (value.getStatus().equals(val)) {
                return value;
            }
        }
        return null;
    }
}
