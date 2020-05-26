package com.gpdi.hqplus.resources.service;

import com.gpdi.hqplus.resources.entity.ServiceResource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 
服务资源 服务类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-02
 */
public interface IServiceResourceService extends IService<ServiceResource> {
    /**
     * 获取增值服务
     * @param ids
     * @return
     */
    Map<Long,ServiceResource> listByIds(Set<Long> ids);
}
