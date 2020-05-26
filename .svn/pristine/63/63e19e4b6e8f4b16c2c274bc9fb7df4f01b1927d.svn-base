package com.gpdi.hqplus.authentication.service;

import com.gpdi.hqplus.authentication.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gpdi.hqplus.authentication.entity.query.LoginQuery;
import com.gpdi.hqplus.authentication.entity.vo.LoginInfoVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-01
 */
public interface IUserService extends IService<User> {
    /**
     * 登录
     * @param query
     * @return
     */
    LoginInfoVO login(LoginQuery query);

    /**
     * 登出
     */
    void logout();
}
