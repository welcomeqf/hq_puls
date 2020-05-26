package com.gpdi.hqplus.order.entity.query;

import com.gpdi.hqplus.common.entity.BasePageQuery;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * @author: lianghb
 * @create: 2019-07-08 14:14
 **/
@Data
public class OrderListQuery extends BasePageQuery {
    private String businessCode;
    private String status;
    private Long userId;
    private String name;
    private String userName;
    private String userConnect;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private LocalDateTime startCreateTime;
    private LocalDateTime endCreateTime;

}
