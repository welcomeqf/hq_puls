package com.gpdi.hqplus.enterprise.controller.app;

import com.gpdi.hqplus.enterprise.entity.Enterprise;
import com.gpdi.hqplus.enterprise.entity.EnterpriseQuery;
import com.gpdi.hqplus.enterprise.service.IEnterpriseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description 企业app端业务
 * @Author wzr
 * @CreateDate 2019-07-15
 * @Time 11:13
 */
@Api(value = "企业app端业务")
@Slf4j
@RestController
@RequestMapping("/v1/app/resources/enterprise")
public class EnterpriseAppController {

    @Autowired
    IEnterpriseService enterpriseService;

    @ApiOperation(value = "获取所有企业信息")
    @GetMapping("/listAll")
    public List<Enterprise> listAll(){
        return enterpriseService.listAll();
    }

    @RequestMapping("/exportExcel")
    public void exportExcel(@RequestBody EnterpriseQuery enterpriseQuery){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpServletResponse response = requestAttributes.getResponse();

        enterpriseService.exportExcel(response,enterpriseQuery);
    }
}
