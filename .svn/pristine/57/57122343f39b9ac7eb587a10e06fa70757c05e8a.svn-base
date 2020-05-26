package com.gpdi.hqplus.authentication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gpdi.hqplus.authentication.entity.UserExtend;
import com.gpdi.hqplus.authentication.mapper.UserExtendMapper;
import com.gpdi.hqplus.authentication.service.IUserExtendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户扩展信息表 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-01
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserExtendServiceImpl extends ServiceImpl<UserExtendMapper, UserExtend> implements IUserExtendService {

    @Override
    public UserExtend getByUserId(Long userId) {
        return baseMapper.selectOne(new QueryWrapper<UserExtend>()
                .lambda()
                .eq(UserExtend::getUserId,userId)
        );
    }
}
