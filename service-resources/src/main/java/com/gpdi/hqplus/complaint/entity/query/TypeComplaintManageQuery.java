package com.gpdi.hqplus.complaint.entity.query;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author qf
 * @Date 2019/7/20
 * @Version 1.0
 */
@Data
public class TypeComplaintManageQuery {

    private Long id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer limitDay;
}
