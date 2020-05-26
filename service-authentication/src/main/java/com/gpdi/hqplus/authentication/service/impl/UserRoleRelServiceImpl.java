package com.gpdi.hqplus.authentication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gpdi.hqplus.authentication.entity.UserRoleRel;
import com.gpdi.hqplus.authentication.mapper.UserRoleRelMapper;
import com.gpdi.hqplus.authentication.service.IUserRoleRelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.common.util.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
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
