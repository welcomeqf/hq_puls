package com.gpdi.hqplus.apartment.controller.app;


import com.gpdi.hqplus.apartment.entity.ApartmentApply;
import com.gpdi.hqplus.apartment.service.IApartmentApplyService;
import com.gpdi.hqplus.apartment.validate.ApartmentApplyValidate;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 公寓看房预约 前端控制器
 * </p>
 *
 * @author lianghb
 * @since 2019-07-20
 */
@Api(tags = "公寓看房预约服务")
@Slf4j
@RestController
@RequestMapping("/v1/app/resources/apartment/apply")
public class ApartmentApplyController {
    @Autowired
    private IApartmentApplyService applyService;

    @PostMapping("/addApply")
    public void addApply(@RequestBody ApartmentApply apply) {
        ApartmentApplyValidate.checkApply(apply);
        applyService.apply(apply);
    }
}

