package com.gpdi.hqplus.meeting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.common.constants.BusinessCode;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.*;
import com.gpdi.hqplus.meeting.constants.MeetingRoomConstants;
import com.gpdi.hqplus.meeting.entity.query.MeetingAddQuery;
import com.gpdi.hqplus.meeting.entity.query.MeetingPageQuery;
import com.gpdi.hqplus.meeting.entity.query.MeetingProductQuery;
import com.gpdi.hqplus.meeting.entity.query.MeetingRoomQuery;
import com.gpdi.hqplus.meeting.entity.vo.MeetingRoomListVO;
import com.gpdi.hqplus.meeting.entity.vo.TimeBitVO;
import com.gpdi.hqplus.meeting.entity.vo.TimeResourceVO;
import com.gpdi.hqplus.meeting.service.IMeetingProductService;
import com.gpdi.hqplus.meeting.service.IMeetingService;
import com.gpdi.hqplus.order.service.IProductOrderService;
import com.gpdi.hqplus.property.entity.vo.PropertyMaintainListVO;
import com.gpdi.hqplus.resources.entity.ProductResource;
import com.gpdi.hqplus.resources.entity.PropertyMaintenanceApply;
import com.gpdi.hqplus.resources.entity.PropertyResource;
import com.gpdi.hqplus.resources.entity.query.PropertyQuery;
import com.gpdi.hqplus.resources.entity.query.TimeResourceQuery;
import com.gpdi.hqplus.resources.service.ITimeResourceRelService;
import com.gpdi.hqplus.resources.service.impl.PropertyResourceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: lianghb
 * @create: 2019-07-12 21:39
 **/
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class MeetingServiceImpl extends PropertyResourceServiceImpl implements IMeetingService {

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private IMeetingProductService productService;
    @Autowired
    private ITimeResourceRelService timeResourceRelService;
    @Autowired
    private IMeetingProductService meetingProductService;

    @Override
    public Page<MeetingRoomListVO> pageMeetingRoomVO(MeetingRoomQuery query) {
        Page<PropertyResource> page = new Page<>();
        page.setCurrent(query.getCurrent());
        page.setSize(query.getSize());

        LambdaQueryWrapper<PropertyResource> queryWrapper = new QueryWrapper<PropertyResource>()
                .lambda()
                .eq(PropertyResource::getBusinessCode, BusinessCode.PROPERTY_MEETING_ROOM)
                .orderByDesc(PropertyResource::getId);

        // 高级条件查询
        if (StringUtil.isNotBlank(query.getName())) {
            queryWrapper.like(PropertyResource::getName, query.getName());
        }
        if (StringUtil.isNotBlank(query.getStatus())) {
            queryWrapper.eq(PropertyResource::getStatus, query.getStatus());
        }
        if (query.getMinArea() != null) {
            queryWrapper.ge(PropertyResource::getArea, query.getMinArea());
        }
        if (query.getMaxArea() != null) {
            queryWrapper.le(PropertyResource::getArea, query.getMaxArea());
        }


        baseMapper.selectPage(page, queryWrapper);

        List<PropertyResource> records = page.getRecords();
        List<MeetingRoomListVO> result = CollectionUtil.createArrayList(records.size());
        for (PropertyResource record : records) {
            result.add(propertyResource2MeetingRoomListVO(record));
        }

        return BeanPowerHelper.mapPage(page, result);
    }

    @Override
    public void addNewMeetingProperty(PropertyQuery query) {
        generatePropertyQuery(query);
        add(query);
    }

    @Override
    public Map<Long, PropertyResource> listByIds(Set<Long> idSet) {
        List<PropertyResource> list = baseMapper.selectList(new QueryWrapper<PropertyResource>()
                .lambda()
                .eq(PropertyResource::getStatus, ProductResource.STATUS_NORMAL)
                .in(PropertyResource::getId, idSet)
        );
        Map<Long, PropertyResource> map = CollectionUtil.createHashMap();

        if (CollectionUtil.isNotEmpty(list)) {
            for (PropertyResource propertyResource : list) {
                map.put(propertyResource.getId(), propertyResource);
            }
        }

        return map;
    }

    @Override
    public PropertyResource getById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * propertyResource 2 MeetingRoomListVO
     *
     * @param resource
     * @return
     */
    private MeetingRoomListVO propertyResource2MeetingRoomListVO(PropertyResource resource) {
        MeetingRoomListVO vo = new MeetingRoomListVO();
        BeanUtils.copyProperties(resource, vo);
        vo.setEquipment(JsonUtil.json2Bean(resource.getEquipment(), List.class));
        return vo;
    }

    @Override
    public void addNewMeetingRoomProduct(MeetingAddQuery query) {
        PropertyQuery propertyQuery = addQuery2PropertyQuery(query);
        addNewMeetingProperty(propertyQuery);
        query.setId(propertyQuery.getId());
        productService.addNewProduct(addQuery2ProductQuery(query));
    }


    @Override
    public void deleteMeetingRoomProduct(Long id) {
        ProductResource productResource = meetingProductService.getById(id);
        if (productResource == null) {
            throw new ApplicationException(ErrorCode.RESOURCES_NOT_FIND, "查询会议室信息失败");
        }
        PropertyResource resource = baseMapper.selectById(productResource.getResourceId());
        if (resource == null) {
            throw new ApplicationException(ErrorCode.RESOURCES_NOT_FIND, "查询会议室信息失败");
        }


        boolean delete = resource.deleteById() && productResource.deleteById();
        if (!delete) {
            log.error("delete MeetingRoomProduct fail");
            throw new ApplicationException(ErrorCode.DATABASE_ERROR, "删除失败");
        }
    }

    @Override
    public void updateMeetingRoomProduct(MeetingAddQuery query) {
        PropertyResource resource = baseMapper.selectById(query.getMeetingResourceId());
        if (resource == null) {
            throw new ApplicationException(ErrorCode.RESOURCES_NOT_FIND, "查询会议室信息失败");
        }

        PropertyQuery propertyQuery = addQuery2PropertyQuery(query);
        generatePropertyQuery(propertyQuery);

        propertyQuery.setId(query.getMeetingResourceId());
        propertyQuery.setCreateTime(resource.getCreateTime());

        boolean update = propertyQuery.updateById();
        if (!update) {
            log.error("update MeetingRoom fail");
            throw new ApplicationException(ErrorCode.DATABASE_ERROR, "更新失败");
        }
    }

    @Override
    public Page<MeetingRoomListVO> listMeetingRoomListVO(MeetingPageQuery query) {
        Page<PropertyResource> page = new Page<>();
        page.setCurrent(query.getCurrent());
        page.setSize(query.getSize());

        LambdaQueryWrapper<PropertyResource> queryWrapper = new QueryWrapper<PropertyResource>()
                .lambda()
                .eq(PropertyResource::getBusinessCode, BusinessCode.PROPERTY_MEETING_ROOM)
                .orderByDesc(PropertyResource::getId);

        // 高级条件查询
        if (StringUtil.isNotBlank(query.getName())) {
            queryWrapper.like(PropertyResource::getName, query.getName());
        }
        if (StringUtil.isNotBlank(query.getStatus())) {
            queryWrapper.eq(PropertyResource::getStatus, query.getStatus());
        }
        if (query.getMinSize() != null) {
            queryWrapper.ge(PropertyResource::getSize, query.getMinSize());
        }
        if (query.getMaxSize() != null) {
            queryWrapper.le(PropertyResource::getSize, query.getMaxSize());
        }


        baseMapper.selectPage(page, queryWrapper);

        List<PropertyResource> records = page.getRecords();
        List<MeetingRoomListVO> result = CollectionUtil.createArrayList(records.size());
        for (PropertyResource record : records) {
            result.add(propertyResource2MeetingRoomListVO(record));
        }

        return BeanPowerHelper.mapPage(page, result);
    }

    @Override
    public List<TimeBitVO> getTimeBit(TimeResourceQuery query) {
        ProductResource productResource = meetingProductService.getById(query.getId());
        if (productResource == null) {
            throw new ApplicationException(ErrorCode.RESOURCES_NOT_FIND, "查询会议室信息失败");
        }
        PropertyResource resource = baseMapper.selectById(productResource.getResourceId());
        if (resource == null) {
            throw new ApplicationException(ErrorCode.RESOURCES_NOT_FIND, "查询会议室信息失败");
        }

        List<TimeBitVO> result = CollectionUtil.createArrayList();

        Integer[] timeBit = timeResourceRelService.getByResIdDate(query.getId(), query.getDate());
        Map<String, String> map = JsonUtil.json2Map(resource.getExtendVal());
        String startTimeStr = map.get(MeetingRoomConstants.MEETING_ROOM_EXTEND_SERVICE_START_TIME);
        String endTimeStr = map.get(MeetingRoomConstants.MEETING_ROOM_EXTEND_SERVICE_END_TIME);

        int startTime = StringUtil.isNotBlank(startTimeStr) ? Integer.parseInt(startTimeStr) : 0;
        int endTime = StringUtil.isNotBlank(endTimeStr) ? Integer.parseInt(endTimeStr) : 24;

        int step = 2;
        for (int i = startTime * step; i < endTime * step; i += step) {
            String time = String.format("%d:00-%d:00", i / 2, i / 2 + 1);

            TimeBitVO vo = new TimeBitVO();
            vo.setId(i);
            vo.setTime(time);
            vo.setSelected(false);
            vo.setDisabled(timeBit[i] != 0);

            result.add(vo);

        }

        return result;
    }

    @Override
    public TimeResourceVO getTimeResource(TimeResourceQuery query) {
        ProductResource productResource = meetingProductService.getById(query.getId());
        if (productResource == null) {
            throw new ApplicationException(ErrorCode.RESOURCES_NOT_FIND, "查询会议室信息失败");
        }
        PropertyResource resource = baseMapper.selectById(productResource.getResourceId());
        if (resource == null) {
            throw new ApplicationException(ErrorCode.RESOURCES_NOT_FIND, "查询会议室信息失败");
        }

        return resource2TimeResource(resource, timeResourceRelService.getByResIdDate(query.getId(), query.getDate()));
    }

    @Override
    public TimeResourceVO resource2TimeResource(PropertyResource resource, Integer[] timeBit) {
        Map<String, String> map = JsonUtil.json2Map(resource.getExtendVal());
        String startTimeStr = map.get(MeetingRoomConstants.MEETING_ROOM_EXTEND_SERVICE_START_TIME);
        String endTimeStr = map.get(MeetingRoomConstants.MEETING_ROOM_EXTEND_SERVICE_END_TIME);

        int startTime = StringUtil.isNotBlank(startTimeStr) ? Integer.parseInt(startTimeStr) : 0;
        int endTime = StringUtil.isNotBlank(endTimeStr) ? Integer.parseInt(endTimeStr) : 24;

        int[] roomState = new int[endTime - startTime];
        int roomStateIndex = 0;

        int step = 2;
        for (int i = startTime * step; i < endTime * step; i += step) {
            roomState[roomStateIndex++] = timeBit[i];
        }

        TimeResourceVO vo = new TimeResourceVO();
        vo.setRoomState(roomState);
        vo.setStartTime(startTime);

        return vo;
    }

    /**
     * MeetingAddQuery 2 PropertyQuery
     *
     * @param query
     * @return
     */
    private PropertyQuery addQuery2PropertyQuery(MeetingAddQuery query) {
        PropertyQuery propertyQuery = new PropertyQuery();
        BeanUtils.copyProperties(query, propertyQuery);
        return propertyQuery;
    }

    /**
     * MeetingAddQuery 2 MeetingProductQuery
     *
     * @param query
     * @return
     */
    private MeetingProductQuery addQuery2ProductQuery(MeetingAddQuery query) {
        MeetingProductQuery productQuery = new MeetingProductQuery();
        BeanUtils.copyProperties(query, productQuery);
        productQuery.setResourceId(query.getId());

        return productQuery;
    }

    /**
     * 初始化PropertyQuery
     *
     * @param query
     */
    private void generatePropertyQuery(PropertyQuery query) {
        query.setBusinessCode(BusinessCode.PROPERTY_MEETING_ROOM);
        query.setEquipment(JsonUtil.bean2JsonString(query.getEquipmentList()));

        Map<String, String> map = CollectionUtil.createHashMap();
        map.put(MeetingRoomConstants.MEETING_ROOM_EXTEND_COVER_IMG, query.getCoverFile());
        map.put(MeetingRoomConstants.MEETING_ROOM_EXTEND_OTHER_IMG, JsonUtil.bean2JsonString(query.getOtherImg()));
        map.put(MeetingRoomConstants.MEETING_ROOM_EXTEND_SERVICE_START_TIME, query.getServiceStartTime().toString());
        map.put(MeetingRoomConstants.MEETING_ROOM_EXTEND_SERVICE_END_TIME, query.getServiceEndTime().toString());


        query.setExtendVal(JsonUtil.bean2JsonString(map));
    }
}
