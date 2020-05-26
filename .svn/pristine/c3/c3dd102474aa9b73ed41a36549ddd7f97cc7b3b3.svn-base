package com.gpdi.hqplus.parking.controller.app;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.parking.constants.ParkingConstants;
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
 * @Description 停车场月卡app接口
 * @Author wzr
 * @CreateDate 2019-07-02
 * @Time 09:21
 */
@Api(tags = "停车场月卡app接口")
@Slf4j
@RestController
@RequestMapping("/v1/app/resources/parking/parkingCard")
public class ParkingCardAppController {

    @Autowired
    ParkingCardService parkingCardService;

    @ApiOperation(value = "获取当前用户停车场月卡列表")
    @GetMapping("/listByUser")
    public List<ParkingCardVO> listParkingCard(){
        List<ParkingCardVO> parkingCardVOS = parkingCardService.listParkingCardByUser();
        log.warn("listByUser:"+parkingCardVOS.toString());
        return parkingCardVOS;
    }

    @ApiOperation(value = "停车场月卡续费")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "停车场月卡id", required = true,dataType = "long",paramType = "query"),
            @ApiImplicitParam(name = "timePeriod", value = "续费市场，单位为月", dataType = "int", required = true, paramType = "query")
    })
    @PostMapping("/renewal")
    public void renewalParkingCard(@RequestBody ParkingCardVO parkingCardVO){
        Long bookId = parkingCardVO.getId();
        int timePeriod = parkingCardVO.getTimePeriod();
        parkingCardService.renewalParkingCard(bookId,timePeriod);
    }

    @ApiOperation(value = "停车场月卡申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "申请人姓名",required = true,dataType = "String",paramType = "path"),
            @ApiImplicitParam(name = "phone",value = "申请手机号",required = true,dataType = "String",paramType = "path"),
            @ApiImplicitParam(name = "companyId",value = "申请人公司id",required = true,dataType = "Long",paramType = "path"),
            @ApiImplicitParam(name = "carNumber",value = "车牌号",required = true,dataType = "String",paramType = "path"),
    })
    @PostMapping("/book")
    public void bookParkingCard(@RequestBody ParkingCardVO parkingCardVO){
        parkingCardService.bookParkingCard(parkingCardVO);
    }

    @ApiOperation(value = "分页查询停车场月卡记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "每页显示数", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "current", value = "当前页", required = true, dataType = "long", paramType = "path")
    })
    @GetMapping("/listByPage")
    public Page listParkingCardByPage(@RequestBody Page page){
        return parkingCardService.listParkingCardByUserAndPage(page);
    }

    @ApiOperation(value = "取消停车场月卡申请")
    @ApiImplicitParam(name = "id",value = "停车场月卡id",required = true ,dataType = "long",paramType = "path")
    @PostMapping("/cancel")
    public void cancelParkingCardBook(@RequestBody ParkingCardVO parkingCardVO){
        Long id = parkingCardVO.getId();
        if (id!=null){
            //限制用户只能取消申请中的停车场月卡记录
            ParkingCardVO parkingCardById = parkingCardService.getParkingCardById(id);
            String flowStatus = parkingCardById.getFlowStatus();
            if (ParkingConstants.PARKING_CARD_STATUS_AUDITING.equals(flowStatus)) {
                parkingCardService.deleteParkingCardById(id);
            }else {
                log.warn("不能取消非申请中的月卡记录");
                throw new ApplicationException(ErrorCode.SERVICE_ERROR,"只能取消申请中的月卡记录！");
            }
        }
    }
}
