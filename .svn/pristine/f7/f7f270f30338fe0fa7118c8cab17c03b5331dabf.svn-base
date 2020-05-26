package com.gpdi.hqplus.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.CollectionUtil;
import com.gpdi.hqplus.common.util.IdGenerator;
import com.gpdi.hqplus.user.entity.RolePermRel;
import com.gpdi.hqplus.user.entity.UserRoleRel;
import com.gpdi.hqplus.user.entity.query.RegisterQuery;
import com.gpdi.hqplus.user.mapper.UserRoleRelMapper;
import com.gpdi.hqplus.user.service.IUserRoleRelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-01
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserRoleRelServiceImpl extends ServiceImpl<UserRoleRelMapper, UserRoleRel> implements IUserRoleRelService {
    @Autowired
    private IdGenerator idGenerator;

    @Override
    public void updateUserRole(RegisterQuery query) {
        List<UserRoleRel> userRoleRels = baseMapper.selectList(new QueryWrapper<UserRoleRel>()
                .lambda()
                .eq(UserRoleRel::getUserId, query.getId())
                .eq(UserRoleRel::getStatus, UserRoleRel.STATUS_NORMAL)
        );

        Set<String> roleCodeSet = CollectionUtil.array2Set(query.getRoles());

        // 删除角色关联
        if (CollectionUtil.isNotEmpty(roleCodeSet)) {
            for (UserRoleRel userRoleRel : userRoleRels) {
                if (roleCodeSet.contains(userRoleRel.getRoleCode())) {
                    roleCodeSet.remove(userRoleRel.getRoleCode());
                    continue;
                }

                boolean delete = userRoleRel.deleteById();
                if (!delete) {
                    log.error("delete user role rel fail.");
                    throw new ApplicationException(ErrorCode.SERVICE_ERROR, "更新失败");
                }
            }
        }

        // 添加角色关联
        for (String roleCode : roleCodeSet) {
            UserRoleRel rel = new UserRoleRel()
                    .setId(idGenerator.getNumberId())
                    .setUserId(query.getId())
                    .setRoleCode(roleCode)
                    .setStatus(RolePermRel.STATUS_NORMAL);
            boolean insert = rel.insert();
            if (!insert) {
                log.error("insert user role rel fail.");
                throw new ApplicationException(ErrorCode.SERVICE_ERROR, "更新失败");
            }
        }
    }

    @Override
    public Set<String> listRoleCodeByUserId(Long userId) {
        List<UserRoleRel> userRoleRels = baseMapper.selectList(new QueryWrapper<UserRoleRel>()
                .lambda()
                .eq(UserRoleRel::getUserId, userId)
                .eq(UserRoleRel::getStatus, UserRoleRel.STATUS_NORMAL)
        );
        if (CollectionUtil.isEmpty(userRoleRels)) {
            return null;
        }
        Set<String> result = CollectionUtil.createSet(userRoleRels.size());
        for (UserRoleRel userRoleRel : userRoleRels) {
            result.add(userRoleRel.getRoleCode());
        }
        return result;
    }
}
