package com.gpdi.hqplus.common.validate;

import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.entity.BasePageQuery;
import com.gpdi.hqplus.common.exception.ApplicationException;

import java.util.Collection;

/**
 * 分页参数验证
 *
 * @author: lianghb
 * @create: 2019-07-01 22:40
 **/
public class PageQueryValidate {
    private PageQueryValidate() {
    }

    /**
     * 检查分页对象
     * 当前页与每页记录数均不能小于 1
     *
     * @param query
     */
    public static void check(BasePageQuery query) {
        if (query.getCurrent() == null || query.getCurrent() < 1) {
            throw new ApplicationException(ErrorCode.SERVICE_ERROR, "当前页不能为空或小于 1");
        }
        if (query.getSize() == null || query.getSize() < 1) {
            throw new ApplicationException(ErrorCode.SERVICE_ERROR, "每页数量不能为空或小于 1");
        }
    }
}
