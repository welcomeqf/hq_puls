package com.gpdi.hqplus.article.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.article.entity.query.NewsQuery;
import com.gpdi.hqplus.article.entity.vo.NewsDetailVO;
import com.gpdi.hqplus.article.entity.vo.NewsVO;
import com.gpdi.hqplus.common.constants.BusinessCode;
import com.gpdi.hqplus.article.service.INewsService;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.entity.vo.JsonResultVO;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.BeanPowerHelper;
import com.gpdi.hqplus.common.util.ExceptionUtil;
import com.gpdi.hqplus.common.util.IdGenerator;
import com.gpdi.hqplus.common.util.StringUtil;
import com.gpdi.hqplus.resources.entity.ArticleResource;
import com.gpdi.hqplus.resources.feign.FileFeignClient;
import com.gpdi.hqplus.resources.mapper.ArticleResourceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: hmx
 * @CreateDate: 2019/07/01 16:00
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NewsServiceImpl extends ServiceImpl<ArticleResourceMapper, ArticleResource> implements INewsService {
	@Autowired
	private IdGenerator idGenerator;
	@Autowired
	private FileFeignClient fileFeignClient;

	/**
	 * aap获取资讯列表
	 *
	 * @param query
	 * @return
	 */
	@Override
	public Page<NewsVO> getNewsPage(NewsQuery query) {
		Page<ArticleResource> page = new Page();
		page.setSize(query.getSize());
		page.setCurrent(query.getCurrent());

		this.baseMapper.selectPage(page, new QueryWrapper<ArticleResource>()
				.lambda()
				.eq(ArticleResource::getBusinessCode, BusinessCode.ARTICLE_PARK_NEWS)
				.eq(ArticleResource::getStatus, ArticleResource.STATUS_NORMAL));

		List<NewsVO> vos = new ArrayList<>();
		if (page.getTotal() > 0) {
			for (ArticleResource articleResource : page.getRecords()) {
				NewsVO vo = new NewsVO();
				BeanUtils.copyProperties(articleResource, vo);
				vos.add(vo);
			}
		}

		return BeanPowerHelper.mapPage(page, vos);
	}

	/**
	 * web端查询资讯分页信息
	 *
	 * @param query
	 * @return
	 */
	@Override
	public Page<NewsVO> getWebNewsPage(NewsQuery query) {
		Page<ArticleResource> page = new Page();
		page.setSize(query.getSize());
		page.setCurrent(query.getCurrent());

		LambdaQueryWrapper<ArticleResource> queryWrapper = new QueryWrapper<ArticleResource>()
				.lambda()
				.eq(ArticleResource::getBusinessCode, BusinessCode.ARTICLE_PARK_NEWS)
				.eq(ArticleResource::getStatus, ArticleResource.STATUS_NORMAL);

		// 查询条件
		if (query.getParams() != null && StringUtil.isNotBlank(query.getParams().getTitle())) {
			queryWrapper.like(ArticleResource::getTitle, query.getParams().getTitle());
		}

		//	排序
		queryWrapper.orderByDesc(ArticleResource::getCreateTime);

		this.baseMapper.selectPage(page, queryWrapper);

		List<NewsVO> vos = new ArrayList<>();
		if (page.getTotal() > 0) {
			for (ArticleResource articleResource : page.getRecords()) {
				NewsVO vo = new NewsVO();
				BeanUtils.copyProperties(articleResource, vo);
				vos.add(vo);
			}
		}

		return BeanPowerHelper.mapPage(page, vos);
	}

	/**
	 * 新增资讯
	 *
	 * @param newsVO
	 * @return
	 */
	@Override
	public String addNews(NewsDetailVO newsVO) {
		ArticleResource articleResource = new ArticleResource();
		BeanUtils.copyProperties(newsVO, articleResource);
		articleResource.setId(idGenerator.getNumberId());
		articleResource.setBusinessCode(BusinessCode.ARTICLE_PARK_NEWS);
		articleResource.setStatus(ArticleResource.STATUS_NORMAL);
		Integer integer = this.baseMapper.insert(articleResource);
		if (integer == 1) {
			// 调用 file 微服务设置文件对应
			try {
				fileFeignClient.setByResourceId(articleResource.getId(), newsVO.getFileNames());
			} catch (Exception e) {
				log.error(ExceptionUtil.getMassage(e));
			}
			return "新增资讯成功!";
		} else {
			log.error("新增资讯失败：tb_article_resource  id = " + articleResource.getId());
			throw new ApplicationException(ErrorCode.SERVICE_ERROR, "新增资讯失败!");
		}
	}

	/**
	 * 根据id删除资讯
	 *
	 * @param ids
	 * @return
	 */
	@Override
	public String deleteNewsByIds(List<Long> ids) {
		Integer integer = this.baseMapper.deleteBatchIds(ids);
		if (integer == ids.size()) {
			return "删除成功!";
		} else {
			log.error("删除资讯失败!");
			throw new ApplicationException(ErrorCode.SERVICE_ERROR, "删除资讯失败!");
		}
	}

	/**
	 * 编辑资讯
	 *
	 * @param newsVO
	 * @return
	 */
	@Override
	public String updateNewsById(NewsDetailVO newsVO) {
		ArticleResource articleResource = new ArticleResource();
		BeanUtils.copyProperties(newsVO, articleResource);
		Integer integer = this.baseMapper.updateById(articleResource);
		if (integer == 1) {
			// 调用 file 微服务设置文件对应
			try {
				fileFeignClient.setByResourceId(articleResource.getId(), newsVO.getFileNames());
			} catch (Exception e) {
				log.error(ExceptionUtil.getMassage(e));
			}
			return "修改成功!";
		} else {
			log.error("编辑资讯失败 tb_article_resource : newsId=" + newsVO.getId());
			throw new ApplicationException(ErrorCode.SERVICE_ERROR, "编辑资讯失败!");
		}
	}

	/**
	 * 根据id查询资讯
	 *
	 * @param id
	 * @return
	 */
	@Override
	public NewsDetailVO getNewsDetailById(Long id) {
		ArticleResource articleResource = this.baseMapper.selectById(id);
		if (articleResource == null) {
			log.error("未查询到相关数据 tb_article_resource newsId=" + id);
			throw new ApplicationException(ErrorCode.SERVICE_ERROR, "未查询到相关数据!");
		} else {
			NewsDetailVO vo = new NewsDetailVO();
			BeanUtils.copyProperties(articleResource, vo);
			JsonResultVO jsonResultVO = fileFeignClient.getFileNamesById(vo.getId().toString());
			String[] fileNames = ((JSONArray) jsonResultVO.getData()).toArray(new String[0]);
			vo.setFileNames(fileNames);

			return vo;
		}
	}


}
