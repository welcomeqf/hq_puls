package com.gpdi.hqplus.complaint.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.common.entity.ProcessCallbackBO;
import com.gpdi.hqplus.complaint.entity.Complaint;
import com.gpdi.hqplus.complaint.entity.query.ComplaintPageQuery;
import com.gpdi.hqplus.complaint.entity.vo.ComplaintAdminVo;
import com.gpdi.hqplus.complaint.entity.vo.ComplaintFeedbackVo;

/**
 * 管理端处理意见反馈接口
 * @Author qf
 * @Date 2019/7/12
 * @Version 1.0
 */
public interface IComplaintAdminService {

    /**
     * 修改流程状态
     * @param bo
     * @return
     */
    Long updateStatus(ProcessCallbackBO bo);

    /**
     *  删除状态~
     *  <p>同时返回处理人id</p>
     * @param complaintAdminVo
     * @return
     */
    Long dateleStatus(ComplaintAdminVo complaintAdminVo);

    /**
     * 取消状态
     * <p>直接反馈结果</p>
     * @param complaintFeedbackVo
     */
    void feed(ComplaintFeedbackVo complaintFeedbackVo);

    /**
     * 分页查看所有流程的历史记录
     * @param complaintPage
     * @return
     */
    Page<Complaint> listPageHistory(ComplaintPageQuery complaintPage);

}
