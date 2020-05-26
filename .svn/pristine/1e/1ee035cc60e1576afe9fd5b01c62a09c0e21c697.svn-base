package com.gpdi.hqplus.complaint.controller.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.entity.ProcessCallbackBO;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.StringUtil;
import com.gpdi.hqplus.complaint.entity.Complaint;
import com.gpdi.hqplus.complaint.entity.TypeComplaint;
import com.gpdi.hqplus.complaint.entity.query.ComplaintPageQuery;
import com.gpdi.hqplus.complaint.entity.query.TypeComplaintManageQuery;
import com.gpdi.hqplus.complaint.entity.vo.AdminComplaintDel;
import com.gpdi.hqplus.complaint.entity.vo.ComplaintFeedbackVo;
import com.gpdi.hqplus.complaint.entity.vo.ComplaintTypeVo;
import com.gpdi.hqplus.complaint.service.IComplaintAdminService;
import com.gpdi.hqplus.complaint.service.IComplaintService;
import com.gpdi.hqplus.complaint.service.ITypeComplaintService;
import com.gpdi.hqplus.complaint.validate.ComplaintAdminDate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * web端管理层意见反馈
 * @Author qf
 * @Date 2019/7/18
 * @Version 1.0
 */
@Api
@Slf4j
@RestController
@RequestMapping("/v1/web/resources/complaintAdmin")
public class ComplaintAdminController {

    @Autowired
    private IComplaintAdminService complaintAdminService;

    @Autowired
    private IComplaintService complaintService;

    @Autowired
    private ITypeComplaintService typeComplaintService;


    /**
     * 管理员同意审核~分派，业务部门处理
     * <p>修改流程的状态</p>
     *
     * @param complaintDel
     * @return
     */
    @ApiOperation(value = "修改状态", notes = "修改状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "fid", value = "分类id", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "deptCode", value = "部门编号", required = true, dataType = "string", paramType = "path")
    })
    @PostMapping("/update")
    public ComplaintTypeVo updateStatus(@RequestBody AdminComplaintDel complaintDel) {
        if (complaintDel.getId() == null || StringUtil.isBlank(complaintDel.getDeptCode()) || complaintDel.getFid() == null) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "接收的参数不能为空");
        }

        TypeComplaintManageQuery typeComplaint = typeComplaintService.queryTypeById(complaintDel.getFid());
        Long day = typeComplaint.getLimitDay().longValue();
        Complaint complaint = complaintService.queryById(complaintDel.getId());

        ProcessCallbackBO bo = ComplaintAdminDate.getBO(complaintDel, complaint);
        Long days = complaintAdminService.updateStatus(bo);

        ComplaintTypeVo result = ComplaintAdminDate.getVo(days, day, complaintDel, typeComplaint);
        return result;
    }

    /**
     * 查看反馈记录
     *
     * @param query
     * @return
     */
    @ApiOperation(value = "查看反馈记录", notes = "查看反馈记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "每页显示条数", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "userName", value = "联系人", required = false, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "userConnect", value = "联系电话", required = false, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "type", value = "反馈类型", required = false, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "businessDept", value = "处理部门", required = false, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "overtime", value = "是否超时", required = false, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "businessType", value = "业务类型", required = false, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "status", value = "流程状态", required = false, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "createTime", value = "创建时间", required = false, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "updateTime", value = "更改时间", required = false, dataType = "string", paramType = "path")
    })
    @RequestMapping("/query")
    public Page<Complaint> queryAllHistory(@RequestBody ComplaintPageQuery query) {
        if (query.getCurrent() == null || query.getSize() == null) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "接收的参数不能为空");
        }
        Page<Complaint> page = complaintAdminService.listPageHistory(query);

        return page;
    }

    /**
     * 取消建议以及反馈内容
     *
     * @param vo
     * @return
     */
    @ApiOperation(value = "直接反馈", notes = "直接反馈")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "feedBack", value = "反馈内容", required = true, dataType = "string", paramType = "path")
    })
    @RequestMapping("/feedback")
    public String feedback(@RequestBody ComplaintFeedbackVo vo) {
        if (vo.getId() == null || StringUtil.isBlank(vo.getFeedBack())) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "接收参数不能为空");
        }
        complaintAdminService.feed(vo);
        return "ok";
    }
}
