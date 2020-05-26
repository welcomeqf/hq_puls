package com.gpdi.hqplus.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.article.service.NoticeFileService;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.manager.RedisService;
import com.gpdi.hqplus.common.util.CollectionUtil;
import com.gpdi.hqplus.common.util.IdGenerator;
import com.gpdi.hqplus.common.util.JsonUtil;
import com.gpdi.hqplus.resources.entity.SysBusinessFile;
import com.gpdi.hqplus.resources.feign.FileFeignClient;
import com.gpdi.hqplus.resources.service.impl.SysBusinessFileServiceImpl;
import com.gpdi.hqplus.resources.validate.CheckEmptyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 入驻申请须知管理service
 * @Author wzr
 * @CreateDate 2019-07-13
 * @Time 10:43
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeFileServiceImpl extends SysBusinessFileServiceImpl implements NoticeFileService {

    @Autowired
    FileFeignClient fileFeignClient;

    @Autowired
    IdGenerator idGenerator;

    @Autowired
    RedisService redisService;

    /**
     * 前端返回文件id，保存到数据库中
     * @param sysBusinessFile
     * @return
     */
    @Override
    public void uploadFile(SysBusinessFile sysBusinessFile){
        //查询数据库，将状态为normal的数据改为disable
        String code = sysBusinessFile.getCode();
        //请求的Code不同，业务不同
        initDB(code);
        //校验参数
        String[] params = {"name","fileId"};
        CheckEmptyUtil.check(JsonUtil.bean2Map(sysBusinessFile),params);
        //新增的文件默认为可用状态
        sysBusinessFile.setStatus(SysBusinessFile.STATUS_NORMAL);
        sysBusinessFile.setId(idGenerator.getNumberId());
        baseMapper.insert(sysBusinessFile);
    }

    /**
     * 返回文件id
     * 状态为可用的记录最多只有一条，返回该条
     * 若都为不可用状态，则返回最新的记录
     *
     * @return
     */
    @Override
    public Long getFileId(String code) {
        //查询状态启用的数据
        List<SysBusinessFile> normalList = getNormalList(code);
        //多于一条报错
        if (normalList.size()>1){
            log.error("数据错误，查询到多条状态启用的图片");
            throw new ApplicationException(ErrorCode.UNKNOWN_ERROR);
        }
        //没有启用状态的，查询所有未启用状态的，返回创建时间最新的数据
        if (CollectionUtil.isEmpty(normalList)){
            List<SysBusinessFile> sysBusinessFiles = baseMapper.selectList(new QueryWrapper<SysBusinessFile>()
                    .lambda()
                    .eq(SysBusinessFile::getCode, code)
                    .eq(SysBusinessFile::getStatus, SysBusinessFile.STATUS_DISABLED)
                    .orderByDesc(SysBusinessFile::getCreateTime)
            );
            if (CollectionUtil.isEmpty(sysBusinessFiles)){
                log.warn("图片查询为空");
                return null;
            }
            return sysBusinessFiles.get(0).getFileId();
        }else{
            //否则返回启用数据
            return normalList.get(0).getFileId();
        }
    }

    /**
     * 分页查询文件列表
     *
     * @param page
     * @return
     */
    @Override
    public Page listByPage(Page page,String code) {
        baseMapper.selectPage(page,new QueryWrapper<SysBusinessFile>()
                .lambda()
                .eq(SysBusinessFile::getCode,code)
                .orderByDesc(SysBusinessFile::getStatus)
                .orderByDesc(SysBusinessFile::getCreateTime)
        );
        return page;
    }

    /**
     * 编辑文件
     *
     * @param sysBusinessFile
     */
    @Override
    public void edit(SysBusinessFile sysBusinessFile) {
        String[] params = {"fileId","id","name","status"};
        CheckEmptyUtil.check(JsonUtil.bean2Map(sysBusinessFile),params);

        baseMapper.updateById(sysBusinessFile);
    }

    /**
     * 启用或禁用文件
     * @param sysBusinessFile
     * @return
     */
    @Override
    public String disableOrUseById(SysBusinessFile sysBusinessFile) {
        SysBusinessFile businessFile = baseMapper.selectById(sysBusinessFile.getId());
        SysBusinessFile businessFileNormal = baseMapper.selectOne(new QueryWrapper<SysBusinessFile>()
                .lambda()
                .eq(SysBusinessFile::getCode,businessFile.getCode())
                .eq(SysBusinessFile::getStatus, SysBusinessFile.STATUS_NORMAL)
        );
        if (businessFileNormal != null){
            businessFileNormal.setStatus(SysBusinessFile.STATUS_DISABLED);
            baseMapper.updateById(businessFileNormal);
        }
        Integer integer = baseMapper.updateById(sysBusinessFile);
        if (integer == 1 ){
            return "ok";
        }else {
            log.error("启用或禁用文件失败；id="+sysBusinessFile.getId().toString()+";status = "+sysBusinessFile.getStatus());
            throw new ApplicationException(ErrorCode.SERVICE_ERROR,"启用或禁用文件失败!");
        }
    }
}
