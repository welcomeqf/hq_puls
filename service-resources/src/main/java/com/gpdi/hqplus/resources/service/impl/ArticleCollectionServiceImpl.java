package com.gpdi.hqplus.resources.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gpdi.hqplus.resources.entity.ArticleCollection;
import com.gpdi.hqplus.resources.entity.ArticleResource;
import com.gpdi.hqplus.resources.mapper.ArticleCollectionMapper;
import com.gpdi.hqplus.resources.service.IArticleCollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 文章收藏表 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-02
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleCollectionServiceImpl extends ServiceImpl<ArticleCollectionMapper, ArticleCollection> implements IArticleCollectionService {

}
