package com.gpdi.hqplus.resources.controller.app;

import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.StringUtil;
import com.gpdi.hqplus.resources.entity.vo.EquipmentVO;
import com.gpdi.hqplus.resources.entity.vo.SimpleEquipmentVO;
import com.gpdi.hqplus.resources.service.IPropertyEquipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: lianghb
 * @create: 2019-07-21 11:45
 **/
@Slf4j
@RestController
@RequestMapping("/v1/app/resources/equipment")
public class EquipmentAppController {

    @Autowired
    private IPropertyEquipmentService equipmentService;

    @GetMapping("/list")
    public List<EquipmentVO> list(@RequestParam("type") String type) {
        if (StringUtil.isBlank(type)) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "类型不能为空");
        }
        return equipmentService.listByType(type);
    }
}
