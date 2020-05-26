package com.gpdi.hqplus.common.constants;

/**
 * 全局错误类型枚举
 *
 * @author: lianghb
 * @create: 2019-06-03 15:51
 **/
public enum ErrorCode {

    /**
     * 请求成功（作为表示，请勿使用）
     */
    SUCCESS(0, "请求成功", 200),
    /**
     * 未知错误
     */
    UNKNOWN_ERROR(999, "未知错误", 500),
    /**
     * 身份认证错误
     */
    AUTHENTICATION_ERROR(1000, "身份认证错误", 403),
    /**
     * 账号或密码错误
     */
    LOGIN_ERROR(1001, "账号或密码错误", 500),
    /**
     * 参数错误
     */
    PARAMETER_ERROR(1002, "参数错误", 500),
    /**
     * 资源未找到
     */
    RESOURCES_NOT_FIND(1003, "资源未找到", 500),
    /**
     * 资源已存在
     */
    RESOURCES_EXISTING(1004, "资源已存在", 500),
    /**
     * 验证码错误
     */
    SMS_CODE_ERROR(1005, "验证码错误", 500),
    /**
     * 业务异常
     */
    SERVICE_ERROR(1006, "业务异常", 500),
    /**
     * feign 调用异常
     */
    FEIGN_CONNECT_ERROR(1007, "网络忙，请稍后再试", 500),
    /**
     * 数据库操作失败
     */
    DATABASE_ERROR(1008, "网络忙，请稍后再试", 500)
    ;

    private int code;
    private String msg;
    private int httpStatus;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.httpStatus = 500;
    }

    ErrorCode(int code, String msg, int httpStatus) {
        this.code = code;
        this.msg = msg;
        this.httpStatus = httpStatus;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }
}
