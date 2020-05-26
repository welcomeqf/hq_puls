package com.gpdi.hqplus.apartment.controller.app;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.apartment.entity.query.RoomPageQuery;
import com.gpdi.hqplus.apartment.entity.query.SeriesPageQuery;
import com.gpdi.hqplus.apartment.entity.vo.RoomListVO;
import com.gpdi.hqplus.apartment.entity.vo.SeriesListVO;
import com.gpdi.hqplus.apartment.service.IApartmentRoomService;
import com.gpdi.hqplus.common.validate.PageQueryValidate;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 公寓房型 前端控制器
 * </p>
 *
 * @author lianghb
 * @since 2019-07-20
 */
@Api(tags = "公寓房型服务")
@Slf4j
@RestController
@RequestMapping("/v1/app/resources/apartment/room")
public class ApartmentRoomController {
    @Autowired
    private IApartmentRoomService roomService;

    /**
     * app page
     *
     * @param query
     */
    @PostMapping("/page")
    public Page<RoomListVO> page(@RequestBody RoomPageQuery query) {
        PageQueryValidate.check(query);
        return roomService.pageRoom(query);
    }
}

