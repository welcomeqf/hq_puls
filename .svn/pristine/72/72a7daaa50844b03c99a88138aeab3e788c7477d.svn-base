package com.gpdi.hqplus.resources.feign;

import com.gpdi.hqplus.article.entity.vo.SettleFileVO;
import com.gpdi.hqplus.common.entity.vo.JsonResultVO;
import com.gpdi.hqplus.resources.feign.fallback.FileFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: lianghb
 * @create: 2019-05-23 10:28
 **/
@FeignClient(value = "service-file", fallback = FileFeignClientFallback.class)
public interface FileFeignClient {

    // fixme 新增 api 接口，可根据 id 列表一次性获取多个文件信息

    /**
     * 根据资源 id 获取文件列表
     *
     * @param resourceId
     * @return
     */
    @RequestMapping("/v1/api/file/listByResourceId")
    JsonResultVO listByResourceId(@RequestParam("resourceId") String resourceId);

    /**
     * 根据资源 id 设置文件
     *
     * @param resourceId
     * @param fileNames
     * @return
     */
    @RequestMapping("/v1/api/file/setByResourceId")
    JsonResultVO setByResourceId(@RequestParam("resourceId") Long resourceId, @RequestParam("fileNames") String[] fileNames);


    /**
     * 根据资源 id 获取与之关联的资源名称数组
     * @param resourceId
     * @return String[]
     */
    @RequestMapping("/v1/api/file/getFileNamesById")
    JsonResultVO getFileNamesById(@RequestParam("resourceId") String resourceId);

}
