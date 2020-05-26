package com.gpdi.hqplus.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.CollectionUtil;
import com.gpdi.hqplus.common.util.IdGenerator;
import com.gpdi.hqplus.user.entity.Role;
import com.gpdi.hqplus.user.entity.RolePermRel;
import com.gpdi.hqplus.user.entity.UserRoleRel;
import com.gpdi.hqplus.user.entity.query.RoleQuery;
import com.gpdi.hqplus.user.mapper.RolePermRelMapper;
import com.gpdi.hqplus.user.service.IRolePermRelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色权限关联表 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-01
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class RolePermRelServiceImpl extends ServiceImpl<RolePermRelMapper, RolePermRel> implements IRolePermRelService {

	@Autowired
	private IdGenerator idGenerator;

	@Override
	public Set<String> listPermissionCodeByRoleList(Set<String> roleList) {
		List<RolePermRel> rolePermRels = baseMapper.selectList(new QueryWrapper<RolePermRel>()
				.lambda()
				.in(RolePermRel::getRoleCode, roleList)
				.eq(RolePermRel::getStatus, RolePermRel.STATUS_NORMAL)
		);
		if (CollectionUtil.isEmpty(rolePermRels)) {
			return null;
		}
		Set<String> result = CollectionUtil.createSet(rolePermRels.size());
		for (RolePermRel rolePermRel : rolePermRels) {
			result.add(rolePermRel.getPermissionCode());
		}
		return result;
	}

	@Override
	public void updateRolePermission(RoleQuery query) {
		List<RolePermRel> rolePermRels = baseMapper.selectList(new QueryWrapper<RolePermRel>()
				.lambda()
				.eq(RolePermRel::getRoleCode, query.getCode())
				.eq(RolePermRel::getStatus, RolePermRel.STATUS_NORMAL)
		);

		Set<String> permissionCodeSet = CollectionUtil.array2Set(query.getPermissionCode());

		// 删除权限关联
		if (CollectionUtil.isNotEmpty(rolePermRels)) {
			for (RolePermRel rolePermRel : rolePermRels) {
				if (permissionCodeSet.contains(rolePermRel.getPermissionCode())) {
					permissionCodeSet.remove(rolePermRel.getPermissionCode());
					continue;
				}

				boolean delete = rolePermRel.deleteById();
				if (!delete) {
					log.error("delete role permission rel fail.");
					throw new ApplicationException(ErrorCode.SERVICE_ERROR, "更新失败");
				}
			}
		}

		// 添加权限关联
		for (String permissionCode : permissionCodeSet) {
			RolePermRel rel = new RolePermRel()
					.setId(idGenerator.getNumberId())
					.setRoleCode(query.getCode())
					.setPermissionCode(permissionCode)
					.setStatus(RolePermRel.STATUS_NORMAL);
			boolean insert = rel.insert();
			if (!insert) {
				log.error("insert role permission rel fail.");
				throw new ApplicationException(ErrorCode.SERVICE_ERROR, "更新失败");
			}
		}
	}

	@Override
	public void deleteByRoleCode(String code) {
		baseMapper.delete(new QueryWrapper<RolePermRel>()
				.lambda()
				.eq(RolePermRel::getRoleCode, code)
		);
	}

	@Override
	public String[] listPermissionCode(String roleCode) {
		List<RolePermRel> rolePermRels = baseMapper.selectList(new QueryWrapper<RolePermRel>()
				.lambda()
				.eq(RolePermRel::getStatus, RolePermRel.STATUS_NORMAL)
				.eq(RolePermRel::getRoleCode, roleCode)
		);
		if (CollectionUtil.isEmpty(rolePermRels)) {
			return null;
		}
		String[] result = new String[rolePermRels.size()];
		for (int i = 0; i < rolePermRels.size(); i++) {
			result[i] = rolePermRels.get(i).getPermissionCode();
		}
		return result;
	}
}
