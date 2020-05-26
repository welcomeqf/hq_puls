package com.gpdi.hqplus.property.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.common.entity.ProcessCallbackBO;
import com.gpdi.hqplus.property.entity.query.PropertyMaintainApplyQuery;
import com.gpdi.hqplus.property.entity.query.PropertyMaintainListQuery;
import com.gpdi.hqplus.property.entity.vo.PropertyMainTainApplyVo;
import com.gpdi.hqplus.property.entity.vo.PropertyMaintainListVO;
import com.gpdi.hqplus.property.entity.vo.PropertyMaintainVO;
import com.gpdi.hqplus.resources.entity.PropertyMaintenanceApply;
import com.gpdi.hqplus.resources.entity.PropertyMaintenanceType;

import java.util.List;

/**
 * @author: lianghb
 * @create: 2019-07-03 14:07
 **/
public interface IPropertyService {

    /**
     * 提交申请
     *
     * @param query
     */
    void apply(PropertyMaintainApplyQuery query);

    /**
     * 获取用户的报修记录
     * @param userId
     * @return
     */
    List<PropertyMaintainVO> listByUser(Long userId);

    /**
     * 获取本人的申请记录-分页
     *
     * @param page
     * @return
     */
    Page<PropertyMaintainVO> pageByUserForFinish(Page<PropertyMaintenanceApply> page);

    /**
     * 获取本人的申请记录-分页
     *
     * @param page
     * @return
     */
    Page<PropertyMaintainVO> pageByUserForProcessing(Page<PropertyMaintenanceApply> page);

    /**
     * 通过 id 获取详情
     *
     * @param id
     * @return
     */
    PropertyMaintainVO getById(Long id);

    /**
     * 流程回调处理
     *
     * @param callbackBO
     */
    void update(ProcessCallbackBO callbackBO);

    /**
     * 反馈
     *
     * @param query
     */
    void feedback(PropertyMaintainApplyQuery query);

    /**
     * 分页获取所有申请记录
     *
     * @param query
     * @return
     */
    Page<PropertyMaintainListVO> pageAll(PropertyMaintainListQuery query);

    /**
     * 通过 id 获取详情
     *
     * @param id
     * @return
     */
    PropertyMaintainListVO getListVOById(Long id);

    /**
     * 维修师傅维修成功
     *
     * @param vo
     */
    PropertyMaintenanceApply success(PropertyMainTainApplyVo vo);

    /**
     * 维修师傅拒绝维修
     *
     * @param id
     */
    void fail(String id);


    /**
     * 维修申请审核通过
     *
     * @param id
     */
    PropertyMaintenanceApply audit(String id);

    /**
     * 审核失败
     *
     * @param id
     */
    void auditFail(String id);
}
