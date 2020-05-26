package com.gpdi.hqplus.apartment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.apartment.entity.ApartmentSeries;
import com.gpdi.hqplus.apartment.entity.query.SeriesPageQuery;
import com.gpdi.hqplus.apartment.entity.query.SeriesQuery;
import com.gpdi.hqplus.apartment.entity.vo.SeriesDetailVO;
import com.gpdi.hqplus.apartment.entity.vo.SeriesListVO;
import com.gpdi.hqplus.apartment.entity.vo.SeriesManagerListVO;
import com.gpdi.hqplus.apartment.entity.vo.SeriesPriceVO;
import com.gpdi.hqplus.apartment.mapper.ApartmentSeriesMapper;
import com.gpdi.hqplus.apartment.service.IApartmentSeriesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 公寓系列 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-20
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ApartmentSeriesServiceImpl extends ServiceImpl<ApartmentSeriesMapper, ApartmentSeries> implements IApartmentSeriesService {

    @Autowired
    private IdGenerator idGenerator;

    @Override
    public void addSeries(SeriesQuery query) {
        query.setId(idGenerator.getNumberId());
        generateApartmentSeries(query);
        boolean insert = query.insert();
        if (!insert) {
            log.error("insert ApartmentSeries fail");
            throw new ApplicationException(ErrorCode.DATABASE_ERROR, "添加失败");
        }
    }

    @Override
    public void deleteSeries(List<Long> ids) {
        int delete = baseMapper.deleteBatchIds(ids);
        if (delete != ids.size()) {
            log.error("delete ApartmentSeries fail");
            throw new ApplicationException(ErrorCode.DATABASE_ERROR, "删除失败");
        }
    }

    @Override
    public void disabledSeries(List<Long> ids) {
        ApartmentSeries series = new ApartmentSeries();
        series.setStatus(ApartmentSeries.STATUS_DISABLED);

        int update = baseMapper.update(series, new QueryWrapper<ApartmentSeries>()
                .lambda()
                .in(ApartmentSeries::getId, ids)
        );

        if (update != ids.size()) {
            log.error("update ApartmentSeries fail");
            throw new ApplicationException(ErrorCode.DATABASE_ERROR, "更新失败");
        }
    }

    @Override
    public void updateSeries(SeriesQuery query) {
        generateApartmentSeries(query);
        boolean update = query.updateById();
        if (!update) {
            log.error("update ApartmentSeries fail");
            throw new ApplicationException(ErrorCode.DATABASE_ERROR, "更新失败");
        }
    }

    @Override
    public Page<SeriesListVO> pageSimple(SeriesPageQuery query) {
        Page<ApartmentSeries> page = PageHelper.newInstance(query, ApartmentSeries.class);

        baseMapper.selectPage(page, new QueryWrapper<ApartmentSeries>()
                .lambda()
                .eq(ApartmentSeries::getStatus, ApartmentSeries.STATUS_NORMAL)
                .orderByDesc(ApartmentSeries::getId)
        );

        List<SeriesListVO> result = CollectionUtil.createArrayList();
        if (CollectionUtil.isNotEmpty(page.getRecords())) {
            for (ApartmentSeries record : page.getRecords()) {
                result.add(series2SeriesListVO(record));
            }
        }

        return BeanPowerHelper.mapPage(page, result);
    }

    @Override
    public Page<SeriesManagerListVO> pageManager(SeriesPageQuery query) {
        Page<ApartmentSeries> page = PageHelper.newInstance(query, ApartmentSeries.class);

        LambdaQueryWrapper<ApartmentSeries> queryWrapper = new QueryWrapper<ApartmentSeries>()
                .lambda()
                .orderByDesc(ApartmentSeries::getId);

        if (StringUtil.isNotBlank(query.getName())) {
            queryWrapper.like(ApartmentSeries::getName, query.getName());
        }
        if (StringUtil.isNotBlank(query.getStatus())) {
            queryWrapper.eq(ApartmentSeries::getStatus, query.getStatus());
        }

        baseMapper.selectPage(page, queryWrapper);

        List<SeriesManagerListVO> result = CollectionUtil.createArrayList();
        if (CollectionUtil.isNotEmpty(page.getRecords())) {
            for (ApartmentSeries record : page.getRecords()) {
                result.add(series2SeriesManagerListVO(record));
            }
        }

        return BeanPowerHelper.mapPage(page, result);
    }

    @Override
    public List<SeriesListVO> listAll() {
        List<ApartmentSeries> seriesList = baseMapper.selectList(new QueryWrapper<ApartmentSeries>()
                .lambda()
                .orderByDesc(ApartmentSeries::getId)
        );

        List<SeriesListVO> result = CollectionUtil.createArrayList();
        if (CollectionUtil.isNotEmpty(seriesList)) {
            for (ApartmentSeries apartmentSeries : seriesList) {
                result.add(series2SeriesListVO(apartmentSeries));
            }
        }

        return result;
    }

    @Override
    public SeriesDetailVO getDetailById(Long id) {
        ApartmentSeries series = baseMapper.selectById(id);
        if (series == null) {
            throw new ApplicationException(ErrorCode.RESOURCES_NOT_FIND, "查询数据失败");
        }
        return null;
    }

    /**
     * 初始化 bean
     *
     * @param query
     */
    private void generateApartmentSeries(SeriesQuery query) {
        query.setEquipments(JsonUtil.bean2JsonString(query.getEquipmentList()));
        query.setShowImages(JsonUtil.bean2JsonString(query.getShowImageList()));
    }

    /**
     * ApartmentSeries 2 SeriesListVO
     *
     * @param series
     * @return
     */
    private SeriesListVO series2SeriesListVO(ApartmentSeries series) {
        SeriesListVO vo = new SeriesListVO();
        BeanUtils.copyProperties(series, vo);
        vo.setShowImages(JsonUtil.json2Bean(series.getShowImages(), String[].class));
        vo.setEquipments(JsonUtil.json2Bean(series.getEquipments(), List.class));
        vo.setPrice(getSeriesPriceVO(series.getId()));
        return vo;
    }

    /**
     * ApartmentSeries 2 SeriesListVO
     *
     * @param series
     * @return
     */
    private SeriesDetailVO series2ApartmentSeries(ApartmentSeries series) {
        SeriesDetailVO vo = new SeriesDetailVO();
        BeanUtils.copyProperties(series, vo);
        vo.setEquipments(JsonUtil.json2Bean(series.getEquipments(), List.class));
        vo.setShowImages(JsonUtil.json2Bean(series.getShowImages(), String[].class));
        return vo;
    }

    /**
     * ApartmentSeries 2 SeriesManagerListVO
     *
     * @param series
     * @return
     */
    private SeriesManagerListVO series2SeriesManagerListVO(ApartmentSeries series) {
        SeriesManagerListVO vo = new SeriesManagerListVO();
        BeanUtils.copyProperties(series, vo);
        vo.setEquipmentList(JsonUtil.json2Bean(series.getEquipments(), List.class));
        vo.setShowImageList(JsonUtil.json2Bean(series.getShowImages(), String[].class));
        return vo;
    }

    /**
     * 获取价格区间，如果没有，返回 0
     *
     * @param id
     * @return
     */
    private SeriesPriceVO getSeriesPriceVO(Long id) {
        SeriesPriceVO price = baseMapper.getPriceBySeries(id);
        if (price == null) {
            price = new SeriesPriceVO(new BigDecimal("0"), new BigDecimal("0"));
        }

        return price;
    }
}
