package com.gpdi.hqplus.resources.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gpdi.hqplus.common.util.CollectionUtil;
import com.gpdi.hqplus.resources.entity.ServiceResource;
import com.gpdi.hqplus.resources.mapper.ServiceResourceMapper;
import com.gpdi.hqplus.resources.service.IServiceResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 
服务资源 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-02
 */
@Slf4j
@Service(value = "serviceResourceServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class ServiceResourceServiceImpl extends ServiceImpl<ServiceResourceMapper, ServiceResource> implements IServiceResourceService {

    @Override
    public Map<Long, ServiceResource> listByIds(Set<Long> ids) {
        List<ServiceResource> serviceResources = baseMapper.selectList(new QueryWrapper<ServiceResource>()
                .lambda()
                .eq(ServiceResource::getStatus, ServiceResource.STATUS_NORMAL)
                .in(ServiceResource::getId, ids)
        );
        Map<Long, ServiceResource> map = CollectionUtil.createHashMap();
        if(CollectionUtil.isNotEmpty(serviceResources)){
            for (ServiceResource serviceResource : serviceResources) {
                map.put(serviceResource.getId(),serviceResource);
            }
        }

        return map;
    }
}
