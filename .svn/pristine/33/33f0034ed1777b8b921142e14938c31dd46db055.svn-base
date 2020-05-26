package com.gpdi.hqplus.article.controller.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.article.entity.query.NewsQuery;
import com.gpdi.hqplus.article.entity.vo.NewsDetailVO;
import com.gpdi.hqplus.article.entity.vo.NewsVO;
import com.gpdi.hqplus.article.service.INewsService;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.validate.ObjectValidate;
import com.gpdi.hqplus.common.validate.PageQueryValidate;
import com.gpdi.hqplus.common.validate.StringValidate;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 园区资讯
 *
 * @Author: hmx
 * @CreateDate: 2019/07/01 15:42
 */


@Api
@Slf4j
@RestController
@RequestMapping("/v1/web/resources/article/news")
public class NewsManagerController {
	@Autowired
	private INewsService newsService;

	/**
	 * 查询资讯列表
	 *
	 * @param: page
	 * @Result:
	 */
	@PostMapping("/list")
	@ApiOperation(value = "查询资讯列表", notes = "查询资讯列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "size", value = "每页显示数", required = true, dataType = "long", paramType = "path"),
			@ApiImplicitParam(name = "current", value = "当前页", required = true, dataType = "long", paramType = "path"),
			@ApiImplicitParam(name = "params.title", value = "标题", required = false, dataType = "string", paramType = "path")

	})
	public Page<NewsVO> getNewsPage(@RequestBody NewsQuery query) {
		PageQueryValidate.check(query);
		return newsService.getWebNewsPage(query);
	}

	/**
	 * 新增资讯
	 *
	 * @param newsVO
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation(value = "新增资讯", notes = "新增资讯")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "title", value = "资讯标题", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "description", value = "资讯概述", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "context", value = "资讯内容", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "fileNames", value = "资讯主图", required = true, dataType = "string", paramType = "path")
	})
	public String addNews(@RequestBody NewsDetailVO newsVO) {
		StringValidate.requireNotBlank(newsVO.getTitle(), "资讯标题不能为空");
		StringValidate.requireNotBlank(newsVO.getContext(), "资讯内容不能为空");
		StringValidate.requireNotBlank(newsVO.getDescription(), "资讯概述不能为空");
		ObjectValidate.requireNotNull(newsVO.getFileNames(), "资讯主图不能为空");
		return newsService.addNews(newsVO);
	}

	/**
	 * 根据ID删除资讯
	 *
	 * @param ids
	 * @return
	 */
	@DeleteMapping("/delete")
	@ApiOperation(value = "删除资讯", notes = "删除资讯")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ids", value = "资讯ID数组集", required = true, paramType = "ids", allowMultiple = true, dataType = "long"),
	})
	public String deleteNews(@RequestBody List<Long> ids) {
		ObjectValidate.requireNotNull(ids, "id不能为空");
		return newsService.deleteNewsByIds(ids);
	}

	/**
	 * 编辑资讯
	 *
	 * @param newsVO
	 * @return
	 */
	@PostMapping("/update")
	@ApiOperation(value = "编辑资讯", notes = "编辑资讯")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "title", value = "资讯标题", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "context", value = "资讯内容", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "description", value = "资讯概述", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "fileNames", value = "资讯主图", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "id", value = "资讯ID", required = true, dataType = "Long", paramType = "path")
	})
	public String updateNewsById(@RequestBody NewsDetailVO newsVO) {
		StringValidate.requireNotBlank(newsVO.getTitle(), "资讯标题不能为空");
		StringValidate.requireNotBlank(newsVO.getContext(), "资讯内容不能为空");
		ObjectValidate.requireNotNull(newsVO.getId(), "资讯ID不能为空");
		StringValidate.requireNotBlank(newsVO.getDescription(), "资讯概述不能为空");
		if (newsVO.getFileNames().length<1){
			throw new ApplicationException(ErrorCode.SERVICE_ERROR, "资讯主图不能为空!");
		}
		return newsService.updateNewsById(newsVO);
	}

	/**
	 * 根据id查询资讯
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "根据id查询资讯", notes = "根据id查询资讯")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "资讯ID", required = true, dataType = "Long", paramType = "path")
	})
	public NewsDetailVO getNewsDetailById(Long id) {
		ObjectValidate.requireNotNull(id, "id不能为空");
		return newsService.getNewsDetailById(id);
	}


}
