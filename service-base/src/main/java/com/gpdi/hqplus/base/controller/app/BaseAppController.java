package com.gpdi.hqplus.base.controller.app;

import com.gpdi.hqplus.common.manager.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * App端接口，包括ios和安卓
 * 如需要区分ios和安卓，可在uri中继续加以区分
 *
 * @author: lianghb
 * @create: 2019-06-03 14:36
 **/
@Api
@Slf4j
@RestController
@RequestMapping("/v1/app/base")
public class BaseAppController {
    @Autowired
    private RedisService redisService;

    /**
     * hello world
     *
     * @param name
     * @return
     */
    @ApiOperation(value = "hello world", notes = "根据url的name来获取hello world信息")
    @ApiImplicitParam(name = "name", value = "名称", required = true, dataType = "String", paramType = "path")
    @GetMapping
    public String hello(String name) {
        return "hello! " + name + ",from user controller!你好！";
    }

    @GetMapping("/redis")
    public String redis(String key) {
        boolean set = redisService.set("v1", "lianghb");
        log.info("--------->>> set:" + set);

        String v1 = (String) redisService.get("v1");
        log.info("--------->>> get:" + v1);
        return v1;

    }

    @GetMapping("/test")
    public String test(String[] ids) {
        System.out.println(ids);
        return null;

    }
}
