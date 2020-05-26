package com.gpdi.hqplus.article.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.article.entity.query.BannerQuery;
import com.gpdi.hqplus.article.entity.vo.BannerVO;
import com.gpdi.hqplus.article.service.IBannerService;
import com.gpdi.hqplus.common.constants.BusinessCode;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.entity.vo.JsonResultVO;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.*;
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
public class BannerServiceImpl extends ServiceImpl<ArticleResourceMapper, ArticleResource> implements IBannerService {
	@Autowired
	private IdGenerator idGenerator;
	@Autowired
	private FileFeignClient fileFeignClient;

	/**
	 * aap获取banner列表
	 *
	 * @param
	 * @return
	 */
	@Override
	public List<BannerVO> getBannerPage() {
		Page<ArticleResource> page = new Page();

		this.baseMapper.selectPage(page, new QueryWrapper<ArticleResource>()
				.lambda()
				.eq(ArticleResource::getBusinessCode, BusinessCode.ARTICLE_BANNER)
				.eq(ArticleResource::getStatus, ArticleResource.STATUS_NORMAL));

		List<BannerVO> vos = CollectionUtil.createArrayList();
		if (page.getTotal()>0){
			for (ArticleResource articleResource : page.getRecords()) {
				BannerVO vo = new BannerVO();
				BeanUtils.copyProperties(articleResource, vo);
				JsonResultVO jsonResultVO = fileFeignClient.listByResourceId(vo.getId().toString());
				JSONArray jsonArray = (JSONArray) jsonResultVO.getData();
				String[] fileNames = new String[jsonArray.size()];
				for (int i = 0 ; i < jsonArray.size() ; i++) {
					String fileName = ((JSONObject) jsonArray.get(i)).getString("fileName");
					fileNames[i] = fileName;
				}
				vo.setFileNames(fileNames);
				vos.add(vo);
			}
		}
		return vos;
	}

	/**
	 * 新增banner
	 *
	 * @param vo
	 * @return
	 */
	@Override
	public String addBanner(BannerVO vo) {
		ArticleResource articleResource = new ArticleResource();
		BeanUtils.copyProperties(vo, articleResource);
		articleResource.setId(idGenerator.getNumberId());
		articleResource.setStatus(ArticleResource.STATUS_NORMAL);
		articleResource.setBusinessCode(BusinessCode.ARTICLE_BANNER);
		Integer integer = this.baseMapper.insert(articleResource);
		if (integer == 1) {
			try {
				fileFeignClient.setByResourceId(articleResource.getId(), vo.getFileNames());
			} catch (Exception e) {
				log.error(ExceptionUtil.getMassage(e));
			}
			return "新增banner成功!";
		} else {
			log.error("新增banner失败：tb_article_resource  id = " + articleResource.getId());
			throw new ApplicationException(ErrorCode.SERVICE_ERROR, "新增资讯失败!");
		}
	}

	/**
	 * 获取banner集合
	 *
	 * @param query
	 * @return
	 */
	@Override
	public Page<BannerVO> getWebNewsPage(BannerQuery query) {
		Page<ArticleResource> page = new Page();
		page.setSize(query.getSize());
		page.setCurrent(query.getCurrent());

		LambdaQueryWrapper<ArticleResource> queryWrapper = new QueryWrapper<ArticleResource>()
				.lambda()
				.eq(ArticleResource::getBusinessCode, BusinessCode.ARTICLE_BANNER)
				.eq(ArticleResource::getStatus, ArticleResource.STATUS_NORMAL);

		// 查询条件
		if (query.getParams() != null && StringUtil.isNotBlank(query.getParams().getTitle())) {
			queryWrapper.like(ArticleResource::getTitle, query.getParams().getTitle());
		}

		//	排序
		queryWrapper.orderByDesc(ArticleResource::getCreateTime);

		this.baseMapper.selectPage(page, queryWrapper);

		List<BannerVO> vos = new ArrayList<>();
		if (page.getTotal()>0){
			for (ArticleResource articleResource : page.getRecords()) {
				BannerVO vo = new BannerVO();
				BeanUtils.copyProperties(articleResource, vo);
				vos.add(vo);
			}
		}
		return BeanPowerHelper.mapPage(page, vos);
	}

	/**
	 * 根据id删除banner
	 *
	 * @param ids
	 * @return
	 */
	@Override
	public String deleteBannerByIds(List<Long> ids) {
		Integer integer = this.baseMapper.deleteBatchIds(ids);
		if (integer == ids.size()) {
			return "删除成功!";
		} else {
			log.error("删除banner失败!ids=" + ids.toString());
			throw new ApplicationException(ErrorCode.SERVICE_ERROR, "删除banner失败!");
		}
	}

	/**
	 * 根据ID查询banner详情
	 *
	 * @param id
	 * @return
	 */
	@Override
	public BannerVO queryBannerById(Long id) {
		ArticleResource articleResource = this.baseMapper.selectById(id);
		if (articleResource == null) {
			log.error("未查询到相关数据 tb_article_resource bannerId=" + id);
			throw new ApplicationException(ErrorCode.SERVICE_ERROR, "未查询到相关数据!");
		} else {
			BannerVO vo = new BannerVO();
			BeanUtils.copyProperties(articleResource, vo);
			JsonResultVO jsonResultVO = fileFeignClient.getFileNamesById(vo.getId().toString());
			String[] fileNames = ((JSONArray) jsonResultVO.getData()).toArray(new String[0]);
			vo.setFileNames(fileNames);
			return vo;
		}
	}

	/**
	 * 更新banner
	 *
	 * @param bannerVO
	 * @return
	 */
	@Override
	public String updateBanner(BannerVO bannerVO) {
		ArticleResource articleResource = new ArticleResource();
		BeanUtils.copyProperties(bannerVO, articleResource);
		Integer integer = this.baseMapper.updateById(articleResource);
		if (integer == 1) {
			try {
				fileFeignClient.setByResourceId(articleResource.getId(), bannerVO.getFileNames());
			} catch (Exception e) {
				log.error(ExceptionUtil.getMassage(e));
			}
			return "更新成功!";
		} else {
			log.error("更新banner失败 tb_article_resource bannerId=" + bannerVO.getId());
			throw new ApplicationException(ErrorCode.SERVICE_ERROR, "更新banner失败!");
		}
	}
}
