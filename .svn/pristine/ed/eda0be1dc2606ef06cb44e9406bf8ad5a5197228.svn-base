package com.gpdi.hqplus.user.controller.web;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.common.util.PageUtil;
import com.gpdi.hqplus.common.validate.ObjectValidate;
import com.gpdi.hqplus.common.validate.PageQueryValidate;
import com.gpdi.hqplus.common.validate.StringValidate;
import com.gpdi.hqplus.user.entity.User;
import com.gpdi.hqplus.user.entity.query.RegisterQuery;
import com.gpdi.hqplus.user.entity.query.UserListQuery;
import com.gpdi.hqplus.user.entity.vo.UserBusinessVO;
import com.gpdi.hqplus.user.entity.vo.UserExtendVO;
import com.gpdi.hqplus.user.entity.vo.UserListVO;
import com.gpdi.hqplus.user.service.IUserService;
import com.gpdi.hqplus.user.validate.UserValidate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author: lianghb
 * @create: 2019-07-01 16:45
 **/
@Api
@Slf4j
@RestController
@RequestMapping("/v1/manager/user")
@PreAuthorize("hasRole('admin')")
public class UserManagerController {

    @Autowired
    private IUserService userService;

    /**
     * 分页获取个体用户列表
     *
     * @param query
     */
    @ApiOperation(value = "分页获取用户列表", notes = "个体用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "params.phone", value = "电话", required = true, dataType = "string", paramType = "path")
    })
    @PostMapping("/listNormal")
    public PageUtil<UserListVO> listNormal(@RequestBody UserListQuery query) {
        PageQueryValidate.check(query);
        query.getParams().setUserType(User.USER_TYPE_NORMAL);
        PageUtil<UserListVO> page = new PageUtil();
        page.setSize(query.getSize());
        page.setCurrent(query.getCurrent());
        page.setParams(query.getParams());
        return userService.listNormal(page);
    }

    /**
     * 分页获取运营用户列表
     *
     * @param query
     */
    @ApiOperation(value = "分页获取用户列表", notes = "业务用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "params.phone", value = "电话", required = true, dataType = "string", paramType = "path")
    })
    @PostMapping("/listBusiness")
    public Page<UserBusinessVO> listBusiness(@RequestBody UserListQuery query) {
        PageQueryValidate.check(query);
        query.getParams().setUserType(User.USER_TYPE_BUSINESS);
        PageUtil<UserBusinessVO> page = new PageUtil();
        page.setSize(query.getSize());
        page.setCurrent(query.getCurrent());
        page.setParams(query.getParams());
        return userService.listBusiness(page);
    }

    /**
     * 新增业务用户
     *
     * @param query
     */
    @ApiOperation(value = "新增业务用户", notes = "新增业务用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "电话", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "userName", value = "名称", required = true, dataType = "string", paramType = "path")
    })
    @PostMapping("/business")
    public String addBusinessUser(@RequestBody RegisterQuery query) {
        UserValidate.checkAdd(query);
        userService.addBusinessUser(query);

        return "ok";
    }

    /**
     * 修改用户信息
     *
     * @param query
     */
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "电话", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "userName", value = "名称", required = true, dataType = "string", paramType = "path")
    })
    @PutMapping("/business")
    public String updateBusinessUser(@RequestBody RegisterQuery query) {
        userService.updateUser(query);
        return "ok";
    }

    /**
     * 根据角色 获取对应角色下的用户
     *
     * @param roleCode
     * @return
     */
    @ApiOperation(value = "根据角色编码获取对应角色下的用户", notes = "根据角色编码获取对应角色下的用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleCode", value = "角色编码", required = true, dataType = "string", paramType = "path")
    })
    @GetMapping("/getUserListByRoleCode")
    public List<UserExtendVO> getUserListByRoleCode(String roleCode){
        StringValidate.requireNotBlank(roleCode, "roleCode不能为空!");
        return userService.getUserListByRoleCode(roleCode);
    }

    /**
     * 获取运营用户
     *
     * @param id
     */
    @ApiOperation(value = "获取运营用户", notes = "获取运营用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "long", paramType = "path")
    })
    @PostMapping("/getUserBusinessVOById")
    public UserBusinessVO getUserBusinessVOById(Long id) {
        ObjectValidate.requireNotNull(id, "ID不能为空!");
        return userService.getUserBusinessVOById(id);
    }
}
