package com.gpdi.hqplus.common.constants;

/**
 * @author: lianghb
 * @create: 2019-07-13 10:58
 **/
public enum PriceType {
    /**
     * 一次性
     */
    ONCE("一次性","pay_type_once");

    private String name;
    private String code;

    PriceType(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
