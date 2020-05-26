package com.gpdi.hqplus.complaint.validate;

import com.gpdi.hqplus.common.entity.ProcessCallbackBO;
import com.gpdi.hqplus.complaint.entity.Complaint;
import com.gpdi.hqplus.complaint.entity.query.TypeComplaintManageQuery;
import com.gpdi.hqplus.complaint.entity.vo.AdminComplaintDel;
import com.gpdi.hqplus.complaint.entity.vo.ComplaintTypeVo;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author qf
 * @Date 2019/7/20
 * @Version 1.0
 */
public class ComplaintAdminDate {

    /**
     * 判断返回结果
     * @param days
     * @param day
     * @param complaintDel
     * @param typeComplaint
     * @return
     */
    public static ComplaintTypeVo getVo(Long days, Long day, AdminComplaintDel complaintDel, TypeComplaintManageQuery typeComplaint){

        ComplaintTypeVo complaintTypeVo = new ComplaintTypeVo();

        if (days > day) {
            complaintTypeVo.setCode("500");
            return complaintTypeVo;
        }
        complaintTypeVo.setDeptCode(complaintDel.getDeptCode());
        complaintTypeVo.setTypeName(typeComplaint.getName());
        complaintTypeVo.setCode("200");
        return complaintTypeVo;
    }

    /**
     * 得到一个组装的bo对象
     * @param complaintDel
     * @param complaint
     * @return
     */
    public static ProcessCallbackBO getBO(AdminComplaintDel complaintDel, Complaint complaint){
        //组装一个BO
        ProcessCallbackBO bo = new ProcessCallbackBO();
        Map<String, Object> variables = new HashMap<>(10);
        variables.put("fid",complaintDel.getFid());
        variables.put("deptCode",complaintDel.getDeptCode());
        bo.setVariables(variables);
        bo.setProcessPointCode(complaint.getStatus());
        bo.setBusinessKey(complaint.getId());
        return bo;
    }
}
