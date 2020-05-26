package com.gpdi.hqplus.user.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: lianghb
 * @create: 2019-07-01 23:45
 **/
@Data
public class RoleListVO {
	private Long id;
	private String description;
	private String name;
	/*
	* 角色权限 集合
	* */

	private String[] permissionNames ;
	private LocalDateTime createTime;

}
