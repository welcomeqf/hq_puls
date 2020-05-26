package com.gpdi.hqplus.resources.controller.manager;

import com.gpdi.hqplus.resources.constants.EquipmentCode;
import com.gpdi.hqplus.resources.entity.PropertyEquipment;
import com.gpdi.hqplus.resources.service.IPropertyEquipmentService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description 设备管理controller
 * @Author wzr
 * @CreateDate 2019-07-18
 * @Time 17:40
 */
@Api(tags = "设备管理端接口")
@Slf4j
@RestController
@RequestMapping("/v1/manager/resources/equipment")
public class PropertyEquipmentManagerController {

    @Autowired
    IPropertyEquipmentService equipmentService;

    @PostMapping("/addApartmentEquipment")
    public void addApartmentEquipment(@RequestBody PropertyEquipment equipment){
        equipment.setCode(EquipmentCode.APARTMENT_EQUIP);
        equipmentService.add(equipment);
    }

    @PostMapping("/listApartmentEquip")
    public List<PropertyEquipment> listApartmentEquip(){
        return equipmentService.listByCode(EquipmentCode.APARTMENT_EQUIP);
    }

    @PostMapping("/addRoomEquipment")
    public void addRoomEquipment(@RequestBody PropertyEquipment equipment){
        equipment.setCode(EquipmentCode.ROOM_EQUIP);
        equipmentService.add(equipment);
    }

    @PostMapping("/listRoomEquip")
    public List<PropertyEquipment> listRoomEquip(){
        return equipmentService.listByCode(EquipmentCode.ROOM_EQUIP);
    }
}
