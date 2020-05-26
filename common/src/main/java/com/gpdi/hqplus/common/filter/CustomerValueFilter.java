package com.gpdi.hqplus.common.filter;

import com.alibaba.fastjson.serializer.ValueFilter;

import java.math.BigDecimal;

/**
 * @author: lianghb
 * @create: 2019-07-10 15:33
 **/
public class CustomerValueFilter implements ValueFilter {

    @Override
    public Object process(Object object, String name, Object value) {
        // long 转换为 string
        if (value instanceof Long) {
            return value.toString();
        }

        // bigDecimal 保留两位小数
        if (value instanceof BigDecimal) {
            return String.format("%.2f", ((BigDecimal) value).doubleValue());
        }

        return value;
    }
}
