package com.gpdi.hqplus.file.controller.app;

import com.gpdi.hqplus.common.anntation.IgnoreResponseAdvice;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.constants.RedisConstants;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.manager.FileService;
import com.gpdi.hqplus.common.manager.RedisService;
import com.gpdi.hqplus.common.util.ExceptionUtil;
import com.gpdi.hqplus.common.util.StringUtil;
import com.gpdi.hqplus.file.entity.FileResource;
import com.gpdi.hqplus.file.entity.vo.FileVO;
import com.gpdi.hqplus.file.service.IFileResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 文件控制器
 *
 * @author: lianghb
 * @create: 2019-07-01 16:49
 **/
@Api
@Slf4j
@RestController
@RequestMapping("/v1/app/file")
public class FileResourceController {
    @Autowired
    private IFileResourceService fileResourceService;
    @Autowired
    private FileService fileService;
    @Autowired
    private RedisService redisService;

    /**
     * 上传文件
     *
     * @param file
     */
    @ApiOperation(value = "上传文件", notes = "上传文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "file", required = true, dataType = "file", paramType = "path")
    })
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public FileVO upload(@RequestParam(name = "file", required = false) MultipartFile file, String fileName) {
        if (file == null) {
            throw new ApplicationException(ErrorCode.SERVICE_ERROR, "文件不能为空");
        }
        byte[] bytes;
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            log.error(ExceptionUtil.getMassage(e));
            throw new ApplicationException(ErrorCode.SERVICE_ERROR, "解析文件失败");
        }

        return fileResourceService.add(bytes, fileName);
    }

    /**
     * 获取文件资源
     *
     * @param id
     */
    @ApiOperation(value = "获取文件资源", notes = "通过 id 获取图文件资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
    })
    @GetMapping("/byId")
    @IgnoreResponseAdvice
    public void getById(String id, HttpServletResponse response) {
        if (StringUtil.isBlank(id)) {
            return;
        }

        FileResource fileResource = fileResourceService.getById(id);
        if (fileResource == null) {
            return;
        }

        writeByte(response, fileResource);
    }

    /**
     * 获取文件资源
     *
     * @param name
     */
    @ApiOperation(value = "获取文件资源", notes = "通过文件名获取文件资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "String", paramType = "path")
    })
    @GetMapping("/byName")
    @IgnoreResponseAdvice
    public void getByName(String name, HttpServletResponse response) {
        if (StringUtil.isBlank(name)) {
            return;
        }

        byte[] bytes = (byte[]) redisService.get(RedisConstants.FILE + name);
        if (bytes != null && bytes.length > 0) {
            writeByte(response, bytes, name);
        } else {
            FileResource fileResource = fileResourceService.getByName(name);
            if (fileResource == null) {
                return;
            }

            writeByte(response, fileResource);
        }
    }

    /**
     * 删除文件资源
     *
     * @param id
     */
    @ApiOperation(value = "删除文件资源", notes = "通过id删除文件资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "path")
    })
    @DeleteMapping("/byId")
    public String deleteById(String id) {
        if (StringUtil.isBlank(id)) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "id 不能为空");
        }

        fileResourceService.removeById(id);

        return "ok";
    }

    /**
     * 删除文件资源
     *
     * @param name
     */
    @ApiOperation(value = "获取文件资源", notes = "通过文件名获取图文件资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "String", paramType = "path")
    })
    @DeleteMapping("/byName")
    public String deleteByName(String name) {
        if (StringUtil.isBlank(name)) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "文件名不能为空");
        }

        FileResource fileResource = fileResourceService.getByName(name);
        if (fileResource != null) {
            fileResource.deleteById();
        }

        return "ok";
    }

    /**
     * 文件流输出
     *
     * @param response
     * @param fileResource
     */
    private void writeByte(HttpServletResponse response, FileResource fileResource) {
        Long id = fileResource.getId();
        byte[] bytes = (byte[]) redisService.get(RedisConstants.FILE + id);
        if (bytes == null || bytes.length == 0) {
            bytes = fileService.download(fileResource.getFilePath());
            if (bytes == null) {
                log.error("get file from FastDFS fail! id:{}", id);
                return;
            }
            redisService.set(RedisConstants.FILE + id, bytes, 7200);
        }

        writeByte(response, bytes, fileResource.getFileName());

    }

    /**
     * 文件流输出
     *
     * @param response
     * @param bytes
     * @param fileName
     */
    private void writeByte(HttpServletResponse response, byte[] bytes, String fileName) {
        try {
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error(ExceptionUtil.getMassage(e));
        }
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(bytes);
        } catch (IOException e) {
            log.error(ExceptionUtil.getMassage(e));
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                log.error(ExceptionUtil.getMassage(e));
            }
        }
    }
}
