package com.gpdi.hqplus.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gpdi.hqplus.user.entity.Permission;
import com.gpdi.hqplus.user.entity.query.PermissionListQuery;
import com.gpdi.hqplus.user.entity.vo.PermissionListVO;

import java.util.List;

/**
 * <p>
 * 用户权限表 服务类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-01
 */
public interface IPermissionService extends IService<Permission> {
    /**
     * 获取权限列表
     *
     * @param query
     * @return
     */
    Page<PermissionListVO> listPermission(PermissionListQuery query);

	/**
	 * 获取权限集合
	 * @return
	 */

	List<PermissionListVO> getPermissionList();

	/**
	 * 获取权限code 获取 权限名称
	 * @param permissionCode
	 * @return
	 */
	String[] getPermissionNameByCode(String[] permissionCode);
}
