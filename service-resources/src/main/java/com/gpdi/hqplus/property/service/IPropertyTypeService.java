package com.gpdi.hqplus.property.service;

import com.gpdi.hqplus.resources.entity.PropertyMaintenanceType;

import java.util.List;

/**
 * @author: lianghb
 * @create: 2019-07-05 10:28
 **/
public interface IPropertyTypeService {
    /**
     * 获取物业报修类型
     *
     * @return
     */
    List<PropertyMaintenanceType> listType();
}
