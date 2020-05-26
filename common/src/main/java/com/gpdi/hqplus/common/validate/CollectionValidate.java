package com.gpdi.hqplus.common.validate;

import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;

import java.util.Collection;

/**
 * @author: lianghb
 * @create: 2019-06-20 15:30
 **/
public class CollectionValidate {
    private CollectionValidate() {
    }

    /**
     * 集合不为空
     *
     * @param collection
     * @param msg
     */
    public static void requireNotEmpty(Collection collection, String msg) {
        requireNotEmpty(collection, ErrorCode.SERVICE_ERROR, msg);
    }

    /**
     * 集合不为空
     *
     * @param collection
     * @param errorCode
     */
    public static void requireNotEmpty(Collection collection, ErrorCode errorCode) {
        requireNotEmpty(collection, errorCode, errorCode.getMsg());
    }

    /**
     * 集合不为空
     *
     * @param collection
     * @param errorCode
     * @param msg
     */
    public static void requireNotEmpty(Collection collection, ErrorCode errorCode, String msg) {
        if (collection != null && collection.size() > 0) {
            return;
        }

        throw new ApplicationException(errorCode, msg);
    }

    /**
     * 集合为空
     *
     * @param collection
     * @param msg
     */
    public static void requireEmpty(Collection collection, String msg) {
        requireEmpty(collection, ErrorCode.SERVICE_ERROR, msg);
    }

    /**
     * 集合为空
     *
     * @param collection
     * @param errorCode
     */
    public static void requireEmpty(Collection collection, ErrorCode errorCode) {
        requireEmpty(collection, errorCode, errorCode.getMsg());
    }

    /**
     * 集合为空
     *
     * @param collection
     * @param errorCode
     * @param msg
     */
    public static void requireEmpty(Collection collection, ErrorCode errorCode, String msg) {
        if (collection == null || collection.size() == 0) {
            return;
        }

        throw new ApplicationException(errorCode, msg);
    }
}
