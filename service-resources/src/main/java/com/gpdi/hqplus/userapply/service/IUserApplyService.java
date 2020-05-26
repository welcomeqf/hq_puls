package com.gpdi.hqplus.userapply.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gpdi.hqplus.common.util.PageUtil;
import com.gpdi.hqplus.userapply.entity.UserApply;
import com.gpdi.hqplus.userapply.entity.UserApplyVO;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author hmx
 * @since 2019-07-02
 */
public interface IUserApplyService extends IService<UserApply> {


	/**
	 * 用户认证
	 * @param userApplyVO
	 * @return
	 */
	String userApply(UserApplyVO userApplyVO);

	/**
	 * 获取用户认证列表
	 * @param page
	 * @return
	 */
	PageUtil<UserApplyVO> getUserApplyList(PageUtil<UserApplyVO> page);
	/**
	 * 用户认证审核
	 * 管理端审核，根据id修改 UserApply状态， 审核通过:由 apply 状态变为 normal状态
	 * @param ids
	 * @return
	 */
	String auditApply(List<Long> ids);
	/**
	 * 用户认证审核
	 * 获取用户认证详情
	 * @param id
	 * @return
	 */
	UserApplyVO getUserApplyDetailById(Long id);

	/**
	 * 用户认证审核
	 * 管理端审核，根据id修改 UserApply状态， 审核通过:由 apply 状态变为 normal状态
	 * @param userApplyVO
	 * @return
	 */
	String checkApply(UserApplyVO userApplyVO);
}
