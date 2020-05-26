package com.gpdi.hqplus.base.controller;


import com.gpdi.hqplus.base.entity.BannerVO;
import com.gpdi.hqplus.base.service.IArticleResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 
文章资源 前端控制器
 * </p>
 *
 * @author lianghb
 * @since 2019-06-30
 */
@Api
@Slf4j
@RestController
@RequestMapping("/v1/app/base/articleResource")
public class ArticleResourceController {
    @Autowired
    private IArticleResourceService articleResourceService;


    /**
     * banner
     *
     * @param name
     * @return
     */
    @ApiOperation(value = "banner", notes = "banner")
    @GetMapping
    public List<BannerVO> banner() {
        return articleResourceService.listBanner();
    }

}

