package com.gpdi.hqplus.build.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.build.entity.Build;
import com.gpdi.hqplus.build.entity.query.BuildQuery;
import com.gpdi.hqplus.build.entity.vo.BuildVO;
import com.gpdi.hqplus.build.mapper.BuildAdminMapper;
import com.gpdi.hqplus.build.service.IBuildAdminService;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户端物业楼栋接口实现类
 * @Author qf
 * @Date 2019/7/18
 * @Version 1.0
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class BuildAdminServiceImpl extends ServiceImpl<BuildAdminMapper,Build> implements IBuildAdminService {

    @Autowired
    private IdGenerator idGenerator;

    /**
     * 添加楼栋
     * @param buildVO
     */
    @Override
    public void insertBuild(BuildVO buildVO) {
        Build build = new Build();
        if (buildVO.getId() == null) {
            //添加楼栋
            System.out.println("-----------");
            build.setId(idGenerator.getNumberId());
            build.setName(buildVO.getName());
            build.setParentId(0L);
            build.setType("build");
            build.setStatus("normal");
            int insert = baseMapper.insert(build);
            if (insert <= 0) {
                log.error("insert build fail.");
                throw new ApplicationException(ErrorCode.SERVICE_ERROR,"添加楼栋失败");
            }
        }

        //添加楼层
        build.setParentId(buildVO.getId());
        build.setId(idGenerator.getNumberId());
        build.setName(buildVO.getName());
        int result = baseMapper.insert(build);
        build.setStatus("normal");
        build.setType("floor");
        if (result <= 0) {
            log.error("insert build name fail.");
            throw new ApplicationException(ErrorCode.SERVICE_ERROR,"添加楼层失败");
        }
    }

    /**
     * 添加房间号
     * @param buildVO
     */
    @Override
    public void insertBuildUnit(BuildVO buildVO) {

        Build build = new Build();
        build.setParentId(buildVO.getId());
        build.setId(idGenerator.getNumberId());
        build.setName(buildVO.getName());
        int result = baseMapper.insert(build);
        build.setStatus("normal");
        build.setType("unit");
        if (result <= 0) {
            log.error("insert build name fail.");
            throw new ApplicationException(ErrorCode.SERVICE_ERROR,"添加房间号失败");
        }
    }

}
