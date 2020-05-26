package com.gpdi.hqplus.complaint.entity.vo;

import lombok.Data;

/**
 * @Author qf
 * @Date 2019/7/20
 * @Version 1.0
 */
@Data
public class AdminComplaintDel {

    private Long id;
    /**
     * 分类Id
     */
    private Long fid;

    /**
     * 部门编号
     */
    private String deptCode;
}
