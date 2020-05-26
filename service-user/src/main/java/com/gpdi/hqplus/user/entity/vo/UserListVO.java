package com.gpdi.hqplus.user.entity.vo;

import lombok.Data;

/**
 * @author: lianghb
 * @create: 2019-07-01 22:29
 **/
@Data
public class UserListVO {
    private Long id;
    private String userName;
    private String phone;
    private String email;
    private String status;
    private String userType;
    private String roleCode;
}
