package com.gpdi.hqplus.complaint.entity.query;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author qf
 * @Date 2019/7/19
 * @Version 1.0
 */
@Data
public class ComplaintHistoryQuery {

    /**
     * 用户Id
     */
    private Long uid;
    /**
     * 流程进度的结果
     */
    private String result;
    /**
     * 投诉的类型
     */
    private String type;

    private String message;

    /**
     * 图片id
     */
    private String images;

    private String userName;

    private String userConnect;
    /**
     * 申请时间
     */
    private LocalDateTime applyTime;
    /**
     * 最后处理时间
     */
    private LocalDateTime dealTime;
}
