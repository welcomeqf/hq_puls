package com.gpdi.hqplus.meeting.controller.web;

/**
 * @author: lianghb
 * @create: 2019-07-15 18:01
 **/

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.validate.PageQueryValidate;
import com.gpdi.hqplus.meeting.entity.query.MeetingAddQuery;
import com.gpdi.hqplus.meeting.entity.query.MeetingPageQuery;
import com.gpdi.hqplus.meeting.entity.query.MeetingQuery;
import com.gpdi.hqplus.meeting.entity.vo.MeetingOrderListVO;
import com.gpdi.hqplus.meeting.entity.vo.MeetingProductVO;
import com.gpdi.hqplus.meeting.entity.vo.MeetingRoomListVO;
import com.gpdi.hqplus.meeting.service.IMeetingOrderService;
import com.gpdi.hqplus.meeting.service.IMeetingProductService;
import com.gpdi.hqplus.meeting.service.IMeetingService;
import com.gpdi.hqplus.meeting.validate.MeetingValidate;
import com.gpdi.hqplus.order.entity.query.OrderListQuery;
import com.gpdi.hqplus.resources.entity.query.ProductListQuery;
import com.gpdi.hqplus.resources.entity.query.PropertyQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author: lianghb
 * @create: 2019-07-13 10:28
 **/
@Api
@Slf4j
@RestController
@RequestMapping("/v1/manager/resources/meeting")
public class MeetingManagerController {
    @Autowired
    private IMeetingService meetingService;
    @Autowired
    private IMeetingProductService productService;
    @Autowired
    @Qualifier("meetingOrderServiceImpl")
    private IMeetingOrderService orderService;

    /**
     * <p>
     * 添加会议室资源
     * </p>
     *
     * @return
     */
    @ApiOperation(value = "添加会议室资源", notes = "添加会议室资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称，标题", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "context", value = "描述，简介", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "address", value = "地址", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "area", value = "面积", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "floor", value = "楼层", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "coverFile", value = "封面图片", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "fileNames", value = "图片", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "equipmentList", value = "设施", required = true, dataType = "string", paramType = "path")
    })
    @PostMapping("/addRoomProduct")
    public void add(@RequestBody MeetingAddQuery query) {
        MeetingValidate.checkAdd(query);
        meetingService.addNewMeetingRoomProduct(query);
    }

    /**
     * <p>
     * 删除会议室资源
     * </p>
     *
     * @return
     */
    @ApiOperation(value = "删除会议室资源", notes = "删除会议室资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "string", paramType = "path"),
    })
    @PostMapping("/delete")
    public void delete(@RequestBody MeetingQuery query) {
        Long[] ids = query.getIds();
        if (ids == null || ids.length == 0) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "id 不能为空");
        }
        for (Long id : ids) {
            meetingService.deleteMeetingRoomProduct(id);
        }
    }

    /**
     * <p>
     * 修改会议室资源
     * </p>
     *
     * @return
     */
    @ApiOperation(value = "修改会议室资源", notes = "修改会议室资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称，标题", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "context", value = "描述，简介", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "address", value = "地址", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "area", value = "面积", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "floor", value = "楼层", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "coverFile", value = "封面图片", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "fileNames", value = "图片", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "equipmentList", value = "设施", required = true, dataType = "string", paramType = "path")
    })
    @PostMapping("/update")
    public void update(@RequestBody MeetingAddQuery query) {
        MeetingValidate.checkUpdate(query);
        productService.updateMeetingRoomProduct(query);
    }

    /**
     * <p>
     * 更新状态
     * </p>
     *
     * @return
     */
    @ApiOperation(value = "更新状态", notes = "更新状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "status", value = "状态", required = true, dataType = "string", paramType = "path")
    })
    @PostMapping("/updateStatus")
    public void updateStatus(@RequestBody MeetingAddQuery query) {
        productService.updateStatus(query);
    }

    /**
     * <p>
     * 获取会议室租赁列表
     * </p>
     *
     * @return
     */
    @ApiOperation(value = "获取会议室租赁列表", notes = "获取会议室租赁列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "params.address", value = "地址", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "params.minSize", value = "最小容纳人数", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "params.maxSize", value = "最大容纳人数", required = true, dataType = "string", paramType = "path")
    })
    @PostMapping("/pageProduct")
    public Page<MeetingProductVO> pageProduct(@RequestBody ProductListQuery query) {
        PageQueryValidate.check(query);
        return productService.pageProduct(query);
    }

    /**
     * <p>
     * 获取会议室订单列表
     * </p>
     *
     * @return
     */
    @ApiOperation(value = "获取会议室订单列表", notes = "获取会议室订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "name", value = "地址", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "userName", value = "用户名称", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "status", value = "状态", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "userConnect", value = "联系方式", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "minPrice", value = "最小价格", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "maxPrice", value = "最大价格", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "startCreateTime", value = "订单开始时间", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "endCreateTime", value = "订单结束时间", required = true, dataType = "string", paramType = "path")
    })
    @PostMapping("/pageOrder")
    public Page<MeetingOrderListVO> pageOrder(@RequestBody OrderListQuery query) {
        PageQueryValidate.check(query);
        return orderService.pageOrder(query);
    }
}
