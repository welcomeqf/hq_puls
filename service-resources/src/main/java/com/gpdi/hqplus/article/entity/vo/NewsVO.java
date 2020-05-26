package com.gpdi.hqplus.article.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: hmx
 * @CreateDate: 2019/07/01 18:12
 */
@Data
public class NewsVO implements Serializable {
	private Long id;

	/*
	 * 资讯标题
	 * */

	private String title;

	/*
	 * 资讯主图
	 * */

	private String linkUrl;

	/*
	 * 发布时间
	 * */

	private LocalDateTime createTime;

	/**
	 * 描述
	 */
	private String description;

}
