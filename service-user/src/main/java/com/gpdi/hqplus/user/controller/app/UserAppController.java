package com.gpdi.hqplus.user.controller.app;

import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.entity.vo.JsonResultVO;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.user.entity.query.LoginQuery;
import com.gpdi.hqplus.user.entity.query.RegisterQuery;
import com.gpdi.hqplus.user.entity.query.UserExtendQuery;
import com.gpdi.hqplus.user.entity.query.UserQuery;
import com.gpdi.hqplus.user.entity.vo.LoginInfoVO;
import com.gpdi.hqplus.common.validate.StringValidate;
import com.gpdi.hqplus.user.entity.vo.RegisterInfoVO;
import com.gpdi.hqplus.user.entity.vo.UserExtendVO;
import com.gpdi.hqplus.user.service.IUserExtendService;
import com.gpdi.hqplus.user.service.IUserService;
import com.gpdi.hqplus.user.validate.UserValidate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author lianghb
 * @since 2019-06-20
 */
@Api
@RestController
@RequestMapping("/v1/app/user")
public class UserAppController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IUserExtendService userExtendService;



    /**
     * <p>
     * 用户表 注册
     * </p>
     */
    @ApiOperation(value = "注册", notes = "注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "电话", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "userName", value = "名称", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "smsCode", value = "手机验证码", required = true, dataType = "string", paramType = "path")
    })
    @PostMapping("/register")
    public String register(@RequestBody RegisterQuery query) {
        UserValidate.checkRegister(query);
        userService.register(query);

        return "ok";
    }

    /**
     * 个性化功能定制
     *
     * @param query
     * @author liujiahui
     * @since 2019-07-02
     */
    @ApiOperation(value = "个性化功能定制", notes = "个性化功能定制")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "功能模块代号", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "sort", value = "排序", required = true, dataType = "string", paramType = "path")
    })
    @PostMapping("/selfMenu")
    public String selfMenu(@RequestBody UserExtendQuery query){

        if (query.getPersonalFunction()==null){

            throw new ApplicationException(ErrorCode.SERVICE_ERROR,"未找到对应参数 personalFunction ");

        }

        userExtendService.selfMenu(query);

        return "ok";

    }

    /**
     * 首页功能浏览
     *
     * @author liujiahui
     * @since 2019-07-02
     */
    @ApiOperation(value = "首页功能浏览", notes = "首页功能浏览")
    @GetMapping("/getMenu")
    public String getMenu(){

        return userExtendService.getMenu();
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @ApiOperation(value = "查询用户信息",notes = "查询用户信息")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "string", paramType = "path")
    @GetMapping("/getUserById/{id}")
    public JsonResultVO<UserQuery> getUserById(@PathVariable("id") Long id){
        UserQuery result = userService.queryUserById(id);
        if (result == null) {
            throw new ApplicationException(ErrorCode.SERVICE_ERROR,"根据id查询用户信息错误");
        }
        return JsonResultVO.success(result);
    }

}

