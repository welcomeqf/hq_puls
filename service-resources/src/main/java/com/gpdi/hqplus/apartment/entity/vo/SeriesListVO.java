package com.gpdi.hqplus.apartment.entity.vo;

import com.gpdi.hqplus.resources.entity.vo.EquipmentVO;
import lombok.Data;

import java.util.List;

/**
 * @author: lianghb
 * @create: 2019-07-20 13:34
 **/
@Data
public class SeriesListVO {
    private Long id;
    private String name;
    private String description;
    private String[] showImages;
    private List<EquipmentVO> equipments;
    private String userInstruction;
    private SeriesPriceVO price;
}
