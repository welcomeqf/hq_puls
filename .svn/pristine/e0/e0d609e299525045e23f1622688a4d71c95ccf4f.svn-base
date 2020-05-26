package com.gpdi.hqplus.apartment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.apartment.entity.ApartmentRoom;
import com.gpdi.hqplus.apartment.entity.ApartmentSeries;
import com.gpdi.hqplus.apartment.entity.query.RoomPageQuery;
import com.gpdi.hqplus.apartment.entity.query.RoomQuery;
import com.gpdi.hqplus.apartment.entity.vo.RoomListVO;
import com.gpdi.hqplus.apartment.entity.vo.RoomManagerListVO;
import com.gpdi.hqplus.apartment.entity.vo.SeriesListVO;
import com.gpdi.hqplus.apartment.entity.vo.SeriesManagerListVO;
import com.gpdi.hqplus.apartment.mapper.ApartmentRoomMapper;
import com.gpdi.hqplus.apartment.service.IApartmentRoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.apartment.service.IApartmentSeriesService;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公寓房型 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-20
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ApartmentRoomServiceImpl extends ServiceImpl<ApartmentRoomMapper, ApartmentRoom> implements IApartmentRoomService {
    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private IApartmentSeriesService seriesService;

    @Override
    public void addRoom(RoomQuery query) {
        query.setId(idGenerator.getNumberId());
        generateRoomQuery(query);
        boolean insert = query.insert();
        if (!insert) {
            log.error("insert ApartmentRoom fail");
            throw new ApplicationException(ErrorCode.DATABASE_ERROR, "添加失败");
        }
    }

    @Override
    public void deleteRoom(List<Long> ids) {
        int delete = baseMapper.deleteBatchIds(ids);
        if (delete != ids.size()) {
            log.error("delete ApartmentRoom fail");
            throw new ApplicationException(ErrorCode.DATABASE_ERROR, "删除失败");
        }
    }

    @Override
    public void disabledRoom(List<Long> ids) {
        ApartmentRoom room = new ApartmentRoom();
        room.setStatus(ApartmentRoom.STATUS_DISABLED);

        int update = baseMapper.update(room, new QueryWrapper<ApartmentRoom>()
                .lambda()
                .in(ApartmentRoom::getId, ids)
        );

        if (update != ids.size()) {
            log.error("update ApartmentRoom fail");
            throw new ApplicationException(ErrorCode.DATABASE_ERROR, "更新失败");
        }
    }

    @Override
    public void updateRoom(RoomQuery query) {
        generateRoomQuery(query);
        boolean update = query.updateById();
        if (!update) {
            log.error("update ApartmentRoom fail");
            throw new ApplicationException(ErrorCode.DATABASE_ERROR, "更新失败");
        }
    }

    @Override
    public Page<RoomListVO> pageRoom(RoomPageQuery query) {
        Page<ApartmentRoom> page = PageHelper.newInstance(query, ApartmentRoom.class);

        LambdaQueryWrapper<ApartmentRoom> queryWrapper = new QueryWrapper<ApartmentRoom>()
                .lambda()
                .eq(ApartmentRoom::getStatus, ApartmentSeries.STATUS_NORMAL)
                .orderByDesc(ApartmentRoom::getId);

        if (query.getSeriesId() != null) {
            queryWrapper.eq(ApartmentRoom::getSeriesId, query.getSeriesId());
        }

        baseMapper.selectPage(page, queryWrapper);

        List<RoomListVO> result = CollectionUtil.createArrayList();
        if (CollectionUtil.isNotEmpty(page.getRecords())) {
            for (ApartmentRoom record : page.getRecords()) {
                result.add(room2RoomList(record));
            }
        }

        return BeanPowerHelper.mapPage(page, result);
    }

    @Override
    public Page<RoomManagerListVO> pageManager(RoomPageQuery query) {
        Page<ApartmentRoom> page = PageHelper.newInstance(query, ApartmentRoom.class);

        LambdaQueryWrapper<ApartmentRoom> queryWrapper = new QueryWrapper<ApartmentRoom>()
                .lambda()
                .orderByDesc(ApartmentRoom::getId);

        setParams(queryWrapper, query);

        baseMapper.selectPage(page, queryWrapper);

        List<RoomManagerListVO> result = CollectionUtil.createArrayList();
        if (CollectionUtil.isNotEmpty(page.getRecords())) {
            // 查询系列信息
            List<SeriesListVO> seriesList = seriesService.listAll();
            Map<Long, SeriesListVO> map = CollectionUtil.createHashMap();
            for (SeriesListVO vo : seriesList) {
                map.put(vo.getId(), vo);
            }

            for (ApartmentRoom record : page.getRecords()) {
                result.add(room2RoomManagerListVO(record, map));
            }
        }

        return BeanPowerHelper.mapPage(page, result);
    }

    /**
     * ApartmentRoom 2 RoomListVO
     *
     * @param room
     * @return
     */
    private RoomListVO room2RoomList(ApartmentRoom room) {
        RoomListVO vo = new RoomListVO();
        BeanUtils.copyProperties(room, vo);
        vo.setShowImages(JsonUtil.json2Bean(room.getShowImages(), String[].class));
        vo.setEquipments(JsonUtil.json2Bean(room.getEquipments(), List.class));
        vo.setModelImages(JsonUtil.json2Bean(room.getModelImages(), String[].class));
        return vo;
    }

    /**
     * ApartmentRoom 2 RoomManagerListVO
     *
     * @param room
     * @return
     */
    private RoomManagerListVO room2RoomManagerListVO(ApartmentRoom room, Map<Long, SeriesListVO> map) {
        RoomManagerListVO vo = new RoomManagerListVO();
        BeanUtils.copyProperties(room, vo);

        vo.setEquipments(JsonUtil.json2Bean(room.getEquipments(), List.class));
        vo.setShowImages(JsonUtil.json2Bean(room.getShowImages(), String[].class));
        vo.setModelImages(JsonUtil.json2Bean(room.getModelImages(), String[].class));

        SeriesListVO seriesListVO = map.get(room.getSeriesId());
        if (seriesListVO != null) {
            vo.setSeriesName(seriesListVO.getName());
        }

        return vo;
    }

    /**
     * 初始化
     *
     * @param query
     */
    private void generateRoomQuery(RoomQuery query) {
        query.setEquipments(JsonUtil.bean2JsonString(query.getEquipmentList()));
        query.setShowImages(JsonUtil.bean2JsonString(query.getShowImageList()));
        query.setModelImages(JsonUtil.bean2JsonString(query.getModelImageList()));
    }

    /**
     * 设置查询参数
     *
     * @param queryWrapper
     * @param query
     */
    private void setParams(LambdaQueryWrapper<ApartmentRoom> queryWrapper, RoomPageQuery query) {
        if (query.getSeriesId() != null) {
            queryWrapper.eq(ApartmentRoom::getSeriesId, query.getSeriesId());
        }
        if (query.getMinArea() != null) {
            queryWrapper.ge(ApartmentRoom::getArea, query.getMinArea());
        }
        if (query.getMaxArea() != null) {
            queryWrapper.le(ApartmentRoom::getArea, query.getMaxArea());
        }
        if (StringUtil.isNotBlank(query.getName())) {
            queryWrapper.like(ApartmentRoom::getName, query.getName());
        }
        if (StringUtil.isNotBlank(query.getStatus())) {
            queryWrapper.eq(ApartmentRoom::getStatus, query.getStatus());
        }
        if (StringUtil.isNotBlank(query.getRentType())) {
            queryWrapper.like(ApartmentRoom::getRentType, query.getRentType());
        }
        if (StringUtil.isNotBlank(query.getToward())) {
            queryWrapper.eq(ApartmentRoom::getToward, query.getToward());
        }

    }
}
