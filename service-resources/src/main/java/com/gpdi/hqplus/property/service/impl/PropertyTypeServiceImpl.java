package com.gpdi.hqplus.property.service.impl;

import com.gpdi.hqplus.property.service.IPropertyTypeService;
import com.gpdi.hqplus.resources.entity.PropertyMaintenanceType;
import com.gpdi.hqplus.resources.service.IProductPayTypeService;
import com.gpdi.hqplus.resources.service.impl.PropertyMaintenanceTypeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: lianghb
 * @create: 2019-07-05 10:28
 **/
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class PropertyTypeServiceImpl extends PropertyMaintenanceTypeServiceImpl implements IPropertyTypeService {
    @Override
    public List<PropertyMaintenanceType> listType() {
        return baseMapper.selectList(null);
    }
}
