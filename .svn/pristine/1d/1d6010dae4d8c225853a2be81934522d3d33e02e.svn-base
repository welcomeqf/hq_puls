package com.gpdi.hqplus.build.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.build.entity.Build;
import com.gpdi.hqplus.build.entity.query.BuildQuery;
import com.gpdi.hqplus.build.entity.query.BuildSubQuery;
import com.gpdi.hqplus.build.mapper.BuildMapper;
import com.gpdi.hqplus.build.service.IBuildService;
import com.gpdi.hqplus.common.util.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 物业楼栋的实现类
 *
 * @Author qf
 * @Date 2019/7/18
 * @Version 1.0
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class BuildServiceImpl extends ServiceImpl<BuildMapper, Build> implements IBuildService {


    /**
     * 查询楼栋,楼层,房间号
     *
     * @param id
     * @return
     */
    @Override
    public List<BuildQuery> queryBuild(Long id) {
        List<BuildQuery> result = new ArrayList<>();
        if (id == 0) {
            //查询所有楼栋
            LambdaQueryWrapper<Build> queryWrapper = new QueryWrapper<Build>().lambda()
                    .eq(Build::getParentId, 0);
            List<Build> builds = baseMapper.selectList(queryWrapper);
            for (Build build : builds) {
                BuildQuery query = new BuildQuery();
                query.setId(build.getId());
                query.setName(build.getName());
                result.add(query);
            }
            return result;
        }
        //查询楼层或者房间号
        LambdaQueryWrapper<Build> query = new QueryWrapper<Build>().lambda()
                .eq(Build::getParentId, id);
        List<Build> buildList = baseMapper.selectList(query);
        for (Build builds : buildList) {
            BuildQuery buildQuery = new BuildQuery();
            buildQuery.setName(builds.getName());
            buildQuery.setId(builds.getId());
            result.add(buildQuery);
        }
        return result;
    }


    @Override
    public List<BuildQuery> listSubById(BuildSubQuery query) {
        LambdaQueryWrapper<Build> queryWrapper = new QueryWrapper<Build>()
                .lambda()
                .orderByDesc(Build::getId);
        if (query.getId().equals(0L)) {
            queryWrapper.eq(Build::getType, query.getType());
        }

        queryWrapper.eq(Build::getParentId, query.getId());

        List<Build> buildList = baseMapper.selectList(queryWrapper);

        List<BuildQuery> result = CollectionUtil.createArrayList();
        for (Build build : buildList) {
            result.add(build2BuildQuery(build));
        }
        return result;
    }

    /**
     * Build 2 BuildQuery
     *
     * @param build
     * @return
     */
    private BuildQuery build2BuildQuery(Build build) {
        BuildQuery buildQuery = new BuildQuery();
        buildQuery.setName(build.getName());
        buildQuery.setId(build.getId());

        return buildQuery;
    }
}
