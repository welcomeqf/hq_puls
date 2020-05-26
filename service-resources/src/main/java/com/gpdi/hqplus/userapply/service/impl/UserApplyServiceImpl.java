package com.gpdi.hqplus.userapply.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.entity.UserContext;
import com.gpdi.hqplus.common.entity.vo.JsonResultVO;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.ExceptionUtil;
import com.gpdi.hqplus.common.util.IdGenerator;
import com.gpdi.hqplus.common.util.PageUtil;
import com.gpdi.hqplus.resources.feign.FileFeignClient;
import com.gpdi.hqplus.userapply.entity.UserApply;
import com.gpdi.hqplus.userapply.entity.UserApplyVO;
import com.gpdi.hqplus.userapply.mapper.UserApplyMapper;
import com.gpdi.hqplus.userapply.service.IUserApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author hmx
 * @since 2019-07-02
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserApplyServiceImpl extends ServiceImpl<UserApplyMapper, UserApply> implements IUserApplyService {
	@Autowired
	private IdGenerator idGenerator;
	@Autowired
	private UserApplyMapper userApplyMapper;
	@Autowired
	private FileFeignClient fileFeignClient;

	/**
	 * 用户认证接口
	 *
	 * @param userApplyVO
	 * @return
	 */
	@Override
	public String userApply(UserApplyVO userApplyVO) {
		UserApply userApply = new UserApply();
		BeanUtils.copyProperties(userApplyVO, userApply);
		userApply.setId(idGenerator.getNumberId());
		userApply.setStatus(UserApply.STATUS_APPLY);
		userApply.setUserId(UserContext.get().getId());
		Integer integer = this.baseMapper.insert(userApply);
		if (integer == 1) {
			// 调用 file 微服务设置文件对应
			try {
				fileFeignClient.setByResourceId(userApplyVO.getId(), userApplyVO.getFileNames());
			} catch (Exception e) {
				log.error(ExceptionUtil.getMassage(e));
			}
			return "请耐心等待,您的申请我们会尽快处理!";
		} else {
			log.error("新增用户认证信息失败：tb_user_apply  id = " + userApply.getId());
			throw new ApplicationException(ErrorCode.SERVICE_ERROR, "用户认证申请失败!");
		}
	}

	/**
	 * 获取用户认证列表
	 *
	 * @param page
	 * @return
	 */
	@Override
	public PageUtil<UserApplyVO> getUserApplyList(PageUtil<UserApplyVO> page) {
		return userApplyMapper.getUserApplyList(page);
	}

	/**
	 * 用户认证审核
	 * @param ids
	 * @return
	 */
	@Override
	public String auditApply(List<Long> ids) {
		Integer integer = userApplyMapper.auditApply(ids);
		if ( ids.size() == integer){
			return "ok";
		}else {
			log.error("用户认证审核：tb_user_apply  ids = " + ids.toString());
			throw new ApplicationException(ErrorCode.SERVICE_ERROR, "用户认证审核!");
		}
	}
	/**
	 * 用户认证审核
	 * 获取用户认证详情
	 * @param id
	 * @return
	 */
	@Override
	public UserApplyVO getUserApplyDetailById(Long id) {
		UserApplyVO userApply = userApplyMapper.getUserApplyDetailById(id);
		//	图片
		JsonResultVO jsonResultVO = fileFeignClient.getFileNamesById(id.toString());
		String[] fileNames = ((JSONArray) jsonResultVO.getData()).toArray(new String[0]);
		userApply.setFileNames(fileNames);
		return userApply ;
	}

	/**
	 * 用户认证审核
	 * 管理端审核，根据id修改 UserApply状态， 审核通过:由 apply 状态变为 normal状态
	 * @param userApplyVO
	 * @return
	 */
	@Override
	public String checkApply(UserApplyVO userApplyVO) {
		UserApply userApply = new UserApply();
		userApply.setId(userApplyVO.getId());
		userApply.setStatus(userApplyVO.getStatus());
		boolean flag = userApply.updateById();
		if (flag){
			return "OK";
		}else {
			log.error("用户审核失败：tb_user_apply  id = "
					+ userApplyVO.getId().toString()
					+";status="+userApplyVO.getStatus());
			throw new ApplicationException(ErrorCode.SERVICE_ERROR, "用户审核失败!");
		}
	}
}
