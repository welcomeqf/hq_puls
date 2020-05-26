package com.gpdi.hqplus.apartment.controller.manager;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.apartment.entity.query.RoomPageQuery;
import com.gpdi.hqplus.apartment.entity.query.RoomQuery;
import com.gpdi.hqplus.apartment.entity.vo.RoomManagerListVO;
import com.gpdi.hqplus.apartment.service.IApartmentRoomService;
import com.gpdi.hqplus.apartment.validate.ApartmentRoomValidate;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.CollectionUtil;
import com.gpdi.hqplus.common.validate.PageQueryValidate;
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
 * 公寓房型 前端控制器
 * </p>
 *
 * @author lianghb
 * @since 2019-07-20
 */
@Api(tags = "公寓房型服务")
@Slf4j
@RestController
@RequestMapping("/v1/manager/resources/apartment/room")
@PreAuthorize("hasRole('admin')")
public class ApartmentRoomManagerController {
    @Autowired
    private IApartmentRoomService roomService;

    /**
     * add
     *
     * @param query
     */
    @PostMapping("/addRoom")
    public void addRoom(@RequestBody RoomQuery query) {
        ApartmentRoomValidate.checkAdd(query);
        roomService.addRoom(query);
    }

    /**
     * delete
     *
     * @param query
     */
    @PostMapping("/deleteRoom")
    public void deleteRoom(@RequestBody RoomQuery query) {
        if (CollectionUtil.isEmpty(query.getIds())) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "id不能为空");
        }
        roomService.deleteRoom(query.getIds());
    }

    /**
     * disabled
     *
     * @param query
     */
    @PostMapping("/disabledRoom")
    public void disabledRoom(@RequestBody RoomQuery query) {
        if (CollectionUtil.isEmpty(query.getIds())) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "id不能为空");
        }
        roomService.disabledRoom(query.getIds());
    }

    /**
     * update
     *
     * @param query
     */
    @PostMapping("/updateRoom")
    public void updateRoom(@RequestBody RoomQuery query) {
        ApartmentRoomValidate.checkUpdate(query);
        roomService.updateRoom(query);
    }

    /**
     * app page
     *
     * @param query
     * @return
     */
    @PostMapping("/page")
    public Page<RoomManagerListVO> page(@RequestBody RoomPageQuery query) {
        PageQueryValidate.check(query);
        return roomService.pageManager(query);
    }
}

