package com.gpdi.hqplus.apartment.entity.vo;

import com.gpdi.hqplus.resources.entity.vo.SimpleEquipmentVO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: lianghb
 * @create: 2019-07-20 17:17
 **/
@Data
public class RoomManagerListVO {
    private Long id;
    private Long seriesId;
    private String name;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private BigDecimal area;
    private List<SimpleEquipmentVO> equipments;
    private String description;
    private String toward;
    private String roomModel;
    private String rentType;
    private String rentTime;
    private String[] showImages;
    private String[] modelImages;
    private String status;

    private String seriesName;
}
