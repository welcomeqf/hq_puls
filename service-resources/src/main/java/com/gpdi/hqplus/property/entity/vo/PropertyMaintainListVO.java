package com.gpdi.hqplus.property.entity.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: lianghb
 * @create: 2019-07-09 16:35
 **/
@Data
public class PropertyMaintainListVO {
    private Long id;
    private String address;
    private String userName;
    private String userConnect;
    private String type;
    private String message;
    private String status;
    private List<String> imageFiles;
    private BigDecimal amount;
    private LocalDateTime createTime;
    private String dealUserName;
    private String taskId;
}
