package com.gpdi.hqplus.resources.entity.query;

import com.gpdi.hqplus.common.entity.BasePageQuery;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: lianghb
 * @create: 2019-07-06 14:17
 **/
@Data
public class PropertyListQuery extends BasePageQuery {
    private String businessCode;
    private String type;
    private String status;
    private Params params;

    @Data
    public static class Params{
        private String name;
        private BigDecimal price;
    }
}
