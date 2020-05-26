package com.gpdi.hqplus.complaint.entity.query;

import com.gpdi.hqplus.common.entity.BasePageQuery;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author qf
 * @Date 2019/7/15
 * @Version 1.0
 */
@Data
public class ComplaintPageQuery extends BasePageQuery {

    private Long id;
    private String type;
    private Long userId;
    private String userName;
    private String userConnect;
    private String message;
    private Long dealUserId;
    private String feedBack;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    /**
     * 分类id
     */
    private Long fid;

    /**
     * 部门id
     */
    private Long bid;

    /**
     * 是否超时
     */
    private String overtime;

    /**
     * 业务部门
     */
    private String businessDept;

    /**
     * 业务类型
     */
    private String businessType;
}
