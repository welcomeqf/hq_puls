package com.gpdi.hqplus.build.service;

import com.gpdi.hqplus.build.entity.query.BuildQuery;
import com.gpdi.hqplus.build.entity.query.BuildSubQuery;

import java.util.List;

/**
 * 物业楼栋的接口
 *
 * @Author qf
 * @Date 2019/7/18
 * @Version 1.0
 */
public interface IBuildService {

    /**
     * 查询楼栋,楼层,房间号
     *
     * @param id
     * @return
     */
    List<BuildQuery> queryBuild(Long id);

    /**
     * 获取子楼栋信息
     *
     * @param query
     * @return
     */
    List<BuildQuery> listSubById(BuildSubQuery query);

}
