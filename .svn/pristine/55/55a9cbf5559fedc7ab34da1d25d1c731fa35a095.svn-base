package com.gpdi.hqplus.enterprise.controller.manager;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.CollectionUtil;
import com.gpdi.hqplus.common.validate.ObjectValidate;
import com.gpdi.hqplus.enterprise.entity.Enterprise;
import com.gpdi.hqplus.enterprise.entity.EnterpriseParams;
import com.gpdi.hqplus.enterprise.service.IEnterpriseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业信息表 前端控制器
 * </p>
 *
 * @author wzr
 * @since 2019-07-13
 */
@Api
@Slf4j
@RestController
@RequestMapping("/v1/manager/resources/enterprise")
public class EnterpriseManagerController {

    @Autowired
    IEnterpriseService enterpriseService;

    @ApiOperation("新增企业")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "企业名称", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "industry", value = "企业所属行业", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "managerName", value = "企业法人姓名", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "tel", value = "法人(企业)联系方式", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "personnel", value = "行政人员", required = false, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "personnelTel", value = "行政人员联系方式", required = false, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "floor", value = "楼层", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "unitCode", value = "所属楼栋单元", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "houseKeeper", value = "企业管家", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "description", value = "企业描述", required = false, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "remark", value = "备注", required = false, dataType = "String", paramType = "path")
    })
    @PostMapping("/add")
    public void add(@RequestBody Enterprise enterprise){
        enterpriseService.add(enterprise);
    }

    @ApiOperation("编辑企业")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "企业id", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "name", value = "企业名称", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "industry", value = "企业所属行业", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "managerName", value = "企业法人姓名", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "tel", value = "法人(企业)联系方式", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "personnel", value = "行政人员", required = false, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "personnelTel", value = "行政人员联系方式", required = false, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "floor", value = "楼层", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "unitCode", value = "所属楼栋单元", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "houseKeeper", value = "企业管家", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "description", value = "企业描述", required = false, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "remark", value = "备注", required = false, dataType = "String", paramType = "path")
    })
    @PostMapping("/edit")
    public void edit(@RequestBody Enterprise enterprise){
        enterpriseService.edit(enterprise);
    }


    @ApiImplicitParam(name = "ids", value = "企业id列表", required = true, dataType = "long", paramType = "path")
    @PostMapping("/delete")
    public void delete(@RequestBody EnterpriseParams enterpriseParams){
        if(CollectionUtil.isEmpty(enterpriseParams.getIds())){
            log.error("企业删除失败，参数为空");
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR,"企业删除失败，参数为空");
        }
        enterpriseService.deleteList(enterpriseParams.getIds());
    }

    @ApiOperation("分页展示企业信息。可以填充查询参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "每页显示数", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "current", value = "当前页", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "records",
                    value = "查询条件数组,参数放在第一个JSONObject内,参数列表可参考新增方法，额外包含创建时间区间",
                    required = false,dataType = "json",paramType = "path")
    })
    @PostMapping("/listByPage")
    public Page listByPage(@RequestBody Page page){
        return enterpriseService.listByPage(page);
    }

    @ApiImplicitParam(name = "id", value = "企业id", required = true, dataType = "long", paramType = "path")
    @PostMapping("/detail")
    public Enterprise getDetailById(@RequestBody Enterprise enterprise){
        ObjectValidate.requireNotNull(enterprise.getId(), "企业id不能为空");
        return enterpriseService.getById(enterprise.getId());
    }

}

