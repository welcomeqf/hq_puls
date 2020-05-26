package com.gpdi.hqplus.common.exception;

import com.gpdi.hqplus.common.constants.ErrorCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: lianghb
 * @create: 2019-06-03 15:46
 **/
@Data
public class ApplicationException extends RuntimeException implements Serializable {
    private String msg;
    private int code;
    private int httpStatus;

    public ApplicationException() {
        super();
    }

    /**
     * 使用 errorCode 中的默认信息返回
     *
     * @param errorCode
     */
    public ApplicationException(ErrorCode errorCode) {
        this.msg = errorCode.getMsg();
        this.code = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
    }

    /**
     * 自定义返回信息
     *
     * @param errorCode
     * @param msg
     */
    public ApplicationException(ErrorCode errorCode, String msg) {
        this.msg = msg;
        this.code = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
    }
}
