package com.gpdi.hqplus.pass.query;

import com.gpdi.hqplus.common.entity.BasePageQuery;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: lianghb
 * @create: 2019-07-01 22:31
 **/
@Data
public class ProductPassQuery extends BasePageQuery {
    private String policemenName;
    private String address;
    private String policemenType;
    private String status;
    /**
     * passDateStart 申请放行时间
     */
    private LocalDateTime passDateStart;
    private LocalDateTime passDateEnd;
    /**
     * 放行时间
     */
    private LocalDateTime finishTimeStart;
    private LocalDateTime finishTimeEnd;
    /**
     * 申请时间
     */
    private LocalDateTime createTimeStart;
    private LocalDateTime createTimeEnd;
}
