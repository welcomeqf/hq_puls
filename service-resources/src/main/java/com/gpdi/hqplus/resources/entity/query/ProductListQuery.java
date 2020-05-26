package com.gpdi.hqplus.resources.entity.query;

import com.gpdi.hqplus.common.entity.BasePageQuery;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author: lianghb
 * @create: 2019-07-06 14:17
 **/
@Data
public class ProductListQuery extends BasePageQuery {
    private String businessCode;
    private String type;
    private String status;
    private LocalDate date;
    private String name;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private BigDecimal minArea;
    private BigDecimal maxArea;
    private String address;
    private Integer minSize;
    private Integer maxSize;
}
