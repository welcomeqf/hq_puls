package com.gpdi.hqplus.resources.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.resources.entity.ProductResource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gpdi.hqplus.resources.entity.query.ProductListQuery;
import com.gpdi.hqplus.resources.entity.query.ProductQuery;

import java.util.List;

/**
 * <p>
 * 
商品资源 服务类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-02
 */
public interface IProductResourceService extends IService<ProductResource> {
    /**
     * 分页查询
     * @param query
     * @return
     */
    Page<ProductResource> page(ProductListQuery query);

    /**
     * 新增
     * @param query
     */
    void add(ProductQuery query);

    /**
     * 修改
     * @param query
     */
    void update(ProductQuery query);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);
}
