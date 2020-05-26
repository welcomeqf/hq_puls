package com.gpdi.hqplus.userapply.entity;

import lombok.Data;

import java.time.LocalDateTime;


/**
 * APP端 vo
 * @Author: hmx
 * @CreateDate: 2019/07/02 10:07
 */
@Data
public class UserApplyVO {

	/**
	 * id
	 */

	private Long id;

	/**
	 * 用户类型
	 */

	private String userType;


	/**
	 * 电话
	 */

	private String phone;


	/**
	 * 姓名
	 */

	private String name;

	/*
	* 相关证明文件图片组件
	* */

	private String unitNature;

	/*
	* 电子邮箱
	* */

	private String email;

	/*
	* 相关证明文件图片
	* */

	private String[] fileNames;

	/**
	 * 物业单位
	 */
	private String propertyUnit;

	/**
	 * 职务
	 */
	private String job;


	/**
	 * 状态
	 */
	private String status;

	/**
	 * 创建时间
	 */

	private LocalDateTime createTime;
	/*
	 * 更新时间
	 */

	private LocalDateTime updateTime;
}
