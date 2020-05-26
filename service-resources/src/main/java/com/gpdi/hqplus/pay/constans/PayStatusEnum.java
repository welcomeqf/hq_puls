package com.gpdi.hqplus.pay.constans;

/**
 * @author: zake
 * @Description:
 * @Date: Created in 16:33 2019-07-17
 **/
public enum PayStatusEnum {
    /**
     * 生成微信预付单id
     */
    PAY_STATUS_PAYMENT("pay_status_payment", "处理中", "处理中"),
    /**
     * 支付成功
     */
    PAY_STATUS_SUCCESS("pay_status_success", "支付成功", "支付成功"),
    /**
     * 支付失败
     */
    PAY_STATUS_FAIL("pay_status_fail", "支付失败", "支付失败"),
    /**
     * 支付关闭
     */
    PAY_STATUS_CLOSE("pay_status_close", "支付关闭", "支付关闭");



    private String status;
    private String name;
    private String msg;

    PayStatusEnum(String status, String name, String msg) {
        this.status = status;
        this.name = name;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
