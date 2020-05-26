package com.gpdi.hqplus.property.constants;

/**
 * 物业报修流程状态
 * @author: lianghb
 * @create: 2019-07-03 14:28
 **/
public enum PropertyMaintainEnum {
    /**
     * 提交申请
     */
    APPLY("apply","提交申请","已提交申请，等待审核"),
    /**
     * 审核派单
     */
    ASSIGN("assign","审核派单","审核通过"),
    /**
     * 上门维修
     */
    MAINTAIN("maintain","上门维修","已派出维修师傅"),
    /**
     * 订单完成
     */
    FINISH("finish","订单完成","订单完成"),
    /**
     * 订单取消
     */
    CANCEL("cancel","订单取消","订单取消"),
    /**
     * 订单拒绝
     */
    FAIL("fail","订单拒绝","订单拒绝");


    private String status;
    private String name;
    private String msg;

    PropertyMaintainEnum(String status, String name, String msg) {
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
