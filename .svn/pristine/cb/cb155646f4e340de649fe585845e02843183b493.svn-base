package com.gpdi.hqplus.userapply.controller;


import com.gpdi.hqplus.common.validate.ObjectValidate;
import com.gpdi.hqplus.common.validate.StringValidate;
import com.gpdi.hqplus.userapply.entity.UserApplyVO;
import com.gpdi.hqplus.userapply.service.IUserApplyService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author hmx
 * @since 2019-07-02
 */
@RestController
@RequestMapping("/v1/app/resources/userapply")
public class UserApplyController {
	@Autowired
	private IUserApplyService userApplyService;

	/**
	 * @Description: 用户认证接口
	 * @param: userApplyAppVO
	 * @Resul: result
	 */
	@RequestMapping("/apply")
	@ApiOperation(value = "用户认证", notes = "用户认证")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userType", value = "用户类型", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "phone", value = "电话", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "name", value = "姓名", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "email", value = "邮箱", required = false, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "propertyUnit", value = "物业单位", required = false, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "job", value = "职务", required = false, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "fileNames", value = "相关证明文件图片", required = false, dataType = "string", paramType = "path")
	})
	public String userApply(@RequestBody UserApplyVO userVO) {
		StringValidate.requireNotBlank(userVO.getPhone(), "电话不能为空");
		StringValidate.requireNotBlank(userVO.getName(), "姓名不能为空");
		StringValidate.requireNotBlank(userVO.getUserType(), "用户类型不能为空");
		ObjectValidate.requireNotNull(userVO.getFileNames(), "相关证明文件图片不能为空");
		return userApplyService.userApply(userVO);
	}
}

