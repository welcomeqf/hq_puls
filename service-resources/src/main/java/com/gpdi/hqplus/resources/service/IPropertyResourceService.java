package com.gpdi.hqplus.resources.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.resources.entity.PropertyResource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gpdi.hqplus.resources.entity.query.ProductListQuery;
import com.gpdi.hqplus.resources.entity.query.ProductQuery;
import com.gpdi.hqplus.resources.entity.query.PropertyListQuery;
import com.gpdi.hqplus.resources.entity.query.PropertyQuery;

/**
 * <p>
 * 物业资源 服务类
 * </p>
 *
 * @author wzr
 * @since 2019-07-05
 */
public interface IPropertyResourceService extends IService<PropertyResource> {
    /**
     * 分页查询
     * @param query
     * @return
     */
    Page<PropertyResource> page(PropertyListQuery query);

    /**
     * 新增
     * @param query
     */
    void add(PropertyQuery query);

    /**
     * 修改
     * @param query
     */
    void update(PropertyQuery query);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);
}
