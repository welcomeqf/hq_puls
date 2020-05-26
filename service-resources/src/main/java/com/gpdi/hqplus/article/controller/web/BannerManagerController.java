package com.gpdi.hqplus.article.controller.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.article.entity.query.BannerQuery;
import com.gpdi.hqplus.article.entity.vo.BannerVO;
import com.gpdi.hqplus.article.service.IBannerService;
import com.gpdi.hqplus.common.validate.ObjectValidate;
import com.gpdi.hqplus.common.validate.PageQueryValidate;
import com.gpdi.hqplus.common.validate.StringValidate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * banner
 * @Author: hmx
 * @CreateDate: 2019/07/01 19:55
 */
@Api
@Slf4j
@RestController
@RequestMapping("/v1/web/resources/article/banner")
public class BannerManagerController {
	@Autowired
	private IBannerService bannerService;

	/**
	 * 查询banner列表
	 *
	 * @param: page
	 * @Result:
	 */
	@PostMapping("/list")
	@ApiOperation(value = "查询banner列表", notes = "查询banner列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "size", value = "每页显示数", required = true, dataType = "long", paramType = "path"),
			@ApiImplicitParam(name = "current", value = "当前页", required = true, dataType = "long", paramType = "path"),
			@ApiImplicitParam(name = "params.title", value = "参数", required = false, dataType = "string", paramType = "path")
	})
	public Page<BannerVO> getWebNewsPage(@RequestBody BannerQuery query) {
		PageQueryValidate.check(query);
		return bannerService.getWebNewsPage(query);
	}

	/**
	 * 新增banner
	 * @param vo
	 * @return
	 */
	@PostMapping("/add")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "title", value = "标题", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "context", value = "内容", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "description", value = "概述", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "fileNames", value = "图片", required = false, dataType = "string", paramType = "path")
	})
	public String addBanner(@RequestBody BannerVO vo){
		StringValidate.requireNotBlank(vo.getTitle(), "标题不能为空");
		ObjectValidate.requireNotNull(vo.getFileNames(),"banner主图不能为空");
		StringValidate.requireNotBlank(vo.getDescription(), "概述不能为空");
		return bannerService.addBanner(vo);
	}

	/**
	 * 根据id删除banner
	 * @param ids
	 * @return
	 */
	@DeleteMapping("/delete")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ids", value = "id数组", required = true, dataType = "long", paramType = "path")
	})
	public String deleteBannerByIds(@RequestBody List<Long> ids){
		ObjectValidate.requireNotNull(ids, "id不能为空");
		return bannerService.deleteBannerByIds(ids);
	}

	/**
	 * 根据ID查询banner详情
	 * @param id
	 * @return
	 */
	@GetMapping("/detail")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "id", required = true, dataType = "long", paramType = "path")
	})
	public BannerVO queryBannerById(Long id){
		ObjectValidate.requireNotNull(id, "id不能为空");
		return bannerService.queryBannerById(id);
	}

	/**
	 * 更新banner
	 * @param bannerVO
	 * @return
	 */
	@PostMapping("/update")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "title", value = "banner标题", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "context", value = "banner内容", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "description", value = "概述", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "fileNames", value = "banner主图", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "id", value = "bannerID", required = true, dataType = "Long", paramType = "path")
	})
	public String updateBanner(@RequestBody BannerVO bannerVO){
		StringValidate.requireNotBlank(bannerVO.getTitle(), "banner标题不能为空");
		StringValidate.requireNotBlank(bannerVO.getContext(),"banner内容不能为空");
		ObjectValidate.requireNotNull(bannerVO.getFileNames(),"banner主图不能为空");
		StringValidate.requireNotBlank(bannerVO.getDescription(),"banner概述不能为空");
		ObjectValidate.requireNotNull(bannerVO.getId(), "id不能为空");
		return bannerService.updateBanner(bannerVO);
	}
}
