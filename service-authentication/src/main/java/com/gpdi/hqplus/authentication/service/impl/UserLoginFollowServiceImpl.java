package com.gpdi.hqplus.authentication.service.impl;

import com.gpdi.hqplus.authentication.entity.UserLoginFollow;
import com.gpdi.hqplus.authentication.mapper.UserLoginFollowMapper;
import com.gpdi.hqplus.authentication.service.IUserLoginFollowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户登录跟踪 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-01
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserLoginFollowServiceImpl extends ServiceImpl<UserLoginFollowMapper, UserLoginFollow> implements IUserLoginFollowService {

}
