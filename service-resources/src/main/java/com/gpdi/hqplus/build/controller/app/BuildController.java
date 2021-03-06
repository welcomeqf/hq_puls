package com.gpdi.hqplus.build.controller.app;

import com.gpdi.hqplus.build.entity.query.BuildQuery;
import com.gpdi.hqplus.build.entity.query.BuildSubQuery;
import com.gpdi.hqplus.build.service.IBuildService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物业楼栋控制器
 *
 * @Author qf
 * @Date 2019/7/18
 * @Version 1.0
 */
@Api
@Slf4j
@RestController
@RequestMapping("/v1/app/resources/build")
public class BuildController {

    @Autowired
    private IBuildService buildService;

    /**
     * 查询楼栋,楼层,房间号
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询楼栋,楼层,房间号", notes = "查询楼栋,楼层,房间号")
    @ApiImplicitParam(name = "id", value = "id", required = false, dataType = "string", paramType = "path")
    @GetMapping("/query/{id}")
    public List<BuildQuery> queryBuildById(@PathVariable("id") Long id) {

        List<BuildQuery> builds = buildService.queryBuild(id);
        return builds;
    }


    @PostMapping("/listSub")
    public List<BuildQuery> listSubById(@RequestBody BuildSubQuery query) {
        if (query.getId() == null) {
            query.setId(0L);
        }
        return buildService.listSubById(query);
    }
}
