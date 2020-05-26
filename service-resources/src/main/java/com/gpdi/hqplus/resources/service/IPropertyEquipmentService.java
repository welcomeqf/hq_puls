package com.gpdi.hqplus.resources.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gpdi.hqplus.resources.entity.PropertyEquipment;
import com.gpdi.hqplus.resources.entity.vo.EquipmentVO;
import com.gpdi.hqplus.resources.entity.vo.SimpleEquipmentVO;

import java.util.List;

/**
 * <p>
 * 物业设备 服务类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-08
 */
public interface IPropertyEquipmentService extends IService<PropertyEquipment> {

    /**
     * 新增设备
     *
     * @param equipment
     */
    void add(PropertyEquipment equipment);

    /**
     * 获取设备map
     *
     * @param ids
     * @return
     */
    List<PropertyEquipment> listByIds(Long[] ids);

    /**
     * 根据type获取设备
     *
     * @param code
     * @return
     */
    List<PropertyEquipment> listByCode(String code);

    /**
     * @param type
     * @return
     */
    List<EquipmentVO> listByType(String type);
}
