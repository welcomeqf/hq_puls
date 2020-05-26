package com.gpdi.hqplus.apartment.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.apartment.entity.ApartmentApply;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gpdi.hqplus.apartment.entity.query.ApplyPageQuery;
import com.gpdi.hqplus.apartment.entity.query.ApplyQuery;
import com.gpdi.hqplus.apartment.entity.vo.ApplyManagerListVO;

/**
 * <p>
 * 公寓看房预约 服务类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-20
 */
public interface IApartmentApplyService extends IService<ApartmentApply> {

    /**
     * 预约
     *
     * @param apply
     */
    void apply(ApartmentApply apply);

    /**
     * 管理端分页查询
     * @param query
     * @return
     */
    Page<ApplyManagerListVO> pageManager(ApplyPageQuery query);

    /**
     * 设置责任人
     *
     * @param query
     */
    void setPrincipal(ApplyQuery query);
}
