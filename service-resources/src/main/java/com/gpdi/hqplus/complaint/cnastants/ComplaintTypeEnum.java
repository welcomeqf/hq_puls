package com.gpdi.hqplus.complaint.cnastants;

/**
 * 用户端投诉类型
 * @Author qf
 * @Date 2019/7/20
 * @Version 1.0
 */
public enum ComplaintTypeEnum {


    PROPOSAL("proposal", "建议", "proposal"),

    CONSULTATION("consultation", "咨询", "consultation"),

    COMPLAINT("complaint", "投诉", "complaint");

    private String type;
    private String name;
    private String code;

    ComplaintTypeEnum(String type, String name, String code) {
        this.type = type;
        this.name = name;
        this.code = code;
    }


    public String getType() {
        return type;
    }

    public void setType(String status) {
        this.type = status;
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

    public void setCode(String msg) {
        this.code = msg;
    }
}
