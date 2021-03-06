package com.gpdi.hqplus.build.controller.web;

import com.gpdi.hqplus.build.entity.query.BuildQuery;
import com.gpdi.hqplus.build.entity.vo.BuildVO;
import com.gpdi.hqplus.build.service.IBuildAdminService;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author qf
 * @Date 2019/7/19
 * @Version 1.0
 */
@Api
@Slf4j
@RestController
@RequestMapping("/v1/web/resources/build")
public class BuildAdminController {

    @Autowired
    private IBuildAdminService userBuildService;

    /**
     * 添加楼栋以及楼层
     * @param buildVO
     * @return
     */
    @ApiOperation(value = "添加楼栋以及楼层门牌号", notes = "添加楼栋以及楼层门牌号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "name", value = "楼栋", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "bName", value = "楼层", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "buildName", value = "房间号", required = true, dataType = "string", paramType = "path")
    })
    @RequestMapping("/add")
    public String insertBuild(@RequestBody BuildVO buildVO){
        if (StringUtil.isBlank(buildVO.getName())) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "参数异常~");
        }
        userBuildService.insertBuild(buildVO);
        return "ok";
    }

    /**
     * 添加房间号
     * @param buildVO
     * @return
     */
    @ApiOperation(value = "添加门牌号", notes = "添加门牌号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "name", value = "楼栋", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "bName", value = "楼层", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "buildName", value = "房间号", required = true, dataType = "string", paramType = "path")
    })
    @RequestMapping("/addUnit")
    public String insertBuildUnit(@RequestBody BuildVO buildVO){
        if (StringUtil.isBlank(buildVO.getName())) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "参数异常~");
        }
        userBuildService.insertBuild(buildVO);
        return "ok";
    }
}
