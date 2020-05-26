package com.gpdi.hqplus.water.utils;

import com.gpdi.hqplus.water.constants.WaterApplyEnum;

/**
 * 枚举工具类
 *
 * @author: liuJiaHui
 * @create: 2019-07-18 10:13
 **/
public class WaterApplyEnumUtil {

    private WaterApplyEnumUtil() {
    }

    public static WaterApplyEnum getByStatus(String val) {
        WaterApplyEnum[] values = WaterApplyEnum.values();
        for (WaterApplyEnum value : values) {
            if (value.getStatus().equals(val)) {
                return value;
            }
        }
        return null;
    }
}
