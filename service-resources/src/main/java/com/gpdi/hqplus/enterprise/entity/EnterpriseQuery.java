package com.gpdi.hqplus.enterprise.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description 企业信息查询entity
 * @Author wzr
 * @CreateDate 2019-07-15
 * @Time 09:39
 */
@Data
public class EnterpriseQuery extends Enterprise{

    private LocalDateTime createTimeMin;
    private LocalDateTime createTimeMax;
}
