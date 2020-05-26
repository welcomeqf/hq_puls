package com.gpdi.hqplus.resources.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gpdi.hqplus.resources.entity.ArticleResource;
import com.gpdi.hqplus.resources.mapper.ArticleResourceMapper;
import com.gpdi.hqplus.resources.service.IArticleResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * <p>
 * 文章资源 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-02
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleResourceServiceImpl extends ServiceImpl<ArticleResourceMapper, ArticleResource> implements IArticleResourceService {

    @Override
    public List<ArticleResource> listByBusinessCode(String code) {
        return baseMapper.selectList(new QueryWrapper<ArticleResource>()
                .lambda()
                .eq(ArticleResource::getBusinessCode, code)
                .eq(ArticleResource::getStatus, ArticleResource.STATUS_NORMAL)
                .orderByDesc(ArticleResource::getSerialNumber)
        );
    }
}
