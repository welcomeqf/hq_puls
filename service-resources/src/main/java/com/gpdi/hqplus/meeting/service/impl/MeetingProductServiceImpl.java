package com.gpdi.hqplus.meeting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.common.constants.BusinessCode;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.manager.RedisService;
import com.gpdi.hqplus.common.util.*;
import com.gpdi.hqplus.meeting.constants.MeetingRoomConstants;
import com.gpdi.hqplus.meeting.entity.query.MeetingAddQuery;
import com.gpdi.hqplus.meeting.entity.query.MeetingBookQuery;
import com.gpdi.hqplus.meeting.entity.query.MeetingProductQuery;
import com.gpdi.hqplus.meeting.entity.vo.MeetingExtendProductVO;
import com.gpdi.hqplus.meeting.entity.vo.MeetingProductVO;
import com.gpdi.hqplus.meeting.entity.vo.TimeResourceVO;
import com.gpdi.hqplus.meeting.service.IMeetingProductService;
import com.gpdi.hqplus.meeting.service.IMeetingService;
import com.gpdi.hqplus.order.entity.ProductOrderDetail;
import com.gpdi.hqplus.order.entity.query.OrderQuery;
import com.gpdi.hqplus.order.service.IProductOrderService;
import com.gpdi.hqplus.resources.entity.ProductResource;
import com.gpdi.hqplus.resources.entity.PropertyEquipment;
import com.gpdi.hqplus.resources.entity.PropertyResource;
import com.gpdi.hqplus.resources.entity.ServiceResource;
import com.gpdi.hqplus.resources.entity.query.ProductListQuery;
import com.gpdi.hqplus.resources.service.IPropertyEquipmentService;
import com.gpdi.hqplus.resources.service.IServiceResourceService;
import com.gpdi.hqplus.resources.service.ITimeResourceRelService;
import com.gpdi.hqplus.resources.service.impl.ProductResourceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: lianghb
 * @create: 2019-07-12 21:40
 **/
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class MeetingProductServiceImpl extends ProductResourceServiceImpl implements IMeetingProductService {
    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private RedisService redisService;

    @Autowired
    private IMeetingService meetingService;
    @Autowired
    private IServiceResourceService serviceResourceService;
    @Autowired
    private IPropertyEquipmentService equipmentService;
    @Autowired
    private ITimeResourceRelService timeResourceRelService;
    @Autowired
    private IProductOrderService orderService;

    /**
     * redis 分布式锁前缀
     */
    private final String REDIS_LOCK_PREFIX_MEETING_BOOK = "redis_lock_prefix_meeting_book::";

    @Override
    public Page<MeetingProductVO> pageProduct(ProductListQuery query) {
        query.setBusinessCode(BusinessCode.PROPERTY_MEETING_ROOM);


        Page<ProductResource> page = PageHelper.newInstance(query, ProductResource.class);

        Map<String, Object> beanMap = BeanPowerHelper.beanToMap(query);
        Integer startIndex = (query.getCurrent() - 1) * query.getSize();
        Integer endIndex = query.getCurrent() * query.getSize();
        beanMap.put("startIndex", startIndex);
        beanMap.put("endIndex", endIndex);

        int count = baseMapper.countMeetingRoomProduct(beanMap);
        page.setTotal(count);
        page.setPages(count / query.getSize() + 1);
        if (count == 0) {
            return BeanPowerHelper.mapPage(page, null);
        }

        List<ProductResource> records = baseMapper.pageMeetingRoomProduct(beanMap);

        // 获取商品列表
        List<MeetingProductVO> productList = CollectionUtil.createArrayList();
        Page<MeetingProductVO> result = BeanPowerHelper.mapPage(page, productList);
        if (CollectionUtil.isEmpty(records)) {
            BeanUtils.copyProperties(page, result);
            return result;
        }

        // 转换商品 vo
        Set<Long> propertyIdSet = CollectionUtil.createSet(records.size());
        Set<Long> productIdSet = CollectionUtil.createSet(records.size());
        for (ProductResource resource : records) {
            productList.add(product2VO(resource));
            propertyIdSet.add(resource.getResourceId());
            productIdSet.add(resource.getId());
        }

        // 获取物业资源记录并更新 vo
        Map<Long, PropertyResource> map = meetingService.listByIds(propertyIdSet);
        Map<Long, Integer[]> timeMap = timeResourceRelService.listByResIdDate(productIdSet, query.getDate());

        for (MeetingProductVO record : result.getRecords()) {
            PropertyResource propertyResource = map.get(record.getMeetingResourceId());
            if (propertyResource == null) {
                continue;
            }
            record.setName(propertyResource.getName());
            record.setArea(propertyResource.getArea());
            record.setSize(propertyResource.getSize());

            // 封面，从 PropertyResource 中取
            String extendVal = propertyResource.getExtendVal();
            Map<String, String> extendMap = JsonUtil.json2Map(extendVal);
            if (CollectionUtil.isEmpty(extendMap)) {
                continue;
            }
            record.setCoverImg(extendMap.get(MeetingRoomConstants.MEETING_ROOM_EXTEND_COVER_IMG));
            String otherImgJson = extendMap.get(MeetingRoomConstants.MEETING_ROOM_EXTEND_OTHER_IMG);
            record.setOtherImg(JsonUtil.json2Bean(otherImgJson, String[].class));
            record.setServiceStartTime(extendMap.get(MeetingRoomConstants.MEETING_ROOM_EXTEND_SERVICE_START_TIME));
            record.setServiceEndTime(extendMap.get(MeetingRoomConstants.MEETING_ROOM_EXTEND_SERVICE_END_TIME));

            Integer[] timeBit = timeMap.get(record.getId());
            TimeResourceVO timeResourceVO = meetingService.resource2TimeResource(propertyResource, timeBit);
            record.setTimeBit(timeResourceVO);

            record.setEquipments(JsonUtil.json2Bean(propertyResource.getEquipment(), List.class));
        }

        return result;
    }

    @Override
    public void addNewProduct(MeetingProductQuery query) {
        query.setId(idGenerator.getNumberId())
                .setBusinessCode(BusinessCode.PROPERTY_MEETING_ROOM)
                .setStatus(ProductResource.STATUS_NORMAL);

        // 增值服务
        Map<String, String> map = CollectionUtil.createHashMap(2);
        map.put(MeetingRoomConstants.MEETING_ROOM_EXTEND_SERVICE_RESOURCES,
                JsonUtil.bean2JsonString(query.getExtendProduct()));

        query.setExtensionResource(JsonUtil.bean2JsonString(map));

        // 获取增值服务信息

        boolean insert = query.insert();
        if (!insert) {
            log.error("insert product resource fail:meeting");
            throw new ApplicationException(ErrorCode.DATABASE_ERROR, "添加失败");
        }
    }

    @Override
    public MeetingProductVO getByProductId(Long id) {
        ProductResource resource = baseMapper.selectById(id);
        if (resource == null) {
            throw new ApplicationException(ErrorCode.RESOURCES_NOT_FIND, "查询数据失败");
        }
        PropertyResource propertyResource = meetingService.getById(resource.getResourceId());

        MeetingProductVO vo = product2VO(resource);
        String extensionResource = resource.getExtensionResource();

        Map<String, String> productExtendMap = JsonUtil.json2Map(extensionResource);
        Map<String, String> propertyExtendMap = JsonUtil.json2Map(propertyResource.getExtendVal());

        // 封面
        vo.setCoverImg(propertyExtendMap.get(MeetingRoomConstants.MEETING_ROOM_EXTEND_COVER_IMG));

        // 增值服务
        String extendServiceJson = productExtendMap.get(MeetingRoomConstants.MEETING_ROOM_EXTEND_SERVICE_RESOURCES);
        // 商品的 ids
        Long[] ids = JsonUtil.json2Bean(extendServiceJson, Long[].class);
        List<ProductResource> extendServiceResourceList = listByIds(ids);
        if (CollectionUtil.isEmpty(extendServiceResourceList)) {
            return vo;
        }

        // 通过商品的 ResourceId 获取增值服务的 id
        // todo ids 空指针
        Set<Long> serviceResIdSet = CollectionUtil.createSet(ids.length);
        for (ProductResource productResource : extendServiceResourceList) {
            serviceResIdSet.add(productResource.getResourceId());
        }

        Map<Long, ServiceResource> serviceResourceMap = serviceResourceService.listByIds(serviceResIdSet);
        List<MeetingExtendProductVO> extendProductVOList = CollectionUtil.createArrayList(extendServiceResourceList.size());
        for (ProductResource productResource : extendServiceResourceList) {
            MeetingExtendProductVO extendProductVO = new MeetingExtendProductVO();
            BeanUtils.copyProperties(productResource, extendProductVO);
            extendProductVO.setPriceUnitCode(productResource.getPriceType());
            ServiceResource serviceResource = serviceResourceMap.get(productResource.getResourceId());
            if (serviceResource != null) {
                extendProductVO.setName(serviceResource.getName());
            }

            extendProductVOList.add(extendProductVO);
        }

        // 设施
        String equipment = propertyResource.getEquipment();
        List<PropertyEquipment> equipments = null;
        if (StringUtil.isNotBlank(equipment) &&
                (equipments = JsonUtil.json2Bean(equipment, List.class)) != null) {
            vo.setEquipments(equipments);
        }

        vo.setName(propertyResource.getName());
        vo.setSize(propertyResource.getSize());
        vo.setArea(propertyResource.getArea());
        vo.setExtendProduct(extendProductVOList);

        return vo;
    }

    @Override
    public String createBookOrder(MeetingBookQuery query) {
        String redisKey = REDIS_LOCK_PREFIX_MEETING_BOOK + query.getProductId() + "::" + query.getDate();
        boolean lock = redisService.getLock(redisKey, 60);
        if (!lock) {
            throw new ApplicationException(ErrorCode.SERVICE_ERROR, "网络忙，请稍后再试");
        }

        try {
            ProductResource productResource = baseMapper.selectById(query.getProductId());
            if (productResource == null) {
                throw new ApplicationException(ErrorCode.RESOURCES_NOT_FIND, "查询会议室数据失败");
            }
            PropertyResource meetingResource = meetingService.getById(productResource.getResourceId());
            if (meetingResource == null) {
                throw new ApplicationException(ErrorCode.RESOURCES_NOT_FIND, "查询会议室数据失败");
            }

            int count = 0;
            /**
             * query.getTime().length = 48
             * 会议室租赁时间粒度为 1 小时，所以递增步长为 2
             */
            int step = 2;
            for (int i = 0; i < query.getTime().length; i += step) {
                if (query.getTime()[i] == 1) {
                    count++;
                }
                // 第一次预定时间，需要检查是否超过当前时间
                if (count == 1 && i / 2 <= LocalDateTime.now().getHour()) {
                    throw new ApplicationException(ErrorCode.SERVICE_ERROR, "不允许预定之前的时间");
                }
            }
            int[] timeBit = new int[48];
            for (int index : query.getTime()) {
                timeBit[index] = 1;
                timeBit[index + 1] = 1;
            }
            timeResourceRelService.updateByResIdDate(query.getProductId(), query.getDate(), timeBit);


            OrderQuery orderQuery = new OrderQuery();
            orderQuery.setBusinessCode(BusinessCode.PROPERTY_MEETING_ROOM);
            orderQuery.setPayType(query.getPayType());
            orderQuery.setUserName(query.getUserName());
            orderQuery.setUserConnect(query.getUserConnect());
            orderQuery.setName(meetingResource.getName());


            List<ProductOrderDetail> details = CollectionUtil.createArrayList();
            // 会议室
            ProductOrderDetail meetingDetail = productResource2OrderDetail(query.getProductId(), count);
            details.add(meetingDetail);


            // 增值服务
            if (CollectionUtil.isNotEmpty(query.getExtendServices())) {
                for (MeetingBookQuery.ExtendService extendService : query.getExtendServices()) {
                    details.add(productResource2OrderDetail(extendService.getProductId(), extendService.getCount()));
                }
            }

            orderQuery.setDetails(details);

            // 添加订单
            orderService.add(orderQuery);

            return orderQuery.getOrderCode();
        } finally {
            redisService.deleteLock(redisKey);
        }
    }

    @Override
    public ProductResource getById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void updateMeetingRoomProduct(MeetingAddQuery query) {
        ProductResource resource = baseMapper.selectById(query.getId());
        if (resource == null) {
            throw new ApplicationException(ErrorCode.RESOURCES_NOT_FIND, "查询会议室信息失败");
        }

        query.setMeetingResourceId(resource.getResourceId());

        // 增值服务
        Map<String, String> map = CollectionUtil.createHashMap(2);
        map.put(MeetingRoomConstants.MEETING_ROOM_EXTEND_SERVICE_RESOURCES,
                JsonUtil.bean2JsonString(query.getExtendProduct()));

        resource.setExtensionResource(JsonUtil.bean2JsonString(map));
        resource.setPrice(query.getPrice());
        resource.setDescription(query.getDescription());

        boolean update = resource.updateById();
        if (!update) {
            log.error("update MeetingRoomProduct fail");
            throw new ApplicationException(ErrorCode.DATABASE_ERROR, "更新失败");
        }

        meetingService.updateMeetingRoomProduct(query);
    }

    @Override
    public void updateStatus(MeetingAddQuery query) {
        int update = baseMapper.update(new ProductResource().setStatus(query.getStatus()), new QueryWrapper<ProductResource>()
                .lambda()
                .in(ProductResource::getId, query.getIds())
        );
        if (update != query.getIds().length) {
            log.error("update MeetingRoomProduct status fail");
            throw new ApplicationException(ErrorCode.DATABASE_ERROR, "更新失败");
        }

    }

    /**
     * ProductResource 2 ProductOrderDetail
     *
     * @param id
     * @param count
     * @return
     */
    private ProductOrderDetail productResource2OrderDetail(Long id, int count) {
        return new ProductOrderDetail()
                .setProductId(id)
                .setCount(count);
    }

    /**
     * listByIds
     *
     * @param ids
     * @return
     */
    private List<ProductResource> listByIds(Long[] ids) {
        return baseMapper.selectList(new QueryWrapper<ProductResource>()
                .lambda()
                .in(ProductResource::getId, ids)
        );
    }

    /**
     * ProductResource to MeetingProductVO
     *
     * @param resource
     * @return
     */
    private MeetingProductVO product2VO(ProductResource resource) {
        MeetingProductVO vo = new MeetingProductVO();
        BeanUtils.copyProperties(resource, vo);
        vo.setMeetingResourceId(resource.getResourceId());
        vo.setPriceUnitCode(resource.getPriceType());

        return vo;
    }
}
