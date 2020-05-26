package com.gpdi.hqplus.apartment.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.apartment.entity.ApartmentRoom;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gpdi.hqplus.apartment.entity.query.RoomPageQuery;
import com.gpdi.hqplus.apartment.entity.query.RoomQuery;
import com.gpdi.hqplus.apartment.entity.vo.RoomListVO;
import com.gpdi.hqplus.apartment.entity.vo.RoomManagerListVO;

import java.util.List;

/**
 * <p>
 * 公寓房型 服务类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-20
 */
public interface IApartmentRoomService extends IService<ApartmentRoom> {
    /**
     * add
     *
     * @param query
     */
    void addRoom(RoomQuery query);

    /**
     * delete
     *
     * @param ids
     */
    void deleteRoom(List<Long> ids);

    /**
     * 批量禁用
     *
     * @param id
     */
    void disabledRoom(List<Long> id);

    /**
     * update
     *
     * @param query
     */
    void updateRoom(RoomQuery query);

    /**
     * page room
     *
     * @param query
     * @return
     */
    Page<RoomListVO> pageRoom(RoomPageQuery query);

    /**
     * 管理端分页
     *
     * @param query
     * @return
     */
    Page<RoomManagerListVO> pageManager(RoomPageQuery query);
}
