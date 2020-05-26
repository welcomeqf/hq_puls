package com.gpdi.hqplus.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.user.entity.Permission;
import com.gpdi.hqplus.user.entity.query.PermissionListQuery;
import com.gpdi.hqplus.user.entity.vo.PermissionListVO;
import com.gpdi.hqplus.user.mapper.PermissionMapper;
import com.gpdi.hqplus.user.service.IPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户权限表 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-01
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

	@Override
	public Page<PermissionListVO> listPermission(PermissionListQuery query) {
		return null;
	}

	@Override
	public List<PermissionListVO> getPermissionList() {
		List<Permission> permissions = this.baseMapper.selectList(new LambdaQueryWrapper<Permission>()
				.eq(Permission::getStatus, Permission.STATUS_NORMAL));
		List<PermissionListVO> vos = new ArrayList<>();
		if (permissions.size() > 0){
			for (Permission permission : permissions){
				PermissionListVO vo = new PermissionListVO();
				BeanUtils.copyProperties(permission, vo);
				vos.add(vo);
			}
		}
		return vos;
	}

	@Override
	public String[] getPermissionNameByCode(String[] permissionCode) {
		List<Permission> permissions = this.baseMapper.selectList(
				new LambdaQueryWrapper<Permission>()
				.eq(Permission::getStatus, Permission.STATUS_NORMAL)
				.in(Permission::getCode,permissionCode)
		);
		if (permissions.size() > 0){
			String[] names = new String[permissions.size()];
			for (int i = 0; i< permissions.size();i++){
				names[i] = permissions.get(i).getName();
			}
			return names;
		}
		return new String[0];
	}
}
