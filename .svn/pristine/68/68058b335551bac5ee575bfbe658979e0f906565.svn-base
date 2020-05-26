package com.gpdi.hqplus.resources.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.IdGenerator;
import com.gpdi.hqplus.common.util.StringUtil;
import com.gpdi.hqplus.resources.entity.ProductResource;
import com.gpdi.hqplus.resources.entity.query.ProductListQuery;
import com.gpdi.hqplus.resources.entity.query.ProductQuery;
import com.gpdi.hqplus.resources.feign.FileFeignClient;
import com.gpdi.hqplus.resources.mapper.ProductResourceMapper;
import com.gpdi.hqplus.resources.service.IProductResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * <p>
 * 商品资源 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-02
 */
@Slf4j
@Service("productResourceServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class ProductResourceServiceImpl extends ServiceImpl<ProductResourceMapper, ProductResource> implements IProductResourceService {
    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private FileFeignClient fileFeignClient;


    @Override
    public Page<ProductResource> page(ProductListQuery query) {
        Page<ProductResource> page = new Page<>();
        page.setCurrent(query.getCurrent());
        page.setSize(query.getSize());

        LambdaQueryWrapper<ProductResource> queryWrapper = new QueryWrapper<ProductResource>().lambda()
                .orderByDesc(ProductResource::getId);

        if (StringUtil.isNotBlank(query.getBusinessCode())) {
            queryWrapper.eq(ProductResource::getBusinessCode, query.getBusinessCode());
        }
        if (StringUtil.isNotBlank(query.getType())) {
            queryWrapper.eq(ProductResource::getType, query.getType());
        }
        if (StringUtil.isNotBlank(query.getStatus())) {
            queryWrapper.eq(ProductResource::getStatus, query.getStatus());
        }

        baseMapper.selectPage(page, queryWrapper);

        return page;
    }

    @Override
    public void add(ProductQuery query) {
        query.setId(idGenerator.getNumberId());
        query.setStatus(ProductResource.STATUS_NORMAL);

        boolean insert = query.insert();
        if (!insert) {
            log.error("insert file resource fail");
            throw new ApplicationException(ErrorCode.SERVICE_ERROR, "添加数据失败,请稍后再试");
        }

        fileFeignClient.setByResourceId(query.getId(), query.getFileNames());
    }

    @Override
    public void update(ProductQuery query) {
        ProductResource productResource = baseMapper.selectById(query.getId());
        if (productResource == null) {
            throw new ApplicationException(ErrorCode.RESOURCES_NOT_FIND, "查询数据失败");
        }

        // 更新字段
        if (StringUtil.isNotBlank(query.getType())) {
            productResource.setType(query.getType());
        }
        if (query.getMinPrice() != null) {
            query.setMinPrice(query.getMinPrice());
        }
        if (query.getMaxPrice() != null) {
            query.setMaxPrice(query.getMaxPrice());
        }
        if (query.getPrice() != null) {
            query.setPrice(query.getPrice());
        }
        if (StringUtil.isNotBlank(query.getPriceType())) {
            productResource.setPriceType(query.getPriceType());
        }
        if (StringUtil.isNotBlank(query.getExtensionResource())) {
            productResource.setExtensionResource(query.getExtensionResource());
        }
        if (StringUtil.isNotBlank(query.getDescription())) {
            productResource.setDescription(query.getDescription());
        }
        if (StringUtil.isNotBlank(query.getContext())) {
            productResource.setContext(query.getContext());
        }

        boolean update = productResource.updateById();
        if (!update) {
            log.error("update file resource fail");
            throw new ApplicationException(ErrorCode.SERVICE_ERROR, "更新数据失败,请稍后再试");
        }
    }

    @Override
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }
}
