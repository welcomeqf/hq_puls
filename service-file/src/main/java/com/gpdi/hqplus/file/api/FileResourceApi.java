package com.gpdi.hqplus.file.api;

import com.gpdi.hqplus.file.entity.FileResource;
import com.gpdi.hqplus.file.service.IFileResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 文件api 接口
 *
 * @author: lianghb
 * @create: 2019-07-01 22:19
 **/
@Api
@Slf4j
@RestController
@RequestMapping("/v1/api/file")
public class FileResourceApi {

    @Autowired
    private IFileResourceService fileResourceService;

    /**
     * 根据资源 id 获取文件列表
     *
     * @param resourceId
     */
    @ApiOperation(value = "根据资源 id 获取文件列表", notes = "根据资源 id 获取文件列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "resourceId", value = "资源 id", required = true, dataType = "string", paramType = "path")
    })
    @GetMapping("/listByResourceId")
    public List<FileResource> listByResourceId(@RequestParam("resourceId") Long resourceId) {
        return fileResourceService.listByResource(resourceId);
    }

    /**
     * 根据资源 id 获取文件列表
     *
     * @param ids
     * @return
     */
    @ApiOperation(value = "根据资源 id 获取文件列表", notes = "根据资源 id 获取文件列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "resourceId", value = "资源 id", required = true, dataType = "string", paramType = "path")
    })
    @PostMapping("/listByIds")
    public Map<Long, List<FileResource>> listByIds(@RequestBody List<Long> ids) {
        return fileResourceService.listByResourceList(ids);
    }

    /**
     * 根据资源 id 关联文件
     *
     * @param resourceId
     */
    @ApiOperation(value = "根据资源 id 关联文件", notes = "根据资源 id 关联文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "resourceId", value = "资源 id", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "fileNames", value = "资源 id", required = true, dataType = "string[]", paramType = "path")
    })
    @GetMapping("/setByResourceId")
    public String setByResourceId(@RequestParam("resourceId") Long resourceId, @RequestParam("fileNames") String[] fileNames) {
        fileResourceService.setByResource(resourceId, fileNames);
        return "ok";
    }


    /**
     * 根据资源 id 获取与之关联的资源名称数组
     * @param resourceId
     * @return String[]
     */
    @ApiOperation(value = "根据资源 id 获取文件列表", notes = "根据资源 id 获取文件列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "resourceId", value = "资源 id", required = true, dataType = "string", paramType = "path")
    })
    @GetMapping("/getFileNamesById")
    public String[] getFileNamesById(@RequestParam("resourceId") Long resourceId) {
        return fileResourceService.getFileNamesById(resourceId);
    }

}
