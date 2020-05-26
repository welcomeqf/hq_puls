package com.gpdi.hqplus.officebuild.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.officebuild.entity.OfficeVO;
import com.gpdi.hqplus.officebuild.entity.params.OfficeParamsVO;

/**
 * @Description 写字楼系列service
 * @Author wzr
 * @CreateDate 2019-07-08
 * @Time 15:58
 */
public interface OfficeService {

    /**
     * 根据id查询写字楼系列
     * @param officeId
     * @return
     */
    OfficeVO getOfficeById(Long officeId);

    /**
     * 分页查询写字楼系列列表
     * @param page
     * @return
     */
    Page listOfficeByPage(Page page);

    /*以上为APP端功能*/

    /**
     * 添加写字楼系列（基本等于公寓房型）
     * @param officeParamsVO
     */
    void add(OfficeParamsVO officeParamsVO);

    /**
     * 编辑办公室系列
     * @param officeParamsVO
     */
    void edit(OfficeParamsVO officeParamsVO);

    /**
     * 删除办公室系列，需要id
     * @param officeVO
     */
    void delete(OfficeVO officeVO);
}
