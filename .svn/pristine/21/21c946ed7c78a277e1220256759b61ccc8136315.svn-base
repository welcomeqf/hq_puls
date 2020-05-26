package com.gpdi.hqplus.common.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.common.entity.BasePageQuery;

/**
 * @author: lianghb
 * @create: 2019-07-20 15:10
 **/
public class PageHelper {
    private PageHelper() {
    }

    /**
     * 分页查询对象
     *
     * @param query
     * @param T
     * @param <T>
     * @return
     */
    public static <T> Page<T> newInstance(BasePageQuery query, Class T) {
        Page<T> page = new Page<>();
        page.setCurrent(query.getCurrent());
        page.setSize(query.getSize());

        return page;
    }
}
