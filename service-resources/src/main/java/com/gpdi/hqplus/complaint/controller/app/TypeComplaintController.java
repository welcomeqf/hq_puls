package com.gpdi.hqplus.complaint.controller.app;

import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.StringUtil;
import com.gpdi.hqplus.complaint.entity.TypeComplaint;
import com.gpdi.hqplus.complaint.entity.query.TypeComplaintManageQuery;
import com.gpdi.hqplus.complaint.entity.query.TypeComplaintQuery;
import com.gpdi.hqplus.complaint.service.ITypeComplaintService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 意见反馈分类控制器
 * @Author qf
 * @Date 2019/7/16
 * @Version 1.0
 */
@Api
@Slf4j
@RestController
@RequestMapping("/v1/app/resources/typeComplaint")
public class TypeComplaintController {

    @Autowired
    private ITypeComplaintService typeComplaintService;

    /**
     * 增加分类
     * @param ty
     * @return
     */
    @ApiOperation(value = "增加分类",notes = "增加分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "意见分类名称", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "limitDay", value = "限制时间天数", required = true, dataType = "int", paramType = "path")
    })
    @RequestMapping("/insert")
    public String insertType(@RequestBody TypeComplaintQuery ty){
        if (StringUtil.isBlank(ty.getName()) || ty.getLimitDay() ==null) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR,"接收的参数不能为空");
        }
        typeComplaintService.insertType(ty);
        return "ok";
    }

    /**
     * 根据Id删除分类
     * @param id
     * @return
     */
    @ApiOperation(value = "删除分类", notes = "删除分类")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "string", paramType = "path")
    @GetMapping("/delete/{id}")
    public String deleteType(@PathVariable("id") Long id){
        if (id == null) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR,"收入的id不能为空");
        }
        typeComplaintService.deleteTypeById(id);
        return "ok";
    }

    /**
     * 修改分类
     * @param typeComplaintQuery
     * @return
     */
    @ApiOperation(value = "修改分类", notes = "修改分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "name", value = "意见分类名称", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "limitDay", value = "限制时间天数", required = true, dataType = "int", paramType = "path")
    })
    @RequestMapping("/update")
    public String updateType(@RequestBody TypeComplaintQuery typeComplaintQuery){
        if (typeComplaintQuery.getId() == null) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR,"接收的参数不能为空");
        }

        typeComplaintService.updateTypeById(typeComplaintQuery);
        return "ok";
    }

    /**
     * 查询所有分类
     * @return
     */
    @ApiOperation(value = "查询所有分类", notes = "查询所有分类")
    @RequestMapping("/list")
    public List<TypeComplaintManageQuery> listType(){
        List<TypeComplaintManageQuery> typeComplaints = typeComplaintService.listType();

        return typeComplaints;
    }

    /**
     * 查询单条分类记录
     * @param id
     * @return
     */
    @ApiOperation(value = "查询单条分类记录", notes = "查询单条分类记录")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "string", paramType = "path")
    @GetMapping("/query/{id}")
    public TypeComplaintManageQuery queryTypeById(@PathVariable("id") Long id){
        if (id == null) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "输入的参数有误");
        }

        TypeComplaintManageQuery typeComplaint = typeComplaintService.queryTypeById(id);

        return typeComplaint;
    }
}
