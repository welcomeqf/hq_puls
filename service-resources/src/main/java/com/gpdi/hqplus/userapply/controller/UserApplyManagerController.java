package com.gpdi.hqplus.userapply.controller;


import com.gpdi.hqplus.common.util.PageUtil;
import com.gpdi.hqplus.common.validate.ObjectValidate;
import com.gpdi.hqplus.userapply.entity.UserApply;
import com.gpdi.hqplus.userapply.entity.UserApplyQuery;
import com.gpdi.hqplus.userapply.entity.UserApplyVO;
import com.gpdi.hqplus.userapply.service.IUserApplyService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户表 管理端控制器
 * </p>
 *
 * @author hmx
 * @since 2019-07-02
 */
@RestController
@RequestMapping("/v1/web/resources/userapply")
public class UserApplyManagerController {
	@Autowired
	private IUserApplyService userApplyService;

	/**
	 * @Description: 获取用户认证列表
	 * @param: userApplyAppVO
	 * @Resul: result
	 */
	@PostMapping("/list")
	@ApiOperation(value = "获取用户认证列表", notes = "获取用户认证列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "size", value = "每页显示数", required = true, dataType = "long", paramType = "path"),
			@ApiImplicitParam(name = "current", value = "当前页", required = true, dataType = "long", paramType = "path"),
			@ApiImplicitParam(name = "params.userType", value = "用户类型", required = false, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "params.phone", value = "电话", required = false, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "params.name", value = "姓名", required = false, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "params.email", value = "邮箱", required = false, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "params.propertyUnit", value = "物业单位", required = false, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "params.job", value = "职务", required = false, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "params.status", value = "状态", required = false, dataType = "string", paramType = "path")
	})

	public PageUtil<UserApplyVO> userApply(@RequestBody UserApplyQuery query) {
		ObjectValidate.requireNotNull(query.getSize(), "每页显示数不能为空");
		ObjectValidate.requireNotNull(query.getCurrent(), "当前页不能为空");
		PageUtil<UserApplyVO> page = new PageUtil();
		page.setSize(query.getSize());
		page.setCurrent(query.getCurrent());
		page.setParams(query.getParams());
		return userApplyService.getUserApplyList(page);
	}

	/**
	 * 用户认证审核
	 * 管理端审核，根据id修改 UserApply状态， 审核通过:由 apply 状态变为 normal状态
	 * @param ids
	 * @return
	 */
	@PostMapping("/audit")
	@ApiOperation(value = "用户认证审核", notes = "用户认证审核")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ids", value = "申请ID", required = true, dataType = "long", paramType = "path")
	})
	public String auditApply(@RequestBody List<Long> ids){
		ObjectValidate.requireNotNull(ids, "ids不能为空");
		return userApplyService.auditApply(ids);
	}

	/**
	 * 用户认证审核
	 * 获取用户认证详情
	 * @param id
	 * @return
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "用户认证审核", notes = "用户认证审核")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "申请ID", required = true, dataType = "long", paramType = "path")
	})
	public UserApplyVO getUserApplyDetailById(Long id){
		ObjectValidate.requireNotNull(id, "id不能为空");
		return userApplyService.getUserApplyDetailById(id);
	}

	/**
	 * 用户认证审核
	 * 管理端审核，根据id修改 UserApply状态， 审核通过:由 apply 状态变为 normal状态
	 * @param userApplyVO
	 * @return
	 */
	@PostMapping("/check")
	@ApiOperation(value = "用户认证审核", notes = "用户认证审核")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "申请ID", required = true, dataType = "long", paramType = "path"),
			@ApiImplicitParam(name = "status", value = "状态", required = true, dataType = "string", paramType = "path")
	})
	public String checkApply(@RequestBody UserApplyVO userApplyVO){
		ObjectValidate.requireNotNull(userApplyVO.getId(), "id不能为空");
		ObjectValidate.requireNotNull(userApplyVO.getStatus(), "状态不能为空");
		return userApplyService.checkApply(userApplyVO);
	}
}

