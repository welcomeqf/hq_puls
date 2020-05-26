package com.gpdi.hqplus.complaint.service;

import com.gpdi.hqplus.complaint.entity.TypeComplaint;
import com.gpdi.hqplus.complaint.entity.query.TypeComplaintManageQuery;
import com.gpdi.hqplus.complaint.entity.query.TypeComplaintQuery;

import java.util.List;

/**
 * 意见反馈分类接口
 * @Author qf
 * @Date 2019/7/16
 * @Version 1.0
 */
public interface ITypeComplaintService {

    /**
     * 增加意见反馈的分类
     * @param ty
     * @return
     */
    void insertType(TypeComplaintQuery ty);

    /**
     * 根据id删除分类
     * @param id
     */
    void deleteTypeById(Long id);

    /**
     * 修改分类
     * @param typeComplaintQuery
     */
    void updateTypeById(TypeComplaintQuery typeComplaintQuery);

    /**
     * 查询所有分类
     * @return
     */
    List<TypeComplaintManageQuery> listType();

    /**
     * 根据id查询一条分类数据
     * @param id
     * @return
     */
    TypeComplaintManageQuery queryTypeById(Long id);
}
