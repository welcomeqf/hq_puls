package com.gpdi.hqplus.common.manager;

import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.entity.UserContext;
import com.gpdi.hqplus.common.entity.UserInfo;
import com.gpdi.hqplus.common.exception.ApplicationException;

import java.util.Set;

/**
 * 权限验证类
 *
 * @author: lianghb
 * @create: 2019-06-11 15:47
 **/
public class AuthenticationManager {
    private AuthenticationManager(){}
    /**
     * 需要登录
     */
    public static void requireAuthentication() {
        UserInfo userInfo = UserContext.get();
        if (userInfo == null) {
            throw new ApplicationException(ErrorCode.AUTHENTICATION_ERROR, "未登录");
        }
    }

    /**
     * 需要角色，默认需要登录
     * 多个角色之间为 或 关系，即用户只要拥有其中一个角色即可
     *
     * @param roles
     */
    public static void requireRole(String... roles) {
        requireAuthentication();

        Set<String> roleSet = UserContext.get().getRoleSet();
        for (String role : roles) {
            if (roleSet.contains(role)) {
                return;
            }
        }
        throw new ApplicationException(ErrorCode.AUTHENTICATION_ERROR, "权限不足");
    }

    /**
     * 需要权限，默认需要登录
     * 多个权限之间为 或 关系，即用户只要拥有其中一个权限即可
     *
     * @param roles
     */
    public static void requirePermission(String... roles) {
        requireAuthentication();

        Set<String> permissionSet = UserContext.get().getPermissionSet();
        for (String role : roles) {
            if (permissionSet.contains(role)) {
                return;
            }
        }
        throw new ApplicationException(ErrorCode.AUTHENTICATION_ERROR, "权限不足");
    }
}
