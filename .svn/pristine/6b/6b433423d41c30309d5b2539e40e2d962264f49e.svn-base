package com.gpdi.hqplus.common.entity;

/**
 * 当前线程用户信息
 *
 * @author: lianghb
 * @create: 2019-06-10 22:18
 **/
public class UserContext {
    private UserContext() {
    }

    private static ThreadLocal<UserInfo> local = new ThreadLocal<>();

    public static void set(UserInfo userInfo) {
        local.set(userInfo);
    }

    public static UserInfo get() {
        return local.get();
    }

    public static void remove() {
        local.remove();
    }
}
