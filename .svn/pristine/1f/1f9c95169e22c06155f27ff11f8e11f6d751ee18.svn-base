package com.gpdi.hqplus.process.api;

import com.gpdi.hqplus.common.entity.vo.JsonResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lianghb
 * @create: 2019-05-23 10:23
 **/
@Api
@Slf4j
@RestController
@RequestMapping("/v1/api/process")
public class ProcessApi{
    /**
     * hello world
     *
     * @param name
     * @return
     */
    @ApiOperation(value = "hello world api", notes = "根据url的name来获取hello world信息")
    @ApiImplicitParam(name = "name", value = "名称", required = true, dataType = "String", paramType = "path")
    @GetMapping("/{name}")
    public JsonResultVO hello(@PathVariable("name") String name) {
        return JsonResultVO.success("hello! " + name + ",from user api!你好！");
    }
}
