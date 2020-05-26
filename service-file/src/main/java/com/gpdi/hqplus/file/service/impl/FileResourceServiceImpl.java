package com.gpdi.hqplus.file.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.manager.FileService;
import com.gpdi.hqplus.common.util.BeanPowerHelper;
import com.gpdi.hqplus.common.util.CollectionUtil;
import com.gpdi.hqplus.common.util.IdGenerator;
import com.gpdi.hqplus.common.util.StringUtil;
import com.gpdi.hqplus.file.entity.FileResource;
import com.gpdi.hqplus.file.entity.vo.FileVO;
import com.gpdi.hqplus.file.mapper.FileResourceMapper;
import com.gpdi.hqplus.file.service.IFileResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文件资源 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-01
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class FileResourceServiceImpl extends ServiceImpl<FileResourceMapper, FileResource> implements IFileResourceService {

    @Autowired
    private FileService fileService;
    @Autowired
    private IdGenerator idGenerator;

    @Override
    public FileVO add(byte[] file, String fileName) {
        String fileType = fileName.substring(fileName.indexOf('.') + 1);
        fileName = idGenerator.getUuid() + "." + fileType;

        String path = fileService.upload(fileName, file);
        if (StringUtil.isBlank(path)) {
            throw new ApplicationException(ErrorCode.SERVICE_ERROR, "上传文件失败");
        }

        FileResource fileResource = new FileResource();
        fileResource.setId(idGenerator.getNumberId());
        fileResource.setFileType(fileType);
        fileResource.setFileName(fileName);
        fileResource.setFilePath(path);
        fileResource.setFileSize(Long.valueOf(file.length));
        fileResource.setStatus(FileResource.STATUS_NORMAL);

        boolean insert = fileResource.insert();
        if (!insert) {
            fileService.delete(path);
            throw new ApplicationException(ErrorCode.SERVICE_ERROR, "上传文件失败");
        }

        return BeanPowerHelper.mapCompleteOverrider(fileResource, FileVO.class);
    }

    @Override
    public FileResource getByName(String name) {
        return baseMapper.selectOne(new QueryWrapper<FileResource>()
                .lambda()
                .eq(FileResource::getFileName, name)
                .eq(FileResource::getStatus, FileResource.STATUS_NORMAL)
        );
    }

    @Override
    public List<FileResource> listByResource(Long resourceId) {
        return baseMapper.selectList(new QueryWrapper<FileResource>()
                .lambda()
                .eq(FileResource::getParentId, resourceId)
                .eq(FileResource::getStatus, FileResource.STATUS_NORMAL)
                .orderByDesc(FileResource::getSerialNumber)
        );
    }

    @Override
    public Map<Long, List<FileResource>> listByResourceList(List<Long> resourceId) {
        List<FileResource> list = baseMapper.selectList(new QueryWrapper<FileResource>()
                .lambda()
                .in(FileResource::getParentId, resourceId)
                .eq(FileResource::getStatus, FileResource.STATUS_NORMAL)
                .orderByDesc(FileResource::getSerialNumber)
        );
        if (CollectionUtil.isEmpty(list)) {
            return CollectionUtil.createHashMap();
        }

        Map<Long, List<FileResource>> map = CollectionUtil.createHashMap();
        for (FileResource fileResource : list) {
            List<FileResource> resourceList = map.get(fileResource.getId());
            if (resourceList == null) {
                resourceList = CollectionUtil.createArrayList();
                map.put(fileResource.getId(), resourceList);
            }

            resourceList.add(fileResource);
        }

        return map;
    }

    @Override
    public void setByResource(Long resourceId, String[] fileNames) {
        int update = baseMapper.update(new FileResource().setParentId(resourceId), new QueryWrapper<FileResource>()
                .lambda()
                .in(FileResource::getFileName, fileNames)
        );
        if (update != fileNames.length) {
            log.error("update file resource fail");
            throw new ApplicationException(ErrorCode.SERVICE_ERROR, "更新文件失败");
        }
    }

    /**
     * 根据资源 id 获取与之关联的资源名称数组
     * @param resourceId
     * @return String[]
     */
    @Override
    public String[] getFileNamesById(Long resourceId) {
        List<FileResource> fileResourceList = baseMapper.selectList(new QueryWrapper<FileResource>()
                .lambda()
                .eq(FileResource::getParentId, resourceId)
                .eq(FileResource::getStatus, FileResource.STATUS_NORMAL)
                .orderByDesc(FileResource::getSerialNumber)
        );
        String[] fileNames = new String[fileResourceList.size()];
        for (int i = 0; i < fileResourceList.size(); i++){
            fileNames[i] = fileResourceList.get(i).getFileName();
        }
        return fileNames;
    }
}
