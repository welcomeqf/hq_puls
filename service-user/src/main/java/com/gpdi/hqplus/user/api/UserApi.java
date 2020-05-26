package com.gpdi.hqplus.user.api;

import com.gpdi.hqplus.user.entity.User;
import com.gpdi.hqplus.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * api 接口
 *
 * @author: lianghb
 * @create: 2019-06-18 14:15
 **/
@Api
@RestController
@RequestMapping("/api/user")
public class UserApi {

}
