package com.gpdi.hqplus.user.entity.vo;

import lombok.Data;

/**
 *
 * @author: lianghb
 * @create: 2019-07-02 11:06
 **/
@Data
public class RoleVO {
    private Long id;
    private String name;
    private String code;
    private String description;
    private String[] permissionCode;
}
