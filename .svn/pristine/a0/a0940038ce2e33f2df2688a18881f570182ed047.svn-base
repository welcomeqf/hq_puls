package com.gpdi.hqplus.article.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.article.entity.query.NoticeQuery;
import com.gpdi.hqplus.article.entity.vo.NoticeVO;
import com.gpdi.hqplus.article.service.INoticeService;
import com.gpdi.hqplus.common.constants.BusinessCode;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.entity.vo.JsonResultVO;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.BeanPowerHelper;
import com.gpdi.hqplus.common.util.ExceptionUtil;
import com.gpdi.hqplus.common.util.IdGenerator;
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
 * @CreateDate: 2019/07/01 20:04
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeServiceImpl extends ServiceImpl<ArticleResourceMapper, ArticleResource> implements INoticeService {

	@Autowired
	private IdGenerator idGenerator;
	@Autowired
	private FileFeignClient fileFeignClient;

	/**
	 * @param query
	 * @Description: aap获取公告列表
	 * @Result
	 */
	@Override
	public Page<NoticeVO> getNoticePage(NoticeQuery query) {
		Page<ArticleResource> page = new Page();
		page.setSize(query.getSize());
		page.setCurrent(query.getCurrent());
		this.baseMapper.selectPage(page, new QueryWrapper<ArticleResource>().lambda()
				.eq(ArticleResource::getBusinessCode, BusinessCode.ARTICLE_PUBLIC_NOTICE)
				.eq(ArticleResource::getStatus, ArticleResource.STATUS_NORMAL));

		List<NoticeVO> vos = new ArrayList<>();
		if (page.getTotal() > 0) {
			for (ArticleResource articleResource : page.getRecords()) {
				NoticeVO vo = new NoticeVO();
				BeanUtils.copyProperties(articleResource, vo);
				vos.add(vo);
			}
		}
		return BeanPowerHelper.mapPage(page, vos);
	}


	/**
	 * @param id
	 * @description aap获取公告详情
	 * @Result articleResource
	 */

	@Override
	public NoticeVO getNoticeDetailById(Long id) {
		ArticleResource articleResource = this.baseMapper.selectById(id);
		if (articleResource == null) {
			log.error("未查询到相关数据 tb_article_resource noticeId=" + id);
			throw new ApplicationException(ErrorCode.SERVICE_ERROR, "未查询到相关数据!");
		} else {
			NoticeVO vo = new NoticeVO();
			BeanUtils.copyProperties(articleResource, vo);
			JsonResultVO jsonResultVO = fileFeignClient.getFileNamesById(vo.getId().toString());
			String[] fileNames = ((JSONArray) jsonResultVO.getData()).toArray(new String[0]);
			vo.setFileNames(fileNames);
			return vo;
		}
	}


	/**
	 * 新增公告
	 *
	 * @param noticeVO
	 * @return
	 */
	@Override
	public String addNotice(NoticeVO noticeVO) {
		ArticleResource articleResource = new ArticleResource();
		BeanUtils.copyProperties(noticeVO, articleResource);
		articleResource.setStatus(ArticleResource.STATUS_NORMAL);
		articleResource.setId(idGenerator.getNumberId());
		articleResource.setBusinessCode(BusinessCode.ARTICLE_PUBLIC_NOTICE);
		Integer integer = this.baseMapper.insert(articleResource);
		if (integer == 1) {
			try {
				fileFeignClient.setByResourceId(articleResource.getId(), noticeVO.getFileNames());
			} catch (Exception e) {
				log.error(ExceptionUtil.getMassage(e));
			}
			return "新增公告成功!";
		} else {
			log.error("新增公告失败：tb_article_resource  id = " + articleResource.getId());
			throw new ApplicationException(ErrorCode.SERVICE_ERROR, "新增公告失败!");
		}
	}

	/**
	 * web 查询公告集合
	 *
	 * @param query
	 * @return
	 */
	@Override
	public Page<NoticeVO> getWebNoticePage(NoticeQuery query) {
		Page<ArticleResource> articleResourcePage = new Page<>();
		articleResourcePage.setSize(query.getSize());
		articleResourcePage.setCurrent(query.getCurrent());
		LambdaQueryWrapper<ArticleResource> queryWrapper = new LambdaQueryWrapper<ArticleResource>()
				.eq(ArticleResource::getBusinessCode, BusinessCode.ARTICLE_PUBLIC_NOTICE)
				.eq(ArticleResource::getStatus, ArticleResource.STATUS_NORMAL);
		if (query.getParams() != null && query.getParams().getTitle() != null) {
			queryWrapper.like(ArticleResource::getTitle, query.getParams().getTitle());
		}
		if (query.getParams() != null && query.getParams().getId() != null) {
			queryWrapper.eq(ArticleResource::getTitle, query.getParams().getId());
		}
		queryWrapper.orderByDesc(ArticleResource::getCreateTime);

		this.baseMapper.selectPage(articleResourcePage, queryWrapper);
		List<NoticeVO> vos = new ArrayList<>();
		if (articleResourcePage.getTotal() > 0){
			for (ArticleResource articleResource: articleResourcePage.getRecords()){
				NoticeVO vo = new NoticeVO();
				BeanUtils.copyProperties(articleResource, vo);
				vos.add(vo);
			}
		}
			return BeanPowerHelper.mapPage(articleResourcePage, vos);
	}

	/**
	 * 删除公告
	 * @param ids
	 * @return
	 */
	@Override
	public String deleteNoticeByIds(List<Long> ids) {
		Integer integer = this.baseMapper.deleteBatchIds(ids);
		if (integer == ids.size()){
			return "删除成功!";
		}else {
			log.error("删除公告失败；ids="+ids.toString());
			throw new ApplicationException(ErrorCode.SERVICE_ERROR,"删除失败!");
		}
	}
	/**
	 * 编辑公告
	 * @param noticeVO
	 * @return
	 */
	@Override
	public String updateNotice(NoticeVO noticeVO) {
		ArticleResource articleResource = new ArticleResource();
		BeanUtils.copyProperties(noticeVO, articleResource);
		Integer integer = this.baseMapper.updateById(articleResource);
		if (integer == 1){
			try {
				fileFeignClient.setByResourceId(articleResource.getId(), noticeVO.getFileNames());
			} catch (Exception e) {
				log.error(ExceptionUtil.getMassage(e));
			}
			return "编辑成功!";
		}else {
			log.error("编辑公告失败：tb_article_resource  id = " + articleResource.getId());
			throw new ApplicationException(ErrorCode.SERVICE_ERROR,"编辑公告失败!");
		}
	}
}
