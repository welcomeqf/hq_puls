package com.gpdi.hqplus.apartment.controller.manager;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.apartment.entity.query.SeriesPageQuery;
import com.gpdi.hqplus.apartment.entity.query.SeriesQuery;
import com.gpdi.hqplus.apartment.entity.vo.SeriesListVO;
import com.gpdi.hqplus.apartment.entity.vo.SeriesManagerListVO;
import com.gpdi.hqplus.apartment.service.IApartmentSeriesService;
import com.gpdi.hqplus.apartment.validate.ApartmentSeriesValidate;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.CollectionUtil;
import com.gpdi.hqplus.common.validate.PageQueryValidate;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 公寓系列 前端控制器
 * </p>
 *
 * @author lianghb
 * @since 2019-07-20
 */
@Api(tags = "公寓系统服务")
@Slf4j
@RestController
@RequestMapping("/v1/manager/resources/apartment/series")
@PreAuthorize("hasRole('admin')")
public class ApartmentSeriesManagerController {

    @Autowired
    private IApartmentSeriesService seriesService;

    /**
     * add
     *
     * @param query
     */
    @PostMapping("/addSeries")
    public void addSeries(@RequestBody SeriesQuery query) {
        ApartmentSeriesValidate.checkAdd(query);
        seriesService.addSeries(query);
    }

    /**
     * delete
     *
     * @param query
     */
    @PostMapping("/deleteSeries")
    public void deleteSeries(@RequestBody SeriesQuery query) {
        if (CollectionUtil.isEmpty(query.getIds())) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "id不能为空");
        }
        seriesService.deleteSeries(query.getIds());
    }

    /**
     * disabled
     *
     * @param query
     */
    @PostMapping("/disabledSeries")
    public void disabledSeries(@RequestBody SeriesQuery query) {
        if (CollectionUtil.isEmpty(query.getIds())) {
            throw new ApplicationException(ErrorCode.PARAMETER_ERROR, "id不能为空");
        }
        seriesService.disabledSeries(query.getIds());
    }

    /**
     * update
     *
     * @param query
     */
    @PostMapping("/updateSeries")
    public void updateSeries(@RequestBody SeriesQuery query) {
        ApartmentSeriesValidate.checkUpdate(query);
        seriesService.updateSeries(query);
    }

    /**
     * app page
     *
     * @param query
     * @return
     */
    @PostMapping("/page")
    public Page<SeriesManagerListVO> page(@RequestBody SeriesPageQuery query) {
        PageQueryValidate.check(query);
        return seriesService.pageManager(query);
    }

    /**
     * list all
     *
     * @return
     */
    @GetMapping("/listAll")
    public List<SeriesListVO> listAll() {
        return seriesService.listAll();
    }
}

