package com.gpdi.hqplus.pass.constans;

/**
 * 物业报修流程状态
 *
 * @author: lianghb
 * @create: 2019-07-03 14:28
 **/
public enum ProductPassEnum {
    /**
     * 提交申请
     */
    STATUS_REVIEW("review", "申请中", "已提交申请，等待审核"),
    /**
     * 写字楼(客服审批状态
     */
    ADDRESS_OFFICE_CUSTOMER("address_office_customer", "客服审批完成", "审核中"),
    /**
     * 客服主管审批转态
     */
    ADDRESS_OFFICE_SUPERVISOR("address_office_supervisor", "客服主管审批完成", "客服主管审批完成"),
    /**
     * 管家审批状态
     */
    ADDRESS_APARTMENT_HOUSEKEEPER("address_apartment_housekeeper", "管家审批完成", "管家审批状态完成"),
    /**
     * 审批放行()保安
     */
    STATUS_POLICEMEN("status_policemen", "保安放行", "审核完成"),

    /**
     * 申请取消
     */
    STATUS_CANCEL("status_cancel","申请取消","申请取消"),

    /**
     * 已失效
     */
    STATUS_LOSE("status_lose","已失效","已失效"),

    /**
     * 审批失败
     */
    STATUS_FAIL("status_fail","失败","失败");


    private String status;
    private String name;
    private String msg;

    ProductPassEnum(String status, String name, String msg) {
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
