package com.gpdi.hqplus.property.entity.query;

import com.gpdi.hqplus.resources.entity.PropertyMaintenanceApply;
import lombok.Data;

/**
 * 物业报修申请单
 * @author: lianghb
 * @create: 2019-07-03 14:18
 **/
@Data
public class PropertyMaintainApplyQuery extends PropertyMaintenanceApply {
    private String[] files;
}
