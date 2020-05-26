package com.gpdi.hqplus.article.controller.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.article.Constants.FileCodeConstants;
import com.gpdi.hqplus.article.service.NoticeFileService;
import com.gpdi.hqplus.common.validate.ObjectValidate;
import com.gpdi.hqplus.resources.entity.SysBusinessFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 入驻申请须知管理controller
 * @Author wzr
 * @CreateDate 2019-07-13
 * @Time 15:35
 */
@Api(value = "入驻申请须知和申请表")
@Slf4j
@RestController
@RequestMapping("/v1/web/resources/article/settleApplicationNotice")
public class NoticeFileManagerController {

    @Autowired
    NoticeFileService noticeService;

    /*********************************************入驻申请须知图片*********************************************/

    @ApiOperation("上传申请须知图片")
    @ApiImplicitParams({
            @ApiImplicitParam (value = "fileId",name = "入驻申请须知图片id",required = true,dataType = "long",paramType = "path"),
            @ApiImplicitParam (value = "name",name = "入驻申请须知图片name",required = true,dataType = "String",paramType = "path"),
    })
    @PostMapping("/uploadSettleImage")
    public void uploadSettleImage(@RequestBody SysBusinessFile sysBusinessFile){
        sysBusinessFile.setCode(FileCodeConstants.SETTLE_NOTICE_IMAGE_CODE);
        noticeService.uploadFile(sysBusinessFile);
    }

    @ApiOperation("获取当前使用的入驻申请须知图片id")
    @PostMapping("/getSettleImageId")
    public Long getSettleImageId(){
        return noticeService.getFileId(FileCodeConstants.SETTLE_NOTICE_IMAGE_CODE);
    }

    @ApiOperation("分页获取入驻申请须知图片列表")
    @PostMapping("/listSettleImageByPage")
    public Page listSettleImageByPage(Page page){
        return noticeService.listByPage(page,FileCodeConstants.SETTLE_NOTICE_IMAGE_CODE);
    }


    /*********************************************入驻申请excel表*********************************************/

    @ApiOperation("上传申请表excel")
    @ApiImplicitParams({
            @ApiImplicitParam (value = "fileId",name = "入驻申请须知图片id",required = true,dataType = "long",paramType = "path"),
            @ApiImplicitParam (value = "name",name = "入驻申请须知图片name",required = true,dataType = "String",paramType = "path"),
    })
    @PostMapping("/uploadSettleExcel")
    public void uploadSettleExcel(@RequestBody SysBusinessFile sysBusinessFile){
        sysBusinessFile.setCode(FileCodeConstants.SETTLE_APPLICATION_EXCEL_CODE);
        noticeService.uploadFile(sysBusinessFile);
    }

    @ApiOperation("获取当前使用的入驻申请表id")
    @PostMapping("/getSettleExcelId")
    public Long getSettleExcelId(){
        return noticeService.getFileId(FileCodeConstants.SETTLE_APPLICATION_EXCEL_CODE);
    }

    @ApiOperation("分页获取入驻申请表文件列表")
    @PostMapping("/listSettleExcelByPage")
    public Page listSettleExcelByPage(Page page){
        return noticeService.listByPage(page,FileCodeConstants.SETTLE_APPLICATION_EXCEL_CODE);
    }

    /*********************************************装修须知图片*********************************************/

    @ApiOperation("上传装修须知图片")
    @ApiImplicitParams({
            @ApiImplicitParam (value = "fileId",name = "装修须知图片id",required = true,dataType = "long",paramType = "path"),
            @ApiImplicitParam (value = "name",name = "装修须知图片name",required = true,dataType = "String",paramType = "path"),
    })
    @PostMapping("/uploadDecorationImage")
    public void uploadDecorationImage(@RequestBody SysBusinessFile sysBusinessFile){
        sysBusinessFile.setCode(FileCodeConstants.PROPERTY_DECORATION_NOTICE_IMAGE_CODE);
        noticeService.uploadFile(sysBusinessFile);
    }

    @ApiOperation("获取当前使用的入驻申请须知图片id")
    @PostMapping("/getDecorationImageId")
    public Long getDecorationNoticeImageId(){
        return noticeService.getFileId(FileCodeConstants.PROPERTY_DECORATION_NOTICE_IMAGE_CODE);
    }

    @ApiOperation("分页获取入驻申请须知图片列表")
    @PostMapping("/listDecorationImageByPage")
    public Page listDecorationImageByPage(Page page){
        return noticeService.listByPage(page,FileCodeConstants.PROPERTY_DECORATION_NOTICE_IMAGE_CODE);
    }

    /*********************************************写字楼首页图片*********************************************/

    @ApiOperation("上传写字楼首页图片")
    @ApiImplicitParams({
            @ApiImplicitParam (value = "fileId",name = "写字楼首页图片id",required = true,dataType = "long",paramType = "path"),
            @ApiImplicitParam (value = "name",name = "写字楼首页图片name",required = true,dataType = "String",paramType = "path"),
    })
    @PostMapping("/uploadOfficeIndexImage")
    public void uploadOfficeIndexImage(@RequestBody SysBusinessFile sysBusinessFile){
        sysBusinessFile.setCode(FileCodeConstants.OFFICE_INDEX_IMAGE_CODE);
        noticeService.uploadFile(sysBusinessFile);
    }

    @ApiOperation("获取当前使用的写字楼首页图片id")
    @PostMapping("/getOfficeIndexImageId")
    public Long getOfficeIndexImageId(){
        return noticeService.getFileId(FileCodeConstants.OFFICE_INDEX_IMAGE_CODE);
    }

    @ApiOperation("分页获取写字楼首页图片列表")
    @PostMapping("/listOfficeIndexImageByPage")
    public Page listOfficeIndexImageByPage(Page page){
        return noticeService.listByPage(page,FileCodeConstants.OFFICE_INDEX_IMAGE_CODE);
    }

    /*********************************************写字楼招商图片*********************************************/

    @ApiOperation("上传写字楼招商图片")
    @ApiImplicitParams({
            @ApiImplicitParam (value = "fileId",name = "写字楼招商图片id",required = true,dataType = "long",paramType = "path"),
            @ApiImplicitParam (value = "name",name = "写字楼招商图片name",required = true,dataType = "String",paramType = "path"),
    })
    @PostMapping("/uploadOfficeInviteBusinessImage")
    public void uploadOfficeInviteBusinessImage(@RequestBody SysBusinessFile sysBusinessFile){
        sysBusinessFile.setCode(FileCodeConstants.OFFICE_INVITE_BUSINESS_IMAGE_CODE);
        noticeService.uploadFile(sysBusinessFile);
    }

    @ApiOperation("获取当前使用的写字楼招商图片id")
    @PostMapping("/getOfficeInviteBusinessImageId")
    public Long getOfficeInviteBusinessImageId(){
        return noticeService.getFileId(FileCodeConstants.OFFICE_INVITE_BUSINESS_IMAGE_CODE);
    }

    @ApiOperation("分页获取写字楼招商图片列表")
    @PostMapping("/listOfficeInviteBusinessImageByPage")
    public Page listOfficeInviteBusinessImageByPage(Page page){
        return noticeService.listByPage(page,FileCodeConstants.OFFICE_INVITE_BUSINESS_IMAGE_CODE);
    }

    /*********************************************写字楼预约图片*********************************************/

    @ApiOperation("上传写字楼预约图片")
    @ApiImplicitParams({
            @ApiImplicitParam (value = "fileId",name = "写字楼预约图片id",required = true,dataType = "long",paramType = "path"),
            @ApiImplicitParam (value = "name",name = "写字楼预约图片name",required = true,dataType = "String",paramType = "path"),
    })
    @PostMapping("/uploadOfficeBookImage")
    public void uploadOfficeBookImage(@RequestBody SysBusinessFile sysBusinessFile){
        sysBusinessFile.setCode(FileCodeConstants.OFFICE_BOOK_IMAGE_CODE);
        noticeService.uploadFile(sysBusinessFile);
    }

    @ApiOperation("获取当前使用的写字楼预约图片id")
    @PostMapping("/getOfficeBookImageId")
    public Long getOfficeBookImageId(){
        return noticeService.getFileId(FileCodeConstants.OFFICE_BOOK_IMAGE_CODE);
    }

    @ApiOperation("分页获取写字楼预约图片列表")
    @PostMapping("/listOfficeBookImageByPage")
    public Page listOfficeBookImageByPage(Page page){
        return noticeService.listByPage(page,FileCodeConstants.OFFICE_BOOK_IMAGE_CODE);
    }

    /*********************************************通用*********************************************/
    @ApiOperation(value = "编辑文件简单信息")
    @PostMapping("/edit")
    public void edit(@RequestBody SysBusinessFile sysBusinessFile){
        noticeService.edit(sysBusinessFile);
    }

    /**
     * 启用或禁用文件
     * @param sysBusinessFile
     * @return
     */
    @ApiOperation(value = "启用或禁用文件")
    @PostMapping("/disableOrUse")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "status", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "long", paramType = "path")
    })
    public String disableOrUseById(@RequestBody SysBusinessFile sysBusinessFile){
        ObjectValidate.requireNotNull(sysBusinessFile.getId(), "id不能为空");
        ObjectValidate.requireNotNull(sysBusinessFile.getStatus(), "状态不能为空");
        return noticeService.disableOrUseById(sysBusinessFile);
    }
}
