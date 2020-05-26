package com.gpdi.hqplus.article.controller.app;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.article.entity.query.NoticeQuery;
import com.gpdi.hqplus.article.entity.vo.NoticeVO;
import com.gpdi.hqplus.article.service.INoticeService;
import com.gpdi.hqplus.common.validate.PageQueryValidate;
import com.gpdi.hqplus.common.validate.StringValidate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 园区公告
 * @Author: hmx
 * @CreateDate: 2019/07/01 19:55
 */
@Api
@Slf4j
@RestController
@RequestMapping("/v1/app/resources/article/notice")
public class NoticeController {
	@Autowired
	private INoticeService noticeService;

	/**
	 * 查询园区公告列表
	 *
	 * @param query
	 * @Result
	 */

	@PostMapping("/list")
	@ApiOperation(value = "查询园区公告列表", notes = "查询园区公告列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "size", value = "每页显示数", required = true, dataType = "long", paramType = "path"),
			@ApiImplicitParam(name = "current", value = "当前页", required = true, dataType = "long", paramType = "path")
	})
	public Page<NoticeVO> getNoticePage(@RequestBody NoticeQuery query) {
		PageQueryValidate.check(query);
		return noticeService.getNoticePage(query);
	}


	/**
	 * @Description 查询园区公告详情
	 * @param id
	 * @Result articleResource
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "查询园区公告列表", notes = "查询园区公告列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "园区公告id", required = true, dataType = "long", paramType = "path")
	})
	public NoticeVO getNoticeDetailById(Long id) {
		StringValidate.requireNotBlank(String.valueOf(id), "园区公告id不能为空");
		return noticeService.getNoticeDetailById(id);
	}
}
