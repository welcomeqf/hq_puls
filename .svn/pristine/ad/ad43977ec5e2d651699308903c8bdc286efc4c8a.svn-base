package com.gpdi.hqplus.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.common.util.PageUtil;
import com.gpdi.hqplus.user.entity.User;
import com.gpdi.hqplus.user.entity.vo.UserBusinessVO;
import com.gpdi.hqplus.user.entity.vo.UserExtendVO;
import com.gpdi.hqplus.user.entity.vo.UserListVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author lianghb
 * @since 2019-07-01
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

	/**
	 * 分页获取个体用户列表
	 * @param page
	 * @return
	 */
	PageUtil<UserListVO> listNormal(PageUtil<UserListVO> page);

	/**
	 * 根据角色 获取对应角色下的用户
	 *
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
	 *  根据ID 获取用户详情
	 * @param id
	 * @return
	 */
	UserBusinessVO getUserBusinessVOById(Long id);
}
