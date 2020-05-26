package com.gpdi.hqplus.officebuild.controller.app;

import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.officebuild.entity.OfficeBookVO;
import com.gpdi.hqplus.officebuild.entity.OfficeVO;
import com.gpdi.hqplus.officebuild.service.OfficeBookService;
import com.gpdi.hqplus.officebuild.service.OfficeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 写字楼系列app端相关业务
 * @Author wzr
 * @CreateDate 2019-07-08
 * @Time 14:47
 */
@Api(tags = "写字楼系列app端相关业务")
@Slf4j
@RestController
@RequestMapping("/v1/app/resources/office")
public class OfficeAppController {

    @Autowired
    OfficeService officeService;

    @Autowired
    OfficeBookService officeBookService;

    @ApiOperation(value = "获取写字楼系列信息")
    @ApiImplicitParam(name = "id",value = "写字楼系列id",required = true,dataType = "long",paramType = "path")
    @PostMapping("/getById")
    public OfficeVO getById(@RequestBody OfficeVO officeVO){
        Long id = officeVO.getId();
        if (id!=null){
            return officeService.getOfficeById(id);
        }else {
            log.error("officeId为空");
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR,"写字楼系列id为空");
        }
    }

    @ApiOperation(value = "在线预约写字楼系列")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "officeId",value = "写字楼系列id",required = true,dataType = "long",paramType = "path"),
            @ApiImplicitParam(name = "name",value = "申请人姓名",required = true,dataType = "String",paramType = "path"),
            @ApiImplicitParam(name = "applyTime",value = "预约日期",required = true,dataType = "LocalDateTime",paramType = "path"),
            @ApiImplicitParam(name = "phone",value = "申请手机号",required = true,dataType = "String",paramType = "path"),
            @ApiImplicitParam(name = "message",value = "用户消息",dataType = "String",paramType = "path")
    })
    @PostMapping("/book")
    public void book(@RequestBody OfficeBookVO officeBookVO){
        officeBookService.book(officeBookVO);
    }
}
