package com.gpdi.hqplus.officebuild.controller.manager;

import com.gpdi.hqplus.officebuild.entity.params.OfficeParamsVO;
import com.gpdi.hqplus.officebuild.service.OfficeService;
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
 * @Description 写字楼系列管理端功能
 * @Author wzr
 * @CreateDate 2019-07-10
 * @Time 18:00
 */
@Api(tags = "写字楼系列管理端功能")
@Slf4j
@RestController
@RequestMapping("/v1/manager/resources/office")
public class OfficeManagerController {

    @Autowired
    OfficeService officeService;


    @ApiOperation(value = "新增写字楼系列")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "写字楼系列名称",required = true,dataType = "String",paramType = "path"),
            @ApiImplicitParam(name = "pictures",value = "图片列表",required = true,dataType = "list",paramType = "path"),
            @ApiImplicitParam(name = "floor",value = "所属楼层",required = true,dataType = "String",paramType = "path"),
            @ApiImplicitParam(name = "unitCode",value = "所属单元、楼栋",required = true,dataType = "String",paramType = "path"),
            @ApiImplicitParam(name = "size",value = "面积（每层）",required = true,dataType = "String",paramType = "path"),
            @ApiImplicitParam(name = "equipmentIds",value = "拥有设备id列表",required = true,dataType = "list",paramType = "path"),
            @ApiImplicitParam(name = "unitPrice",value = "单层价格",required = true,dataType = "decimal",paramType = "path"),
            @ApiImplicitParam(name = "unitPriceType",value = "单层价格单位",required = true,dataType = "String",paramType = "path"),
            @ApiImplicitParam(name = "totalPrice",value = "总价",required = true,dataType = "decimal",paramType = "path"),
            @ApiImplicitParam(name = "totalPriceType",value = "总价价格单位",required = true,dataType = "String",paramType = "path"),
            @ApiImplicitParam(name = "workerNum",value = "工位数量",required = true,dataType = "String",paramType = "path"),
            @ApiImplicitParam(name = "saleStatus",value = "售卖状态",required = true,dataType = "String",paramType = "path"),
            @ApiImplicitParam(name = "decorationStatus",value = "装修标准",required = true,dataType = "String",paramType = "path"),
            @ApiImplicitParam(name = "propertyYears",value = "产品年限",required = true,dataType = "int",paramType = "path"),
            @ApiImplicitParam(name = "modelUrl",value = "户型图片",required = true,dataType = "String",paramType = "path"),
            @ApiImplicitParam(name = "description",value = "详情",required = false,dataType = "String",paramType = "path"),
            @ApiImplicitParam(name = "surrounding",value = "周边",required = false,dataType = "String",paramType = "path"),
            @ApiImplicitParam(name = "dynamics",value = "动态",required = false,dataType = "list",paramType = "path")
    })
    @PostMapping("/add")
    public void add(@RequestBody OfficeParamsVO officeParamsVO){
        officeService.add(officeParamsVO);
    }

}
