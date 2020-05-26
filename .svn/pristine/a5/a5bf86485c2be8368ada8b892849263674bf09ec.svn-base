package com.gpdi.hqplus.article.controller.app;

import com.gpdi.hqplus.article.entity.vo.IntroduceVO;
import com.gpdi.hqplus.article.service.IIntroduceService;
import com.gpdi.hqplus.common.validate.StringValidate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 园区介绍
 *
 * @author liujiahui
 * @since 2019-07-05
 */
@Api
@Slf4j
@RestController
@RequestMapping("/v1/app/resources/article")
public class IntroduceController {

    @Autowired
    private IIntroduceService introduceService;

    /**
     * 查看园区介绍
     *
     *@param  parkCode
     * @author liujiahui
     * @since 2019-07-05
     */
    @ApiOperation(value = "查看园区介绍", notes = "查看园区介绍")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parkCode", value = "园区代号", required = true, dataType = "String", paramType = "path")
    })
    @PostMapping("/introduce")
    public IntroduceVO getIntroduce(@RequestBody String parkCode){

        StringValidate.requireNotBlank(parkCode , "园区代号不能为空");

        return introduceService.getIntroduce(parkCode) ;

    }

}
