package com.gpdi.hqplus.authentication.entity.vo;

import lombok.Data;

/**
 * @author: lianghb
 * @create: 2019-06-10 23:11
 **/
@Data
public class LoginInfoVO {
    private Long id;
    private String userName;
    private String phone;
    private String imgSrc;
    private String token;
}
