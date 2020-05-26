package com.gpdi.hqplus.authentication.controller.app;

import com.gpdi.hqplus.authentication.entity.query.LoginQuery;
import com.gpdi.hqplus.authentication.entity.vo.LoginInfoVO;
import com.gpdi.hqplus.authentication.service.IUserService;
import com.gpdi.hqplus.common.validate.StringValidate;
import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: lianghb
 * @create: 2019-07-01 09:49
 **/
@Api
@Slf4j
@RestController
@RequestMapping("/v1/app/authentication")
public class AuthAppController {
    @Autowired
    private IUserService userService;

    /**
     * 登录
     *
     * @param query
     * @return
     */
    @ApiOperation(value = "登录", notes = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "电话", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "code", value = "验证码", required = false, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "deviceId", value = "设备 id", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "deviceToken", value = "应用 id", required = true, dataType = "String", paramType = "path")
    })
    @PostMapping("/login")
    public LoginInfoVO login(@RequestBody LoginQuery query) {
        StringValidate.requireNotBlank(query.getPhone(), "手机号不能为空");
        StringValidate.requireNotBlank(query.getPassword(), "密码不能为空");
        StringValidate.requireNotBlank(query.getDeviceId(), "设备 id不能为空");
        StringValidate.requireNotBlank(query.getDeviceToken(), "应用 id不能为空");

        return userService.login(query);
    }

    /**
     * <p>
     * 退出
     * </p>
     */
    @ApiOperation(value = "退出", notes = "退出")
    @PostMapping("/logout")
    public String logout() {
        userService.logout();
        return "ok";
    }
}
