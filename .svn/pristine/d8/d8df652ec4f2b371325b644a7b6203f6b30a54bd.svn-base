package com.gpdi.hqplus.resources.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.IdGenerator;
import com.gpdi.hqplus.common.util.StringUtil;
import com.gpdi.hqplus.resources.entity.ProductResource;
import com.gpdi.hqplus.resources.entity.PropertyResource;
import com.gpdi.hqplus.resources.entity.query.ProductListQuery;
import com.gpdi.hqplus.resources.entity.query.ProductQuery;
import com.gpdi.hqplus.resources.entity.query.PropertyListQuery;
import com.gpdi.hqplus.resources.entity.query.PropertyQuery;
import com.gpdi.hqplus.resources.feign.FileFeignClient;
import com.gpdi.hqplus.resources.mapper.PropertyResourceMapper;
import com.gpdi.hqplus.resources.service.IPropertyResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 物业资源 服务实现类
 * </p>
 *
 * @author wzr
 * @since 2019-07-05
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class PropertyResourceServiceImpl extends ServiceImpl<PropertyResourceMapper, PropertyResource> implements IPropertyResourceService {
    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private FileFeignClient fileFeignClient;


    @Override
    public Page<PropertyResource> page(PropertyListQuery query) {
        Page<PropertyResource> page = new Page<>();
        page.setCurrent(query.getCurrent());
        page.setSize(query.getSize());

        LambdaQueryWrapper<PropertyResource> queryWrapper = new QueryWrapper<PropertyResource>().lambda()
                .orderByDesc(PropertyResource::getId);

        if (StringUtil.isNotBlank(query.getBusinessCode())) {
            queryWrapper.eq(PropertyResource::getBusinessCode, query.getBusinessCode());
        }
        if (StringUtil.isNotBlank(query.getType())) {
            queryWrapper.eq(PropertyResource::getType, query.getType());
        }
        if (StringUtil.isNotBlank(query.getStatus())) {
            queryWrapper.eq(PropertyResource::getStatus, query.getStatus());
        }

        baseMapper.selectPage(page, queryWrapper);

        return page;
    }

    @Override
    public void add(PropertyQuery query) {
        query.setId(idGenerator.getNumberId());
        query.setStatus(ProductResource.STATUS_NORMAL);

        boolean insert = query.insert();
        if (!insert) {
            log.error("insert property resource fail");
            throw new ApplicationException(ErrorCode.SERVICE_ERROR, "添加数据失败,请稍后再试");
        }

        //fileFeignClient.setByResourceId(query.getId(), query.getFileNames());
    }

    @Override
    public void update(PropertyQuery query) {
        PropertyResource propertyResource = baseMapper.selectById(query.getId());
        if (propertyResource == null) {
            throw new ApplicationException(ErrorCode.RESOURCES_NOT_FIND, "查询数据失败");
        }


        boolean update = propertyResource.updateById();
        if (!update) {
            log.error("update property resource fail");
            throw new ApplicationException(ErrorCode.SERVICE_ERROR, "更新数据失败,请稍后再试");
        }
    }

    @Override
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }
}
