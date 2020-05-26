package com.gpdi.hqplus.complaint.util;

import com.gpdi.hqplus.complaint.cnastants.ComplaintHandleEnum;

/**
 * @Author qf
 * @Date 2019/7/12
 * @Version 1.0
 */
public class ComplaintEnumUtil {

    public static ComplaintHandleEnum getByStatus(String val) {
        ComplaintHandleEnum[] values = ComplaintHandleEnum.values();
        for (ComplaintHandleEnum value : values) {
            if (value.getStatus().equals(val)) {
                return value;
            }
        }
        return null;
    }


}
