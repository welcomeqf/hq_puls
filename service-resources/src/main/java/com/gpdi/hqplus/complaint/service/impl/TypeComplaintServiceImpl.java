package com.gpdi.hqplus.complaint.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.IdGenerator;
import com.gpdi.hqplus.complaint.entity.TypeComplaint;
import com.gpdi.hqplus.complaint.entity.query.TypeComplaintManageQuery;
import com.gpdi.hqplus.complaint.entity.query.TypeComplaintQuery;
import com.gpdi.hqplus.complaint.mapper.TypeComplaintMapper;
import com.gpdi.hqplus.complaint.service.ITypeComplaintService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 意见反馈分类实现类
 * @Author qf
 * @Date 2019/7/16
 * @Version 1.0
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TypeComplaintServiceImpl extends ServiceImpl<TypeComplaintMapper, TypeComplaint> implements ITypeComplaintService {

    @Autowired
    private IdGenerator idGenerator;

    /**
     * 增加分类
     * @param ty
     * @return
     */
    @Override
    public void insertType(TypeComplaintQuery ty) {

        //设置属性
        TypeComplaint typeComplaint = new TypeComplaint();
        typeComplaint.setId(idGenerator.getNumberId());
        typeComplaint.setName(ty.getName());
        typeComplaint.setLimitDay(ty.getLimitDay());

        boolean insert = typeComplaint.insert();
        if (!insert) {
            log.error("add complaint type fail.");
            throw new ApplicationException(ErrorCode.SERVICE_ERROR,"添加分类失败，请稍后再试");
        }
    }

    /**
     * 根据id删除分类
     * @param id
     */
    @Override
    public void deleteTypeById(Long id) {
        int result = baseMapper.deleteById(id);
        if (result <= 0) {
            log.error("complaint type delete fail.");
            throw new ApplicationException(ErrorCode.SERVICE_ERROR,"意见分类删除失败，请稍后再试");
        }
    }

    /**
     * 修改分类
     * @param ty
     */
    @Override
    public void updateTypeById(TypeComplaintQuery ty) {

        //设置属性
        TypeComplaint typeComplaint = new TypeComplaint();
        typeComplaint.setId(ty.getId());
        typeComplaint.setName(ty.getName());
        typeComplaint.setLimitDay(ty.getLimitDay());

        boolean update = typeComplaint.updateById();
        if (!update) {
            log.error("update complaint type fail.");
            throw new ApplicationException(ErrorCode.SERVICE_ERROR,"修改分类失败，请稍后再试");
        }
    }

    /**
     * 查询所有分类
     * @return
     */
    @Override
    public List<TypeComplaintManageQuery> listType() {
        List<TypeComplaint> typeComplaints = baseMapper.selectList(null);
        if (typeComplaints == null) {
            log.error("query complaint all type fail.");
            throw new ApplicationException(ErrorCode.SERVICE_ERROR,"查询所有分类失败");
        }

        List<TypeComplaintManageQuery> result = new ArrayList<>();
        for (TypeComplaint typeComplaint : typeComplaints) {
            TypeComplaintManageQuery ty = new TypeComplaintManageQuery();
            //装配query
            ty.setId(typeComplaint.getId());
            ty.setCreateTime(typeComplaint.getCreateTime());
            ty.setLimitDay(typeComplaint.getLimitDay());
            ty.setName(typeComplaint.getName());
            ty.setUpdateTime(typeComplaint.getUpdateTime());
            result.add(ty);
        }

        return result;
    }

    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    @Override
    public TypeComplaintManageQuery queryTypeById(Long id) {
        TypeComplaint typeComplaint = baseMapper.selectById(id);

        if (typeComplaint == null) {
            throw new ApplicationException(ErrorCode.SERVICE_ERROR,"未查询到数据");
        }

        TypeComplaintManageQuery ty = new TypeComplaintManageQuery();
        //装配query
        ty.setId(typeComplaint.getId());
        ty.setCreateTime(typeComplaint.getCreateTime());
        ty.setLimitDay(typeComplaint.getLimitDay());
        ty.setName(typeComplaint.getName());
        ty.setUpdateTime(typeComplaint.getUpdateTime());
        return ty;
    }
}
