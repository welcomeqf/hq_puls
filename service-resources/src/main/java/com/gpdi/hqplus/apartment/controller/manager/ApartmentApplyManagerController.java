package com.gpdi.hqplus.apartment.controller.manager;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.apartment.entity.ApartmentApply;
import com.gpdi.hqplus.apartment.entity.query.ApplyPageQuery;
import com.gpdi.hqplus.apartment.entity.query.ApplyQuery;
import com.gpdi.hqplus.apartment.entity.vo.ApplyManagerListVO;
import com.gpdi.hqplus.apartment.service.IApartmentApplyService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/v1/manager/resources/apartment/apply")
@PreAuthorize("hasRole('admin')")
public class ApartmentApplyManagerController {
    @Autowired
    private IApartmentApplyService applyService;

    @PostMapping("/page")
    public Page<ApplyManagerListVO> page(@RequestBody ApplyPageQuery query) {
        return applyService.pageManager(query);
    }

    @PostMapping("/setPrincipal")
    public void setPrincipal(@RequestBody ApplyQuery query) {
        applyService.setPrincipal(query);
    }
}

