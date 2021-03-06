package com.gpdi.hqplus.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.manager.RedisService;
import com.gpdi.hqplus.common.util.BeanPowerHelper;
import com.gpdi.hqplus.common.util.CollectionUtil;
import com.gpdi.hqplus.common.util.IdGenerator;
import com.gpdi.hqplus.common.util.StringUtil;
import com.gpdi.hqplus.user.entity.Role;
import com.gpdi.hqplus.user.entity.query.RoleListQuery;
import com.gpdi.hqplus.user.entity.query.RoleQuery;
import com.gpdi.hqplus.user.entity.vo.RoleVO;
import com.gpdi.hqplus.user.entity.vo.RoleListVO;
import com.gpdi.hqplus.user.mapper.RoleMapper;
import com.gpdi.hqplus.user.service.IPermissionService;
import com.gpdi.hqplus.user.service.IRolePermRelService;
import com.gpdi.hqplus.user.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-01
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
	@Autowired
	private RedisService redisService;
	@Autowired
	private IRolePermRelService rolePermRelService;
	@Autowired
	private IPermissionService permissionService;

	@Autowired
	private IdGenerator idGenerator;

	private final String ADD_LOCK = "add_lock";


	@Override
	public Page<RoleListVO> listRole(RoleListQuery query) {
		Page<Role> page = new Page<>();
		page.setCurrent(query.getCurrent());
		page.setSize(query.getSize());

		LambdaQueryWrapper<Role> queryWrapper = new QueryWrapper<Role>()
				.lambda()
				.orderByDesc(Role::getId);

		// 多条件查询
		RoleListQuery.Params params = query.getParams();
		if (params != null) {
			if (StringUtil.isNotBlank(params.getName())) {
				queryWrapper.like(Role::getName, params.getName());
			}
			if (StringUtil.isNotBlank(params.getCode())) {
				queryWrapper.like(Role::getCode, params.getCode());
			}
		}

		baseMapper.selectPage(page, queryWrapper);

		List<RoleListVO> result = CollectionUtil.createArrayList(query.getSize());
		for (Role role : page.getRecords()) {
			RoleListVO vo = BeanPowerHelper.mapCompleteOverrider(role, RoleListVO.class);
			String[] permissionCode = rolePermRelService.listPermissionCode(role.getCode());
			if (permissionCode != null) {
				String[] permissionName = permissionService.getPermissionNameByCode(permissionCode);
				vo.setPermissionNames(permissionName);
			}
			result.add(vo);
		}

		return BeanPowerHelper.mapPage(page, result);
	}

	@Override
	public void add(RoleQuery query) {
		String lockKey = ADD_LOCK + query.getCode();
		boolean lock = redisService.getLock(lockKey, 60);
		if (!lock) {
			throw new ApplicationException(ErrorCode.SERVICE_ERROR, "网络忙，请稍后再试");
		}

		try {
			Integer count = baseMapper.selectCount(new QueryWrapper<Role>()
					.lambda()
					.eq(Role::getCode, query.getCode())
			);
			if (count != null && count > 0) {
				throw new ApplicationException(ErrorCode.RESOURCES_EXISTING, "角色代号已存在：" + query.getCode());
			}

			Role role = new Role()
					.setId(idGenerator.getNumberId())
					.setName(query.getName())
					.setCode(query.getCode())
					.setDescription(query.getDescription())
					.setStatus(Role.STATUS_NORMAL);

			boolean insert = role.insert();
			if (!insert) {
				throw new ApplicationException(ErrorCode.SERVICE_ERROR, "添加失败");
			}

			rolePermRelService.updateRolePermission(query);
		} finally {
			redisService.deleteLock(lockKey);
		}
	}

	@Override
	public void update(RoleQuery query) {
		Role role = baseMapper.selectById(query.getId());
		if (role == null) {
			throw new ApplicationException(ErrorCode.RESOURCES_NOT_FIND, "查询信息失败");
		}

		if (StringUtil.isNotBlank(query.getName())) {
			role.setName(query.getName());
		}
		if (StringUtil.isNotBlank(query.getDescription())) {
			role.setDescription(query.getDescription());
		}
		boolean update = role.updateById();
		if (!update) {
			log.error("update role fail.");
			throw new ApplicationException(ErrorCode.SERVICE_ERROR, "更新失败");
		}

		rolePermRelService.updateRolePermission(query);
	}

	@Override
	public void delete(Long roleId) {
		Role role = baseMapper.selectById(roleId);
		if (role == null) {
			throw new ApplicationException(ErrorCode.RESOURCES_NOT_FIND, "查询信息失败");
		}
		boolean delete = role.deleteById();
		if (!delete) {
			log.error("delete role fail.");
			throw new ApplicationException(ErrorCode.SERVICE_ERROR, "删除失败");
		}
		rolePermRelService.deleteByRoleCode(role.getCode());
	}

	@Override
	public String deleteBatchIds(List<Long> ids) {
		Integer flag = this.baseMapper.deleteBatchIds(ids);
		if (flag == ids.size()) {
			return "ok";
		} else {
			throw new ApplicationException(ErrorCode.DATABASE_ERROR, "删除角色失败!ids=" + ids.toString());
		}
	}

	@Override
	public RoleVO getDetailById(Long id) {
		RoleVO vo = new RoleVO();
		Role role = this.baseMapper.selectById(id);
		if (role != null && role.getCode() != null) {
			BeanUtils.copyProperties(role, vo);
			String[] permissionCode = rolePermRelService.listPermissionCode(role.getCode());
			vo.setPermissionCode(permissionCode);
			return vo;
		} else {
			throw new ApplicationException(ErrorCode.RESOURCES_NOT_FIND, "为查询到相关角色!id=" + id);
		}
	}

	@Override
	public List<RoleVO> getRolesByRoleType(String userType) {
		LambdaQueryWrapper<Role> queryWrapper = new QueryWrapper<Role>()
				.lambda()
				.eq(Role::getStatus, Role.STATUS_NORMAL);
		if (userType != null) {
			queryWrapper.eq(Role::getRoleType, userType);
		}
		List<Role> roles = baseMapper.selectList(queryWrapper);
		List<RoleVO> vos = new ArrayList<>();
		if (roles.size() > 0) {
			for (Role role : roles) {
				RoleVO vo = new RoleVO();
				BeanUtils.copyProperties(role, vo);
				vos.add(vo);
			}
		}
		return vos;
	}
}
