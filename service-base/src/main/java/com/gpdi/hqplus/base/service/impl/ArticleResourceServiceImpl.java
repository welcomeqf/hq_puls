package com.gpdi.hqplus.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.base.entity.ArticleResource;
import com.gpdi.hqplus.base.entity.BannerVO;
import com.gpdi.hqplus.base.mapper.ArticleResourceMapper;
import com.gpdi.hqplus.base.service.IArticleResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.common.constants.BusinessCode;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.BeanPowerHelper;
import com.gpdi.hqplus.common.util.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 
文章资源 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-06-30
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleResourceServiceImpl extends ServiceImpl<ArticleResourceMapper, ArticleResource> implements IArticleResourceService {

    @Override
    public List<BannerVO> listBanner() {
        List<ArticleResource> articleResources = this.baseMapper.selectList(new QueryWrapper<ArticleResource>()
                .lambda()
                .eq(ArticleResource::getBusinessCode,BusinessCode.ARTICLE_BANNER)
                .eq(ArticleResource::getStatus,ArticleResource.STATUS_NORMAL)
        );
        if(CollectionUtil.isEmpty(articleResources)){
            return CollectionUtil.createArrayList();
        }
        List<BannerVO> result = CollectionUtil.createArrayList(articleResources.size());
        for (ArticleResource resource : articleResources) {
            BannerVO bannerVO = BeanPowerHelper.mapCompleteOverrider(resource, BannerVO.class);
            bannerVO.setImgSrc(resource.getContext());
            result.add(bannerVO);
        }

        return result;
    }

    @Override
    public Page<BannerVO> listBannerByPage(int index, int pageSize) {
        Page page = new Page<>();
        page.setCurrent(index);
        page.setSize(pageSize);

        baseMapper.selectPage(page,new QueryWrapper<ArticleResource>()
                .lambda()
                .eq(ArticleResource::getBusinessCode, BusinessCode.ARTICLE_BANNER)
                .eq(ArticleResource::getStatus, ArticleResource.STATUS_NORMAL)
        );
        Page pageNew = new Page<BannerVO>();
        BeanPowerHelper.mapCompleteOverrider(page,pageNew);
        return pageNew;
    }
}
