package com.gpdi.hqplus.pass.controller.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.common.validate.PageQueryValidate;
import com.gpdi.hqplus.common.validate.StringValidate;
import com.gpdi.hqplus.pass.entity.ProductPass;
import com.gpdi.hqplus.pass.query.ProductPassQuery;
import com.gpdi.hqplus.pass.service.IProductPassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zake
 * @Description: 管理端
 * @Date: Created in 14:14 2019-07-06
 **/
@Api
@Slf4j
@RestController
@RequestMapping("/v1/manager/resources/pass/productPass")
public class ProductManageController {

    @Autowired
    private IProductPassService passService;


    @ApiOperation(value = "物品放行提交申请记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "每页显示数", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "current", value = "当前页", required = true, dataType = "long", paramType = "path")
    })
    @PostMapping("/listAll")
    public Page listAll(@RequestBody ProductPassQuery query) {
        PageQueryValidate.check(query);
        return passService.listAll(query);
    }

    @ApiOperation(value = "物品放行提交申请审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productPassId", value = "物品放行提交id", required = true, dataType = "String", paramType = "path")
    })
    @GetMapping("/audit")
    public ProductPass audit(String productPassId) throws Exception {
        StringValidate.requireNotBlank(productPassId, "放行记录id不能为空");
        ProductPass productPass = passService.handUpdate(productPassId);
        return productPass;
    }


    @ApiOperation(value = "物品放行提交申请拒绝")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productPassId", value = "物品放行提交id", required = true, dataType = "String", paramType = "path")
    })
    @GetMapping("/auditFail")
    public String auditFail(String productPassId){
        StringValidate.requireNotBlank(productPassId, "放行记录id不能为空");
        passService.handFail(productPassId);
        return "ok";
    }


}
