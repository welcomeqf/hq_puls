package com.gpdi.hqplus.resources.service;

import com.gpdi.hqplus.resources.entity.SysBusinessFile;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzr
 * @since 2019-07-13
 */
public interface ISysBusinessFileService extends IService<SysBusinessFile> {

    /**
     * 初始化数据库，根据code查询数据，将status都改为disable
     * @param code
     */
    void initDB(String code);

    /**
     * 获取状态为normal的记录
     * @param code
     * @return
     */
    List<SysBusinessFile> getNormalList(String code);

}
