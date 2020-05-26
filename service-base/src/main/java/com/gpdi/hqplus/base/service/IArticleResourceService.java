package com.gpdi.hqplus.base.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.base.entity.ArticleResource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gpdi.hqplus.base.entity.BannerVO;

import java.util.List;

/**
 * <p>
 * 
文章资源 服务类
 * </p>
 *
 * @author lianghb
 * @since 2019-06-30
 */
public interface IArticleResourceService extends IService<ArticleResource> {
    List<BannerVO> listBanner();
    Page<BannerVO> listBannerByPage(int index, int pageSize);
}
