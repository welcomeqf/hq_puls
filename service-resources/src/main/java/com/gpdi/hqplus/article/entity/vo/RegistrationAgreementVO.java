package com.gpdi.hqplus.article.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: hmx
 * @CreateDate: 2019/07/17 14:35
 */
@Data
public class RegistrationAgreementVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 内容
	 */
	private String context;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;

	/**
	 * 描述
	 */
	private String description;

}
