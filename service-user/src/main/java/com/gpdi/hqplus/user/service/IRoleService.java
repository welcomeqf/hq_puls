package com.gpdi.hqplus.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gpdi.hqplus.user.entity.Role;
import com.gpdi.hqplus.user.entity.query.RoleListQuery;
import com.gpdi.hqplus.user.entity.query.RoleQuery;
import com.gpdi.hqplus.user.entity.vo.RoleVO;
import com.gpdi.hqplus.user.entity.vo.RoleListVO;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-01
 */
public interface IRoleService extends IService<Role> {
    /**
     * 获取列表
     * @param query
     * @return
     */
    Page<RoleListVO> listRole(RoleListQuery query);

    /**
     * 新增
     * @param query
     */
    void add(RoleQuery query);

    /**
     * 修改
     * @param query
     */
    void update(RoleQuery query);

    /**
     * 删除
     * @param roleId
     */
    void delete(Long roleId);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    String deleteBatchIds(List<Long> ids);

    /**
     * 获取角色详情信息
     * @param id
     * @return
     */
    RoleVO getDetailById(Long id);

    /**
     * 根据角色类型 获取角色集合
     * @param userType
     * @return
     */
    List<RoleVO>  getRolesByRoleType(String userType);
}
