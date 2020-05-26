package com.gpdi.hqplus.order.constants;

/**
 * @author: lianghb
 * @create: 2019-07-08 15:21
 **/
public enum OrderStatusEnum {

    /**
     * 创建订单
     */
    CREATE("create", "创建订单", "待支付"),

    /**
     * 支付订单
     */
    PAID("paid", "支付订单", "已支付"),

    /**
     * 取消
     */
    CANCEL("cancel", "取消", "已取消"),

    /**
     * 完成
     */
    FINISH("finish", "完成", "已完成");

    private String code;
    private String name;
    private String context;

    OrderStatusEnum(String code, String name, String context) {
        this.code = code;
        this.name = name;
        this.context = context;
    }

    OrderStatusEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
