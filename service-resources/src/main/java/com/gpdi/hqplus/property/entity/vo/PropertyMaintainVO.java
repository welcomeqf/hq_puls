package com.gpdi.hqplus.property.entity.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: lianghb
 * @create: 2019-07-03 14:21
 **/
@Data
public class PropertyMaintainVO {
    private Long id;
    private String type;
    private String address;
    private String addressRemark;
    /**
     * 反馈
     */
    private String feedBack;
    private String userName;
    private String userConnect;
    private LocalDateTime createTime;
    private String status;
    private String message;
    private List<String> imageFiles;
    private BigDecimal amount;
}
