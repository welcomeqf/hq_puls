package com.gpdi.hqplus.user.entity.query;

import lombok.Data;

/**
 * @author: lianghb
 * @create: 2019-06-10 23:44
 **/
@Data
public class LoginQuery {
    private String phone;
    private String password;
    private String deviceId;
    private String deviceToken;
}
