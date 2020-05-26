package com.gpdi.hqplus.article.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gpdi.hqplus.article.entity.query.BannerQuery;
import com.gpdi.hqplus.article.entity.vo.BannerVO;
import com.gpdi.hqplus.resources.entity.ArticleResource;

import java.util.List;

/**
 * @Author: hmx
 * @CreateDate: 2019/07/01 19:57
 */
public interface IBannerService extends IService<ArticleResource> {

	/**
	 * app查询banner分页数据
	 * @param
	 * @return
	 */
	List<BannerVO> getBannerPage();

	/**
	 * 新增banner
	 * @param vo
	 * @return
	 */
	String addBanner(BannerVO vo);

	/**
	 * 获取banner分页数据
	 * @param query
	 * @return
	 */
	Page<BannerVO> getWebNewsPage(BannerQuery query);

	/**
	 * 根据id删除banner
	 * @param ids
	 * @return
	 */
	String deleteBannerByIds(List<Long> ids);
	/**
	 * 根据ID查询banner详情
	 * @param id
	 * @return
	 */
	BannerVO queryBannerById(Long id);
	/**
	 * 更新banner
	 * @param bannerVO
	 * @return
	 */
	String updateBanner(BannerVO bannerVO);
}
