package com.gpdi.hqplus.complaint.service;

import com.gpdi.hqplus.complaint.entity.Complaint;
import com.gpdi.hqplus.complaint.entity.query.ComplaintHistoryQuery;
import com.gpdi.hqplus.complaint.entity.query.ComplaintQuery;

import java.util.List;

/**
 * 意见反馈接口
 * @Author qf
 * @Date 2019/7/11
 * @Version 1.0
 */
public interface IComplaintService {

    /**
     * 提交申请建议
     * @param query
     * @return
     */
    Complaint apply(ComplaintQuery query);

    /**
     * 通过id查询申请记录
     * <p>查看流程</p>
     * @param id
     * @return
     */
    Complaint queryById(Long id);

    /**
     * 根据id修改状态
     * @param complaint
     * @return
     */
    boolean updateStatusById(Complaint complaint);

    /**
     * 查询用户所有的投诉记录
     * @return
     */
    List<ComplaintHistoryQuery> listHistory();

    /**
     * 查看用户正在进行中的记录
     * @return
     */
    List<ComplaintHistoryQuery> listProcess();
}
