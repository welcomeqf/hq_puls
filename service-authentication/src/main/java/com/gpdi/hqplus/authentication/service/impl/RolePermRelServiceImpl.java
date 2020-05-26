package com.gpdi.hqplus.authentication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gpdi.hqplus.authentication.entity.RolePermRel;
import com.gpdi.hqplus.authentication.entity.UserRoleRel;
import com.gpdi.hqplus.authentication.mapper.RolePermRelMapper;
import com.gpdi.hqplus.authentication.service.IRolePermRelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.common.util.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
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
}
