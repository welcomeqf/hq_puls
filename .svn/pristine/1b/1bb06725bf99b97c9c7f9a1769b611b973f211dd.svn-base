package com.gpdi.hqplus.article.controller.app;

import com.gpdi.hqplus.article.entity.vo.BannerVO;
import com.gpdi.hqplus.article.service.IBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * banner
 * @Author: hmx
 * @CreateDate: 2019/07/01 19:55
 */
@Api
@Slf4j
@RestController
@RequestMapping("/v1/app/resources/article/banner")
public class BannerController {



	@Autowired
	private IBannerService bannerService;

	/**
	 * 查询banner列表
	 *
	 * @param
	 * @Result
	 */
	@GetMapping("/list")
	@ApiOperation(value = "查询banner列表", notes = "查询banner列表")
	@ApiImplicitParams({
	})
	public List<BannerVO> getNewsPage() {
		return bannerService.getBannerPage();
	}
}
