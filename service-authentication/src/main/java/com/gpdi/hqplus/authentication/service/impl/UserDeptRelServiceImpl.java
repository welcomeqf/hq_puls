package com.gpdi.hqplus.authentication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gpdi.hqplus.authentication.entity.Dept;
import com.gpdi.hqplus.authentication.entity.UserDeptRel;
import com.gpdi.hqplus.authentication.entity.UserRoleRel;
import com.gpdi.hqplus.authentication.mapper.UserDeptRelMapper;
import com.gpdi.hqplus.authentication.service.IUserDeptRelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.common.util.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 部门用户关联表 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-01
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserDeptRelServiceImpl extends ServiceImpl<UserDeptRelMapper, UserDeptRel> implements IUserDeptRelService {

    @Override
    public Set<String> listDeptByUserId(Long userId) {
        List<UserDeptRel> userDeptRels = baseMapper.selectList(new QueryWrapper<UserDeptRel>()
                .lambda()
                .eq(UserDeptRel::getUserId, userId)
                .eq(UserDeptRel::getStatus, UserDeptRel.STATUS_NORMAL)
        );
        if (CollectionUtil.isEmpty(userDeptRels)) {
            return null;
        }
        Set<String> result = CollectionUtil.createSet(userDeptRels.size());
        for (UserDeptRel userDeptRel : userDeptRels) {
            result.add(userDeptRel.getDeptCode());
        }
        return result;
    }
}
