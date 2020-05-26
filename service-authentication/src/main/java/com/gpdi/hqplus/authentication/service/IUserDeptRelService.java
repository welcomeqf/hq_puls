package com.gpdi.hqplus.authentication.service;

import com.gpdi.hqplus.authentication.entity.UserDeptRel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 * 部门用户关联表 服务类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-01
 */
public interface IUserDeptRelService extends IService<UserDeptRel> {
    /**
     * 根据用户用户 id 获取用户组
     * @param userId
     * @return
     */
    Set<String> listDeptByUserId(Long userId);
}
