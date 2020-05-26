package com.gpdi.hqplus.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gpdi.hqplus.user.entity.RolePermRel;
import com.gpdi.hqplus.user.entity.query.RoleQuery;

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

    /**
     * 更新角色权限
     * @param query
     */
    void updateRolePermission(RoleQuery query);

    /**
     * 删除
     * @param code
     */
    void deleteByRoleCode(String code);

    /**
     * 根据 roleCode 获取对应角色与之对应的所有权限 code
     * @param roleCode
     * @return
     */
    String[] listPermissionCode(String roleCode);

}
