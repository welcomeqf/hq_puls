package com.gpdi.hqplus.resources.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.common.util.CollectionUtil;
import com.gpdi.hqplus.common.util.StringUtil;
import com.gpdi.hqplus.resources.entity.SysBusinessFile;
import com.gpdi.hqplus.resources.mapper.SysBusinessFileMapper;
import com.gpdi.hqplus.resources.service.ISysBusinessFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzr
 * @since 2019-07-13
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysBusinessFileServiceImpl extends ServiceImpl<SysBusinessFileMapper, SysBusinessFile> implements ISysBusinessFileService {

    /**
     * 初始化数据库，根据code查询数据，将status都改为disable
     * @param code
     */
    @Override
    public void initDB(String code){
        //need lock
        List<SysBusinessFile> normalList = getNormalList(code);
        if (CollectionUtil.isNotEmpty(normalList)){
            for (SysBusinessFile sysBusinessFile:normalList) {
                sysBusinessFile.setStatus(SysBusinessFile.STATUS_DISABLED);
                baseMapper.updateById(sysBusinessFile);
            }
        }
    }

    /**
     * 获取状态为normal的记录
     * @param code
     * @return
     */
    @Override
    public List<SysBusinessFile> getNormalList(String code){
        LambdaQueryWrapper<SysBusinessFile> wrapper = new QueryWrapper<SysBusinessFile>()
                .lambda()
                .eq(SysBusinessFile::getStatus, SysBusinessFile.STATUS_NORMAL);
        if (StringUtil.isNotEmpty(code)){
            wrapper.eq(SysBusinessFile::getCode,code);
        }

        List<SysBusinessFile> sysBusinessFiles = baseMapper.selectList(wrapper);
        return sysBusinessFiles;
    }
}
