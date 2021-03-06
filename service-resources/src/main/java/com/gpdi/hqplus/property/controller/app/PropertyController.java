package com.gpdi.hqplus.property.controller.app;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.property.entity.query.PropertyMaintainApplyQuery;
import com.gpdi.hqplus.property.entity.vo.PropertyMaintainVO;
import com.gpdi.hqplus.property.service.IPropertyService;
import com.gpdi.hqplus.property.service.IPropertyTypeService;
import com.gpdi.hqplus.property.validate.PropertyValidate;
import com.gpdi.hqplus.resources.entity.PropertyMaintenanceApply;
import com.gpdi.hqplus.resources.entity.PropertyMaintenanceType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物业报修
 *
 * @author: lianghb
 * @create: 2019-07-03 14:04
 **/
@Api
@Slf4j
@RestController
@RequestMapping("/v1/app/resources/property")
public class PropertyController {
    @Autowired
    private IPropertyService propertyService;
    @Autowired
    private IPropertyTypeService propertyTypeService;

    /**
     * <p>
     * 申请物业报修
     * </p>
     */
    @ApiOperation(value = "申请物业报修", notes = "申请物业报修")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "维修类型", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "address", value = "地址", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "userName", value = "姓名", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "userConnect", value = "联系方式", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "message", value = "留言", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "files", value = "文件名称列表", required = true, dataType = "string[]", paramType = "path")
    })
    @PostMapping("/maintain")
    public String apply(@RequestBody PropertyMaintainApplyQuery query) {
        PropertyValidate.checkApply(query);
        propertyService.apply(query);
        return "ok";
    }

    /**
     * <p>
     * 物业报修反馈
     * </p>
     */
    @ApiOperation(value = "物业报修反馈", notes = "物业报修反馈")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "feedback", value = "反馈", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "amount", value = "金额", required = true, dataType = "string", paramType = "path")
    })
    @PostMapping("/feedback")
    public String feedback(@RequestBody PropertyMaintainApplyQuery query) {
        propertyService.feedback(query);
        return "ok";
    }

    /**
     * <p>
     * 物业报修类型
     * </p>
     */
    @ApiOperation(value = "物业报修类型", notes = "物业报修类型")
    @GetMapping("/listMaintainType")
    public List<PropertyMaintenanceType> listMaintainType() {
        return propertyTypeService.listType();
    }


    /**
     * <p>
     * 分页获取个人报修记录-已完成
     * </p>
     */
    @ApiOperation(value = "个人报修记录", notes = "个人报修记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "每页显示条数", required = true, dataType = "string", paramType = "path")
    })
    @PostMapping("/pageByUserForFinish")
    public Page<PropertyMaintainVO> pageByUserForFinish(@RequestBody Page<PropertyMaintenanceApply> page) {
        // id 倒序
        page.setDesc("id");
        return propertyService.pageByUserForFinish(page);
    }

    /**
     * <p>
     * 分页获取个人报修记录-进行中
     * </p>
     */
    @ApiOperation(value = "个人报修记录", notes = "个人报修记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "每页显示条数", required = true, dataType = "string", paramType = "path")
    })
    @PostMapping("/pageByUserForProcessing")
    public Page<PropertyMaintainVO> pageByUserForProcessing(@RequestBody Page<PropertyMaintenanceApply> page) {
        // id 倒序
        page.setDesc("id");
        return propertyService.pageByUserForProcessing(page);
    }
}
