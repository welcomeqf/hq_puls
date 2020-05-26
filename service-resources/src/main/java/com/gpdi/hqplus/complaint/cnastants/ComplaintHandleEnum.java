package com.gpdi.hqplus.complaint.cnastants;

/**
 * 投诉建议流程状态
 * @Author qf
 * @Date 2019/7/11
 * @Version 1.0
 */
public enum ComplaintHandleEnum {

    /**
     *提交申请：apply
     */
    APPLY("apply","提交申请","审核中"),

    /**
     * 提交反馈:feedback
     */
    FEEDBACK("feedback","提交反馈","反馈中"),


    /**
     * 业务部门处理：handle
     */
    HANDLE("handle","业务部门处理","受理中"),

    FINISH("finish","处理完成","已完成"),

    CANCEL("cancel","反馈取消","已取消");

    private String status;
    private String name;
    private String msg;

    ComplaintHandleEnum(String status, String name, String msg) {
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


