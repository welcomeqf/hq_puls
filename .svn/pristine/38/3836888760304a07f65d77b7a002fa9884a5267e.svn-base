package com.gpdi.hqplus.water.constants;

/**
 * 水电开通申请流程状态
 * @author: liuJiaHui
 * @create: 2019-07-18 14:28
 **/
public enum WaterApplyEnum {
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
    CANCEL("cancel","订单取消","订单取消");


    private String status;
    private String name;
    private String msg;

    WaterApplyEnum(String status, String name, String msg) {
        this.status = status;
        this.name = name;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }


    public String getName() {
        return name;
    }


    public String getMsg() {
        return msg;
    }

}
