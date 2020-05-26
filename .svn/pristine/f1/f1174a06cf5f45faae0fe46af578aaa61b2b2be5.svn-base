package com.gpdi.hqplus.user.controller.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.common.validate.ObjectValidate;
import com.gpdi.hqplus.common.validate.PageQueryValidate;
import com.gpdi.hqplus.user.entity.query.*;
import com.gpdi.hqplus.user.entity.vo.RoleListVO;
import com.gpdi.hqplus.user.entity.vo.RoleVO;
import com.gpdi.hqplus.user.service.IRoleService;
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
 * @create: 2019-07-01 23:40
 **/
@Api
@Slf4j
@RestController
@RequestMapping("/v1/manager/user/role")
@PreAuthorize("hasRole('admin')")
public class RoleManagerController {
	@Autowired
	private IRoleService roleService;

	/**
	 * 分页获取角色列表
	 *
	 * @param query
	 */
	@ApiOperation(value = "分页获取角色列表", notes = "分页获取角色列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "current", value = "当前页", required = true, dataType = "int", paramType = "path"),
			@ApiImplicitParam(name = "size", value = "每页记录数", required = true, dataType = "int", paramType = "path"),
			@ApiImplicitParam(name = "params.name", value = "角色名称", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "params.code", value = "角色代码", required = true, dataType = "string", paramType = "path")
	})
	@PostMapping("/list")
	public Page<RoleListVO> listRole(@RequestBody RoleListQuery query) {
		PageQueryValidate.check(query);
		if (query.getParams() == null) {
			query.setParams(new RoleListQuery.Params());
		}
		return roleService.listRole(query);
	}

	/**
	 * 新增业务角色
	 *
	 * @param query
	 */
	@ApiOperation(value = "新增业务角色", notes = "新增业务角色")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "phone", value = "电话", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "roleName", value = "名称", required = true, dataType = "string", paramType = "path")
	})
	@PostMapping("/add")
	public String addRole(@RequestBody RoleQuery query) {
		roleService.add(query);
		return "ok";
	}

	/**
	 * 修改角色信息
	 *
	 * @param query
	 */
	@ApiOperation(value = "修改角色信息", notes = "修改角色信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "phone", value = "电话", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "roleName", value = "名称", required = true, dataType = "string", paramType = "path")
	})
	@PostMapping("/editor")
	public String updateRole(@RequestBody RoleQuery query) {
		roleService.update(query);
		return "ok";
	}

	/**
	 * 获取角色详细信息
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "获取角色详细信息", notes = "获取角色详细信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "long", paramType = "path")
	})
	@GetMapping("/detail")
	public RoleVO getDetailById(Long id) {
		ObjectValidate.requireNotNull(id, "角色ID不能为空");
		return roleService.getDetailById(id);
	}


	/**
	 * 批量删除角色信息
	 *
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "获取角色详细信息", notes = "获取角色详细信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ids", value = "角色ID集合", required = true, dataType = "long", paramType = "path")
	})
	@PostMapping("/delete")
	public String deleteByIds(@RequestBody List<Long> ids) {
		ObjectValidate.requireNotNull(ids, "角色ids不能为空");
		return roleService.deleteBatchIds(ids);
	}


	/**
	 * 根据角色类型 获取角色集合
	 * @param userType
	 * @return
	 */
	@ApiOperation(value = "获取角色集合", notes = "获取角色集合")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "roleType", value = "角色类型", required = true, dataType = "long", paramType = "path")
	})
	@GetMapping("/getRoleType")
	public List<RoleVO> getRolesByRoleType(String userType){
		return roleService.getRolesByRoleType(userType);
	}
}
