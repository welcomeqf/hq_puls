package com.gpdi.hqplus.resources.entity.vo;

import com.gpdi.hqplus.resources.entity.PropertyEquipment;
import lombok.Data;

/**
 * @Description 设备vo，主要是为了加一个数量
 * @Author wzr
 * @CreateDate 2019-07-18
 * @Time 17:29
 */
@Data
public class EquipmentVO {
    private Long id;
    private String name;
    private String code;
    private String imgSrc;
}
