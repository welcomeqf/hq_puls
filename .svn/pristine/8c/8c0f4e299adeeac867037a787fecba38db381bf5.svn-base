package com.gpdi.hqplus.authentication.service;

import com.gpdi.hqplus.authentication.entity.RolePermRel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 * 角色权限关联表 服务类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-01
 */
public interface IRolePermRelService extends IService<RolePermRel> {
    /**
     * 根据用户角色列表获取权限
     * @param roleList
     * @return
     */
    Set<String> listPermissionCodeByRoleList(Set<String> roleList);
}
