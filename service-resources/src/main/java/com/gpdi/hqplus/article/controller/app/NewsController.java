package com.gpdi.hqplus.article.controller.app;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.article.entity.query.NewsQuery;
import com.gpdi.hqplus.article.entity.vo.NewsDetailVO;
import com.gpdi.hqplus.article.entity.vo.NewsVO;
import com.gpdi.hqplus.article.service.INewsService;
import com.gpdi.hqplus.common.validate.ObjectValidate;
import com.gpdi.hqplus.common.validate.PageQueryValidate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 园区资讯
 *
 * @Author: hmx
 * @CreateDate: 2019/07/01 15:42
 */


@Api
@Slf4j
@RestController
@RequestMapping("/v1/app/resources/article/news")
public class NewsController {
	@Autowired
	private INewsService newsService;


	/**
	 * 查询资讯列表
	 *
	 * @param query
	 * @Result
	 */
	@PostMapping("/list")
	@ApiOperation(value = "查询资讯列表", notes = "查询资讯列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "size", value = "每页显示数", required = true, dataType = "long", paramType = "path"),
			@ApiImplicitParam(name = "current", value = "当前页", required = true, dataType = "long", paramType = "path")
	})
	public Page<NewsVO> getNewsPage(@RequestBody NewsQuery query) {
		PageQueryValidate.check(query);
		return newsService.getNewsPage(query);
	}

	/**
	 * 查询资讯详情
	 * @param id
	 * @return
	 */
	@GetMapping
	@RequestMapping("/detail")
	@ApiOperation(value = "查询资讯详情", notes = "查询资讯详情")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "资讯id", required = true, dataType = "long", paramType = "path")
	})
	public NewsDetailVO getNewsById(Long id){
		ObjectValidate.requireNotNull(id,"id不能为空");
		return newsService.getNewsDetailById(id);
	}
}
