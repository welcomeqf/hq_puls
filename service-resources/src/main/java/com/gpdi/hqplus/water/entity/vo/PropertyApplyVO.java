package com.gpdi.hqplus.water.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: liuJiaHui
 * @create: 2019-07-17 14:40
 **/
@Data
public class PropertyApplyVO {

    private Long id;
    private String businessCode;
    private String userName;
    private String userConnect;
    private Long userId;
    private String address;
    private String userMessage;
    private String feedback;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer version;
    private String projectCode;

}
