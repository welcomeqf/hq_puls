package com.gpdi.hqplus.officebuild.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.common.constants.BusinessCode;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.*;
import com.gpdi.hqplus.officebuild.entity.OfficeVO;
import com.gpdi.hqplus.officebuild.entity.params.OfficeParamsVO;
import com.gpdi.hqplus.officebuild.service.OfficeService;
import com.gpdi.hqplus.resources.constants.ResourceType;
import com.gpdi.hqplus.resources.entity.PropertyEquipment;
import com.gpdi.hqplus.resources.entity.PropertyRoomType;
import com.gpdi.hqplus.resources.service.IPropertyEquipmentService;
import com.gpdi.hqplus.resources.service.impl.PropertyRoomTypeServiceImpl;
import com.gpdi.hqplus.resources.util.CopyProperties;
import com.gpdi.hqplus.resources.validate.CheckEmptyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Description 写字楼系列service
 * @Author wzr
 * @CreateDate 2019-07-08
 * @Time 15:59
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class OfficeServiceImpl extends PropertyRoomTypeServiceImpl implements OfficeService {


    @Autowired
    IdGenerator idGenerator;

    @Autowired
    IPropertyEquipmentService equipmentService;

    /**
     * 根据id查询写字楼系列
     *
     * @param officeId
     * @return
     */
    @Override
    public OfficeVO getOfficeById(Long officeId) {
        PropertyRoomType propertyRoomType = baseMapper.selectById(officeId);
        if (propertyRoomType==null){
            log.error("写字楼系列查询失败，入参id="+officeId);
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR,"写字楼系列查询失败，请检查参数是否正确");
        }
        return propertyRoomType2office(propertyRoomType);
    }

    /**
     * 分页查询写字楼系列列表
     *
     * @param page
     * @return
     */
    @Override
    public Page listOfficeByPage(Page page) {
        baseMapper.selectPage(page, new QueryWrapper<PropertyRoomType>()
                .lambda()
                .eq(PropertyRoomType::getBusinessCode, BusinessCode.PROPERTY_OFFICE_BUILD)
                .eq(PropertyRoomType::getType, ResourceType.OFFICE_TYPE)
                .orderByDesc(PropertyRoomType::getCreateTime)
        );
        List records = page.getRecords();
        if (CollectionUtil.isNotEmpty(records)){
            List<OfficeVO> recordsNew = CollectionUtil.createArrayList();
            for (Object record:records){
                OfficeVO officeVO = propertyRoomType2office((PropertyRoomType) record);
                recordsNew.add(officeVO);
            }
            page.setRecords(recordsNew);
        }
        return page;
    }

    /**
     * 添加写字楼系列（基本等于公寓房型）
     *
     * @param officeParamsVO
     */
    @Override
    public void add(OfficeParamsVO officeParamsVO) {
        String[] params = {"name",
                "pictures",
                "floor",
                "unitCode",
                "size",
                "equipmentIds",
                "unitPrice",
                "unitPriceType",
                "totalPrice",
                "totalPriceType",
                "workerNum",
                "saleStatus",
                "decorationStatus",
                "decorationStatus",
                "propertyYears",
                "modelUrl"};
        CheckEmptyUtil.check(JsonUtil.bean2Map(officeParamsVO),params);

        OfficeVO officeVO = new OfficeVO();
        CopyProperties.copyAll(officeParamsVO,officeVO);

        PropertyRoomType propertyRoomType = office2propertyRoomType(officeVO);

        OfficeParamsVO extendParams = JsonUtil.json2Bean(propertyRoomType.getExtendVal(), OfficeParamsVO.class);
        //把equipmentIds插入到extendVal内
        extendParams.setEquipmentIds(officeParamsVO.getEquipmentIds());
        propertyRoomType.setExtendVal(JsonUtil.bean2JsonString(extendParams));
        propertyRoomType.setId(idGenerator.getNumberId());
        int insert = baseMapper.insert(propertyRoomType);
        if (insert!=1){
            log.error("写字楼系列新增失败");
            throw new ApplicationException(ErrorCode.SERVICE_ERROR,"写字楼系列新增失败");
        }

    }

    /**
     * 编辑办公室系列
     *
     * @param officeParamsVO
     */
    @Override
    public void edit(OfficeParamsVO officeParamsVO) {
        //检查必要参数
        String[] params = {"id",
                "name",
                "pictures",
                "floor",
                "unitCode",
                "size",
                "equipmentIds",
                "unitPrice",
                "unitPriceType",
                "totalPrice",
                "totalPriceType",
                "workerNum",
                "saleStatus",
                "decorationStatus",
                "decorationStatus",
                "propertyYears",
                "modelUrl"};
        CheckEmptyUtil.check(JsonUtil.bean2Map(officeParamsVO),params);

        OfficeVO officeVO = new OfficeVO();
        CopyProperties.copyAll(officeParamsVO,officeVO);
        PropertyRoomType propertyRoomType = office2propertyRoomType(officeVO);
        String extendVal = propertyRoomType.getExtendVal();
        OfficeParamsVO extendParams = JsonUtil.json2Bean(extendVal, OfficeParamsVO.class);
        extendParams.setEquipmentIds(officeParamsVO.getEquipmentIds());
        propertyRoomType.setExtendVal(JsonUtil.bean2JsonString(extendParams));
        int i = baseMapper.updateById(propertyRoomType);
        if (i!=1){
            log.error("写字楼系列更新失败，id="+propertyRoomType.getId());
            throw new ApplicationException(ErrorCode.SERVICE_ERROR,"写字楼系列更新失败");
        }

    }

    /**
     * 删除办公室系列，需要id
     *
     * @param officeVO
     */
    @Override
    public void delete(OfficeVO officeVO) {
        Long id = officeVO.getId();
        if (id!=null){
            int i = baseMapper.deleteById(id);
            if (i!=1){
                log.error("删除办公室系列失败，id="+id);
                throw new ApplicationException(ErrorCode.SERVICE_ERROR,"办公室系列删除失败");
            }
        }else {
            log.error("获取办公室系列id失败");
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR,"获取办公室系列id失败");
        }
    }

    public OfficeVO propertyRoomType2office(PropertyRoomType propertyRoomType){
        OfficeVO office = BeanPowerHelper.mapCompleteOverrider(propertyRoomType, OfficeVO.class);
        String extendVal = propertyRoomType.getExtendVal();
        if (StringUtil.isEmpty(extendVal)){
            log.error("写字楼系列数据错误，id="+propertyRoomType.getId());
            throw new ApplicationException(ErrorCode.UNKNOWN_ERROR,"写字楼系列数据错误");
        }

        OfficeVO officeExtend = JsonUtil.json2Bean(extendVal, OfficeVO.class);
        //获取设备ids
        Map extendMap = JsonUtil.json2Map(extendVal);
        JSONArray equipmentIds = JSONArray.parseArray(JsonUtil.bean2JsonString(extendMap.get("equipmentIds")));
        //获取设备列表
        if (!equipmentIds.isEmpty()){
            List<PropertyEquipment> equipments = CollectionUtil.createArrayList();
            for (int i = 0;i<equipmentIds.size();i++){
                PropertyEquipment equipment = equipmentService.getById(equipmentIds.getLong(i));
                equipments.add(equipment);
            }
            officeExtend.setEquipments(equipments);
        }
        //读取图片列表
        List<String> pictures = JsonUtil.json2Bean(propertyRoomType.getImgSrc(), List.class);
        officeExtend.setPictures(pictures);

        CopyProperties.copyIgnoreNull(officeExtend,office);
        return office;
    }

    public PropertyRoomType office2propertyRoomType(OfficeVO office){
        //复制通用字段
        PropertyRoomType propertyRoomType = BeanPowerHelper.mapCompleteOverrider(office, PropertyRoomType.class);
        //设置图片
        List<String> pictures = office.getPictures();
        propertyRoomType.setImgSrc(JsonUtil.bean2JsonString(pictures));

        //获取设备列表
        List<PropertyEquipment> equipments = office.getEquipments();

        OfficeParamsVO extendParams = new OfficeParamsVO();
        //转为设备id列表
        if (CollectionUtil.isNotEmpty(equipments)){
            JSONArray equipmentIds = new JSONArray();
            for (PropertyEquipment equipment:equipments){
                equipmentIds.add(equipment.getId());
            }
            extendParams.setEquipmentIds(equipmentIds.toJavaList(Long.class));
        }
        //填充额外字段
        extendParams.setAddress(office.getAddress());
        extendParams.setFloor(office.getFloor());
        extendParams.setUnitCode(office.getUnitCode());
        extendParams.setSize(office.getSize());
        extendParams.setUnitPrice(office.getUnitPrice());
        extendParams.setUnitPriceType(office.getUnitPriceType());
        extendParams.setTotalPrice(office.getTotalPrice());
        extendParams.setTotalPriceType(office.getTotalPriceType());
        extendParams.setWorkerNum(office.getWorkerNum());
        extendParams.setSaleStatus(office.getSaleStatus());
        extendParams.setDecorationStatus(office.getDecorationStatus());
        extendParams.setPropertyYears(office.getPropertyYears());
        extendParams.setModelUrl(office.getModelUrl());
        extendParams.setSurrounding(office.getSurrounding());
        extendParams.setDynamics(office.getDynamics());

        propertyRoomType.setExtendVal(JsonUtil.bean2JsonString(extendParams));

        propertyRoomType.setBusinessCode(BusinessCode.PROPERTY_OFFICE_BUILD);
        if (StringUtil.isEmpty(propertyRoomType.getType())){
            propertyRoomType.setType(ResourceType.OFFICE_TYPE);
        }

        return propertyRoomType;
    }
}
