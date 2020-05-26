package com.gpdi.hqplus.pass.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.common.entity.ProcessCallbackBO;
import com.gpdi.hqplus.pass.entity.ProductPass;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gpdi.hqplus.pass.query.ProductPassQuery;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zake
 * @since 2019-07-05
 */
public interface IProductPassService extends IService<ProductPass> {

    /**
     * 提交申请
     *
     * @param pass
     */
    void submit(ProductPass pass);

    /**
     * 查询当前用户的记录
     *
     * @param page
     * @return 申请记录表
     */
    Page list(Page page);


    /**
     * 查询所有记录
     *
     * @param query
     * @return 申请记录表
     */
    Page listAll(ProductPassQuery query);


    /**
     * 流程回调处理
     *
     * @param callbackBO
     */
    void update(ProcessCallbackBO callbackBO) throws Exception;

    /**
     * 手动推动( 审核成功)
     *
     * @param productPassId
     */
    ProductPass handUpdate(String productPassId) throws Exception;

    /**
     * 手动推动( 审核不通过)
     *
     * @param id
     */
    void handFail(String id);


    /**
     * 删除申请
     *
     * @param id
     */
    void deleteSubmit(String id);

    /**
     * 物业放行时间过期 改变转态
     *
     * @param
     */
    void changeStatus();

    /**
     * 管家审批
     *
     * @param id
     * @return
     * @throws Exception
     */
    ProductPass houseKeeper(String id) throws Exception;

    /**
     * 客服审批
     *
     * @param id
     * @return
     */
    ProductPass addressOfficeCustomer(String id);

    /**
     * 客服主管审批
     *
     * @param id
     * @return
     * @throws Exception
     */
    ProductPass addressOfficeSupervisor(String id) throws Exception;

    /**
     * 保安审批
     *
     * @param id
     * @return
     */
    ProductPass policemenPass(String id);

    /**
     * 管家审批失败
     *
     * @param productPass
     * @return
     */
    ProductPass houseKeeperFail(ProductPass productPass);

    /**
     * 保安不同意放行
     *
     * @param productPass
     * @return
     */
    ProductPass policemenPassFail(ProductPass productPass);

    /**
     * 客服审批不通过
     *
     * @param productPass
     * @return
     */
    ProductPass addressOfficeCustomerFail(ProductPass productPass);

    /**
     * 客服主管审批不通过
     *
     * @param productPass
     * @return
     */
    ProductPass addressOfficeSupervisorFail(ProductPass productPass);


}
