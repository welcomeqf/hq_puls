package com.gpdi.hqplus.user.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserExtendVO {

    private Long id;
    private Long userId;
    private String address;
    private String userName;
    private String sex;
    private String imgSrc;
    private String userType;
    private String defaultProjectCode;
    private String status;
    private String projectCode;
    private String extendVal;
    private String resultMsg;
    private LocalDateTime createTime;

}
