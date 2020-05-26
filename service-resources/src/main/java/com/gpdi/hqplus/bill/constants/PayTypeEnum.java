package com.gpdi.hqplus.bill.constants;

/**
 * @author: lianghb
 * @create: 2019-07-09 16:06
 **/
public enum PayTypeEnum {

    /**
     * 一次性支付
     */
    ONCE("一次支付", "pay_type_once");

    private String name;
    private String code;

    PayTypeEnum(String name, String code) {
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
