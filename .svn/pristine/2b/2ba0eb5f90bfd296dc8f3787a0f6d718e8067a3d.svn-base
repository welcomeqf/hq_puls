package com.gpdi.hqplus.authentication.service;

import com.gpdi.hqplus.authentication.entity.UserRoleRel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-01
 */
public interface IUserRoleRelService extends IService<UserRoleRel> {
    /**
     * 根据用户 id 获取用户角色
     * @param userId
     * @return
     */
    Set<String> listRoleCodeByUserId(Long userId);
}
