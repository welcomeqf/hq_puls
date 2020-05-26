package com.gpdi.hqplus.parking.controller.manager;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.parking.entity.vo.ParkingCardVO;
import com.gpdi.hqplus.parking.service.ParkingCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description 停车场月卡web端接口
 * @Author wzr
 * @CreateDate 2019-07-03
 * @Time 10:59
 */
@Api(tags = "停车场月卡web接口")
@Slf4j
@RestController
@RequestMapping("/v1/manager/resources/parking/parkingCard")
public class ParkingCardManagerController {

    @Autowired
    ParkingCardService parkingCardService;

    @ApiOperation(value = "获取所有停车场月卡列表")
    @GetMapping("/listAll")
    public List<ParkingCardVO> listParkingCard(){
        return parkingCardService.listParkingCardByUser();
    }

    @ApiOperation(value = "分页查询停车场月卡记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "每页显示数", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "current", value = "当前页", required = true, dataType = "long", paramType = "path")
    })
    @GetMapping("/listByPage")
    public Page listParkingCardByPage(@RequestBody Page page){
        return parkingCardService.listParkingCardByPage(page);
    }

    @ApiOperation(value = "审核停车场月卡通过")
    @ApiImplicitParam(name = "id",value = "停车场月卡id",required = true ,dataType = "long",paramType = "path")
    @PostMapping("/pass")
    public void passParkingCard(@RequestBody ParkingCardVO parkingCardVO){
        Long id = parkingCardVO.getId();
        if (id!=null){
            parkingCardService.passParkingCard(id);
        }
    }

    @ApiOperation(value = "审核停车场月卡不通过")
    @ApiImplicitParam(name = "id",value = "停车场月卡id",required = true ,dataType = "long",paramType = "path")
    @PostMapping("/reject")
    public void rejectParkingCard(@RequestBody ParkingCardVO parkingCardVO){
        Long id = parkingCardVO.getId();
        if (id!=null){
            parkingCardService.rejectParkingCard(id);
        }
    }

    @ApiOperation(value = "删除月卡记录")
    @ApiImplicitParam(name = "id",value = "停车场月卡id",required = true ,dataType = "long",paramType = "path")
    @PostMapping("/delete")
    public void deleteParkingCard(@RequestBody ParkingCardVO parkingCardVO){
        Long id = parkingCardVO.getId();
        if (id!=null){
            parkingCardService.deleteParkingCardById(id);
        }
    }
}
