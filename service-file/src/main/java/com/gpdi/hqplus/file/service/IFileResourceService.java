package com.gpdi.hqplus.file.service;

import com.gpdi.hqplus.file.entity.FileResource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gpdi.hqplus.file.entity.vo.FileVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文件资源 服务类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-01
 */
public interface IFileResourceService extends IService<FileResource> {
    /**
     * 保存文件
     *
     * @param file
     * @param fileName
     * @return
     */
    FileVO add(byte[] file, String fileName);

    /**
     * 通过文件名获取文件信息
     *
     * @param name
     * @return
     */
    FileResource getByName(String name);

    /**
     * 通过资源 id 查询文件列表
     *
     * @param resourceId
     * @return
     */
    List<FileResource> listByResource(Long resourceId);

    /**
     * 通过资源 id 查询文件列表
     *
     * @param resourceId
     * @return
     */
    Map<Long,List<FileResource>> listByResourceList(List<Long> resourceId);

    /**
     *
     * @param resourceId
     * @param fileNames
     */
    void setByResource(Long resourceId,String[] fileNames);

    /**
     * 根据资源 id 获取与之关联的资源名称数组
     * @param resourceId
     * @return String[]
     */
    String[] getFileNamesById(Long resourceId);
}
