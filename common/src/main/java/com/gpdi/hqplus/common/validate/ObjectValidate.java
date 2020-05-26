package com.gpdi.hqplus.common.validate;

import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;

/**
 * 验证类
 *
 * @author: lianghb
 * @create: 2019-06-17 17:09
 **/
public class ObjectValidate {
    private ObjectValidate() {
    }

    /**
     * 对象不为空
     *
     * @param val
     * @param msg
     */
    public static void requireNotNull(Object val, String msg) {
        requireNotNull(val, ErrorCode.SERVICE_ERROR, msg);
    }

    /**
     * 对象不为空
     *
     * @param val
     * @param errorCode
     */
    public static void requireNotNull(Object val, ErrorCode errorCode) {
        requireNotNull(val, errorCode, errorCode.getMsg());
    }

    /**
     * 对象不为空
     *
     * @param val
     * @param errorCode
     * @param msg
     */
    public static void requireNotNull(Object val, ErrorCode errorCode, String msg) {
        if (val != null) {
            return;
        }

        throw new ApplicationException(errorCode, msg);
    }

    /**
     * 对象为空
     *
     * @param val
     * @param msg
     */
    public static void requireNull(Object val, String msg) {
        requireNull(val, ErrorCode.SERVICE_ERROR, msg);
    }

    /**
     * 对象为空
     *
     * @param val
     * @param errorCode
     */
    public static void requireNull(Object val, ErrorCode errorCode) {
        requireNull(val, errorCode, errorCode.getMsg());
    }

    /**
     * 对象为空
     *
     * @param val
     * @param errorCode
     * @param msg
     */
    public static void requireNull(Object val, ErrorCode errorCode, String msg) {
        if (val == null) {
            return;
        }

        throw new ApplicationException(errorCode, msg);
    }


    /**
     * equals
     *
     * @param object
     * @param target
     * @param msg
     */
    public static void equals(Object object, Object target, String msg) {
        equals(object, target, ErrorCode.SERVICE_ERROR, msg);
    }

    /**
     * equals
     *
     * @param object
     * @param target
     * @param errorCode
     */
    public static void equals(Object object, Object target, ErrorCode errorCode) {
        equals(object, target, errorCode, errorCode.getMsg());
    }

    /**
     * equals
     *
     * @param object
     * @param target
     * @param errorCode
     * @param msg
     */
    public static void equals(Object object, Object target, ErrorCode errorCode, String msg) {
        requireNotNull(object, "对象不能为空");

        boolean equals = object.equals(target);
        if (equals) {
            return;
        }

        throw new ApplicationException(errorCode, msg);
    }

    /**
     * notEquals
     *
     * @param object
     * @param target
     * @param msg
     */
    public static void notEquals(Object object, Object target, String msg) {
        notEquals(object, target, ErrorCode.SERVICE_ERROR, msg);
    }

    /**
     * notEquals
     *
     * @param object
     * @param target
     * @param errorCode
     */
    public static void notEquals(Object object, Object target, ErrorCode errorCode) {
        notEquals(object, target, errorCode, errorCode.getMsg());
    }

    /**
     * notEquals
     *
     * @param object
     * @param target
     * @param errorCode
     * @param msg
     */
    public static void notEquals(Object object, Object target, ErrorCode errorCode, String msg) {
        requireNotNull(object, "对象不能为空");

        boolean notEquals = !object.equals(target);
        if (notEquals) {
            return;
        }

        throw new ApplicationException(errorCode, msg);
    }

}
