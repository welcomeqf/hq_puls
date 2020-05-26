package com.gpdi.hqplus.article.controller.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.article.entity.query.NoticeQuery;
import com.gpdi.hqplus.article.entity.vo.NoticeVO;
import com.gpdi.hqplus.article.service.INoticeService;
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
 * 园区公告
 * @Author: hmx
 * @CreateDate: 2019/07/01 19:55
 */
@Api
@Slf4j
@RestController
@RequestMapping("/v1/web/resources/article/notice")
public class NoticeManagerController {
	@Autowired
	private INoticeService noticeService;

	/**
	 * 查询园区公告列表
	 *
	 * @param: query
	 * @Result:
	 */

	@PostMapping("/list")
	@ApiOperation(value = "查询园区公告列表", notes = "查询园区公告列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "size", value = "每页显示数", required = true, dataType = "long", paramType = "path"),
			@ApiImplicitParam(name = "current", value = "当前页", required = true, dataType = "long", paramType = "path"),
			@ApiImplicitParam(name = "params.title", value = "公告标题", required = false, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "params.id", value = "公告ID", required = false, dataType = "Long", paramType = "path")

	})
	public Page<NoticeVO> getWebNoticePage(@RequestBody NoticeQuery query) {
		PageQueryValidate.check(query);
		return noticeService.getWebNoticePage(query);
	}


	/**
	 * @Description: 查询园区公告详情
	 * @param: id
	 * @Resul: articleResource
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "查询园区公告详情", notes = "查询园区公告详情")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "园区公告id", required = true, dataType = "long", paramType = "path")
	})
	public NoticeVO getNoticeDetail(Long id) {
		ObjectValidate.requireNotNull(id, "园区公告id不能为空");
		return noticeService.getNoticeDetailById(id);
	}

	/**
	 * 新增园区公告
	 * @param vo
	 * @return
	 */
	@PostMapping("/add")
	@ApiOperation(value = "新增公告", notes = "新增公告")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "title", value = "公告标题", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "description", value = "公告概述", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "context", value = "公告内容", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "fileNames", value = "公告主图", required = true, dataType = "string", paramType = "path")

	})
	public String addNotice(@RequestBody NoticeVO vo){
		StringValidate.requireNotBlank(vo.getTitle(), "公告标题不能为空");
		StringValidate.requireNotBlank(vo.getContext(), "公告内容不能为空");
		ObjectValidate.requireNotNull(vo.getFileNames(), "公告主图不能为空");
		StringValidate.requireNotBlank(vo.getDescription(), "概述不能为空");
		return noticeService.addNotice(vo);
	}

	/**
	 * 删除园区公告
	 * @param ids
	 * @return
	 */
	@DeleteMapping("/delete")
	@ApiOperation(value = "删除园区公告", notes = "删除园区公告")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ids", value = "id数组", required = true, dataType = "long", paramType = "path")
	})
	public String deleteNoticeByIds(@RequestBody List<Long> ids){
		ObjectValidate.requireNotNull(ids, "id不能为空");
		return noticeService.deleteNoticeByIds(ids);
	}

	/**
	 * 编辑公告
	 * @param noticeVO
	 * @return
	 */
	@PostMapping("/update")
	@ApiOperation(value = "编辑公告", notes = "编辑公告")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "title", value = "公告标题", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "context", value = "公告内容", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "fileNames", value = "公告主图", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "description", value = "公告概述", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "id", value = "公告ID", required = true, dataType = "Long", paramType = "path")

	})
	public String updateNotice(@RequestBody NoticeVO noticeVO){
		StringValidate.requireNotBlank(noticeVO.getTitle(), "公告标题不能为空");
		StringValidate.requireNotBlank(noticeVO.getContext(), "公告内容不能为空");
		ObjectValidate.requireNotNull(noticeVO.getFileNames(), "公告主图不能为空");
		ObjectValidate.requireNotNull(noticeVO.getId(), "公告ID不能为空");
		StringValidate.requireNotBlank(noticeVO.getDescription(), "概述不能为空");
		return noticeService.updateNotice(noticeVO);
	}
}
