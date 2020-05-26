package com.gpdi.hqplus.officebuild.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.officebuild.entity.OfficeBookVO;

/**
 * @Description 写字楼系列预约事项
 * @Author wzr
 * @CreateDate 2019-07-10
 * @Time 14:52
 */
public interface OfficeBookService {

    /**
     * 预约写字楼系列
     * @param officeBookVO
     */
    void book(OfficeBookVO officeBookVO);

    /*以上为app端可使用功能*/

    /**
     * 根据写字楼系列id分页查询预约记录
     * @param page
     * @return
     */
    Page listByPageAndOfficeId(Page<OfficeBookVO> page);
}
