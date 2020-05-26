package com.gpdi.hqplus.article.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gpdi.hqplus.article.entity.query.NewsQuery;
import com.gpdi.hqplus.article.entity.vo.NewsDetailVO;
import com.gpdi.hqplus.article.entity.vo.NewsVO;
import com.gpdi.hqplus.resources.entity.ArticleResource;

import java.util.List;

/**
 * @Author: hmx
 * @CreateDate: 2019/07/01 16:00
 */
public interface INewsService extends IService<ArticleResource> {

	/**
	 * app查询资讯分页数据
	 * @param query
	 * @return
	 */
	Page<NewsVO> getNewsPage(NewsQuery query);

	/**
	 * web端查询资讯分页信息
	 * @param query
	 * @return
	 */
	Page<NewsVO> getWebNewsPage(NewsQuery query);

	/**
	 * web 新增资讯
	 * @param newsVO
	 * @return
	 */
	String addNews(NewsDetailVO newsVO);

	/**
	 * 编辑资讯
	 * @param newsVO
	 * @return
	 */
	String updateNewsById(NewsDetailVO newsVO);

	/**
	 * 根据ID删除资讯
	 * @param ids
	 * @return
	 */
	String deleteNewsByIds(List<Long> ids);

	/**
	 * 根据id查询资讯
	 * @param id
	 * @return
	 */
	NewsDetailVO getNewsDetailById(Long id);
}
