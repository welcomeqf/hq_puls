package com.gpdi.hqplus.enterprise.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gpdi.hqplus.enterprise.entity.Enterprise;
import com.gpdi.hqplus.enterprise.entity.EnterpriseQuery;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 企业信息表 服务类
 * </p>
 *
 * @author wzr
 * @since 2019-07-13
 */
public interface IEnterpriseService extends IService<Enterprise> {

    /**
     * 返回所有企业列表，不分页
     * @return
     */
    List<Enterprise> listAll();

    /*以上为app端功能*/

    /**
     * 添加企业
     * @param enterprise
     */
    void add(Enterprise enterprise);

    /**
     * 编辑企业信息
     * @param enterprise
     */
    void edit(Enterprise enterprise);

    /**
     * 根据id集合批量删除企业
     * @param ids
     */
    void deleteList(List<Long> ids);

    /**
     * 分页查询企业
     * @param page
     * @return
     */
    Page listByPage(Page page);

    /**
     * 根据查询条件查询所有企业
     * @param enterpriseQuery
     * @return
     */
    List<Enterprise> listAllForQuery(EnterpriseQuery enterpriseQuery);

    /**
     * 导出企业信息表
     * @param response
     * @param enterpriseQuery
     */
    void exportExcel(HttpServletResponse response, EnterpriseQuery enterpriseQuery);

}
