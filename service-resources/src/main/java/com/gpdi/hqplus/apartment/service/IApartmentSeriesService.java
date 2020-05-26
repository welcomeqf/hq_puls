package com.gpdi.hqplus.apartment.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.apartment.entity.ApartmentSeries;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gpdi.hqplus.apartment.entity.query.SeriesPageQuery;
import com.gpdi.hqplus.apartment.entity.query.SeriesQuery;
import com.gpdi.hqplus.apartment.entity.vo.SeriesDetailVO;
import com.gpdi.hqplus.apartment.entity.vo.SeriesListVO;
import com.gpdi.hqplus.apartment.entity.vo.SeriesManagerListVO;

import java.util.List;

/**
 * <p>
 * 公寓系列 服务类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-20
 */
public interface IApartmentSeriesService extends IService<ApartmentSeries> {

    /**
     * 添加
     *
     * @param query
     */
    void addSeries(SeriesQuery query);

    /**
     * 删除
     *
     * @param id
     */
    void deleteSeries(List<Long> id);

    /**
     * 批量禁用
     *
     * @param id
     */
    void disabledSeries(List<Long> id);

    /**
     * 更新
     *
     * @param query
     */
    void updateSeries(SeriesQuery query);

    /**
     * app 列表
     *
     * @param query
     * @return
     */
    Page<SeriesListVO> pageSimple(SeriesPageQuery query);

    /**
     * app 列表
     *
     * @param query
     * @return
     */
    Page<SeriesManagerListVO> pageManager(SeriesPageQuery query);

    /**
     * 获取所有
     * @return
     */
    List<SeriesListVO> listAll();

    /**
     * id 获取系列详情
     * @param id
     * @return
     */
    SeriesDetailVO getDetailById(Long id);
}
