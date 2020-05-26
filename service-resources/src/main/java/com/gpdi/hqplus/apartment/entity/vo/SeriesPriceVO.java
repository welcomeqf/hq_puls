package com.gpdi.hqplus.apartment.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author: lianghb
 * @create: 2019-07-21 14:51
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeriesPriceVO {
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}
