package com.gpdi.hqplus.user.controller.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.common.validate.PageQueryValidate;
import com.gpdi.hqplus.user.entity.query.PermissionListQuery;
import com.gpdi.hqplus.user.entity.query.RoleListQuery;
import com.gpdi.hqplus.user.entity.vo.PermissionListVO;
import com.gpdi.hqplus.user.service.IPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: lianghb
 * @create: 2019-07-01 23:41
 **/
@Api
@Slf4j
@RestController
@RequestMapping("/v1/manager/user/permission")
@PreAuthorize("hasRole('admin')")
public class PermissionManagerController {
    @Autowired
    private IPermissionService permissionService;

    /**
     * 分页获取权限列表
     *
     * @param query
     */
    @ApiOperation(value = "分页获取权限列表", notes = "分页获取权限列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "params.name", value = "权限名称", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "params.code", value = "权限代码", required = true, dataType = "string", paramType = "path")
    })
    @GetMapping("/list")
    public Page<PermissionListVO> listRole(@RequestBody PermissionListQuery query) {
        PageQueryValidate.check(query);
        if (query.getParams() == null) {
            query.setParams(new RoleListQuery.Params());
        }
        return permissionService.listPermission(query);
    }

    /**
     * 获取权限集合
     * @return
     */
    @ApiOperation(value = "获取权限集合", notes = "获取权限集合")
    @ApiImplicitParams({
    })
    @GetMapping("/permissionList")
    public List<PermissionListVO> listRole() {
        return permissionService.getPermissionList();
    }
}
