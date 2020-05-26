package com.gpdi.hqplus.user.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: hmx
 * @CreateDate: 2019/07/21 18:20
 */
@Data
public class UserBusinessVO {
	private Long id;
	private String userName;
	private String phone;
	private String status;
	private String roleName;
	private String deptName;
	private LocalDateTime createTime;

}
