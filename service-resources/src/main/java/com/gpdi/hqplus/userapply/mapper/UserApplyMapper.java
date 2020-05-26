package com.gpdi.hqplus.userapply.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gpdi.hqplus.common.util.PageUtil;
import com.gpdi.hqplus.userapply.entity.UserApply;
import com.gpdi.hqplus.userapply.entity.UserApplyVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户认证 Mapper 接口
 * </p>
 *
 * @author hmx
 * @since 2019-07-02
 */
@Repository
public interface UserApplyMapper extends BaseMapper<UserApply> {

	/**
	 * 查询用户认证分页信息
	 * @param page
	 * @return
	 */
	PageUtil<UserApplyVO> getUserApplyList(PageUtil<UserApplyVO> page);

	/**
	 * 用户认证审核
	 * @param ids
	 * @return
	 */
	Integer auditApply(List<Long> ids);

	/**
	 * 获取用户申请详情
	 * @param id
	 * @return
	 */
	UserApplyVO getUserApplyDetailById(Long id);
}
