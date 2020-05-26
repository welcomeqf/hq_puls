package com.gpdi.hqplus.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gpdi.hqplus.common.util.PageUtil;
import com.gpdi.hqplus.user.entity.User;
import com.gpdi.hqplus.user.entity.query.UserListQuery;
import com.gpdi.hqplus.user.entity.query.UserQuery;
import com.gpdi.hqplus.user.entity.vo.UserBusinessVO;
import com.gpdi.hqplus.user.entity.vo.UserExtendVO;
import com.gpdi.hqplus.user.entity.vo.UserListVO;

import java.util.List;
import com.gpdi.hqplus.user.entity.query.RegisterQuery;
import com.gpdi.hqplus.user.entity.vo.RegisterInfoVO;

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
     * 获取用户列表
     * @param query
     * @return
     */
    Page<UserListVO> listUser(UserListQuery query);

    /**
     * 注册
     * @param query
     * @return
     */
    void register(RegisterQuery query);

    /**
     * 新增业务用户
     * @param query
     */
    void addBusinessUser(RegisterQuery query);

    /**
     * 更新用户信息
     * @param query
     */
    void updateUser(RegisterQuery query);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    UserQuery queryUserById(Long id);

    /**
     * 分页获取个体用户列表
     * @param page
     * @return
     */
    PageUtil<UserListVO> listNormal(PageUtil<UserListVO> page);

    /**
     * 根据角色 获取对应角色下的用户
     * @param roleCode
     * @return
     */
	List<UserExtendVO> getUserListByRoleCode(String roleCode);

    /**
     * 分页获取运营用户列表
     * @param page
     * @return
     */
    Page<UserBusinessVO> listBusiness(PageUtil<UserBusinessVO> page);

    /**
     * 获取运营用户
     * @param id
     * @return
     */
    UserBusinessVO getUserBusinessVOById(Long id);
}
