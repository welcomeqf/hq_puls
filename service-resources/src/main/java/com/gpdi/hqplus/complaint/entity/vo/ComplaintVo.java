package com.gpdi.hqplus.complaint.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author qf
 * @Date 2019/7/17
 * @Version 1.0
 */
@Data
public class ComplaintVo {

    private Long id;
    private String type;
    private Long userId;
    private String userName;
    private String userConnect;
    private Long dealUserId;
    private String feedBack;
    private Long parentId;
    private String status;
    private Integer version;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String projectCode;
    private String taskId;
}
