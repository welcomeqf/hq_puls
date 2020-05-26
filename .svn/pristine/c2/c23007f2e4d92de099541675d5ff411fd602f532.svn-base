package com.gpdi.hqplus.common.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: lianghb
 * @create: 2019-06-10 22:19
 **/
@Data
public class UserInfo implements Serializable {
    /**
     * 多租户：园区代码
     */
    private String projectCode;
    /**
     * 用户 id
     */
    private Long id;
    /**
     * 用户账号
     */
    private String account;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户类型
     */
    private String userType;
    /**
     * 用户状态
     */
    private String status;
    /**
     * 当前请求 ip 地址
     */
    private String ipAddress;
    /**
     * token
     */
    private String token;
    /**
     * redisKey
     */
    private String redisKey;
    /**
     * 用户角色列表
     */
    private Set<String> roleSet = new HashSet<>();
    /**
     * 用户权限列表
     */
    private Set<String> permissionSet = new HashSet<>();
    /**
     * 用户组列表
     */
    private Set<String> groupSet = new HashSet<>();
    /**
     * 用户权限列表
     */
    private Collection<? extends GrantedAuthority> authorities;
}
