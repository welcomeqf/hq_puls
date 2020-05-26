package com.gpdi.hqplus.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.constants.RedisConstants;
import com.gpdi.hqplus.common.entity.UserInfo;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.manager.RedisService;
import com.gpdi.hqplus.common.manager.SmsService;
import com.gpdi.hqplus.common.util.*;
import com.gpdi.hqplus.user.entity.User;
import com.gpdi.hqplus.user.entity.UserExtend;
import com.gpdi.hqplus.user.entity.query.UserListQuery;
import com.gpdi.hqplus.user.entity.query.RegisterQuery;
import com.gpdi.hqplus.user.entity.query.UserQuery;
import com.gpdi.hqplus.user.entity.vo.LoginInfoVO;
import com.gpdi.hqplus.user.entity.vo.UserBusinessVO;
import com.gpdi.hqplus.user.entity.vo.UserExtendVO;
import com.gpdi.hqplus.user.entity.vo.UserListVO;
import com.gpdi.hqplus.user.mapper.UserMapper;
import com.gpdi.hqplus.user.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-01
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private SmsService smsService;

    @Autowired
    private IUserExtendService extendService;
    @Autowired
    private IUserRoleRelService roleRelService;
    @Autowired
    private IRolePermRelService rolePermRelService;
    @Autowired
    private IUserDeptRelService deptRelService;
    @Autowired
    private UserMapper userMapper;

    private final String REGISTER_LOCK = "REGISTER_LOCK::";

    @Override
    public Page<UserListVO> listUser(UserListQuery query) {
        Page<User> page = new Page<>();
        page.setCurrent(query.getCurrent());
        page.setSize(query.getSize());

        LambdaQueryWrapper<User> queryWrapper = new QueryWrapper<User>()
                .lambda()
                .orderByDesc(User::getId);

        // 多条件查询
        UserListQuery.Params params = query.getParams();
        if (params != null) {
            if (StringUtil.isNotBlank(params.getPhone())) {
                queryWrapper.like(User::getPhone, params.getPhone());
            }
            if (StringUtil.isNotBlank(params.getUserType())) {
                queryWrapper.like(User::getUserType, params.getUserType());
            }
        }

        baseMapper.selectPage(page, queryWrapper);

        Set<Long> userIds = CollectionUtil.createSet(query.getSize());
        List<UserListVO> result = CollectionUtil.createArrayList(query.getSize());
        for (User user : page.getRecords()) {
            UserListVO userListVO = BeanPowerHelper.mapCompleteOverrider(user, UserListVO.class);
            userIds.add(user.getId());
            result.add(userListVO);
        }

        Map<Long, UserExtend> map = extendService.listByUserSet(userIds);
        for (UserListVO userListVO : result) {
            UserExtend userExtend = map.get(userListVO.getId());
            BeanPowerHelper.mapPartOverrider(userExtend, userListVO);
        }

        return BeanPowerHelper.mapPage(page, result);
    }

    /**
     * user2LoginInfoVO
     *
     * @param user
     * @param userExtend
     * @param token
     * @return
     */
    private LoginInfoVO user2LoginInfoVO(User user, UserExtend userExtend, String token) {
        LoginInfoVO vo = BeanPowerHelper.mapCompleteOverrider(user, LoginInfoVO.class);
        BeanPowerHelper.mapCompleteOverrider(vo, userExtend);
        vo.setToken(token);
        return vo;
    }

    /**
     * user2UserInfo
     *
     * @param user
     * @param userExtend
     * @param token
     * @param redisKey
     * @return
     */
    private UserInfo user2UserInfo(User user, UserExtend userExtend, String token, String redisKey) {
        Set<String> roleSet = roleRelService.listRoleCodeByUserId(user.getId());
        Set<String> permissionSet = rolePermRelService.listPermissionCodeByRoleList(roleSet);
        Set<String> groupSet = deptRelService.listDeptByUserId(user.getId());

        UserInfo info = BeanPowerHelper.mapCompleteOverrider(user, UserInfo.class);
        BeanPowerHelper.mapCompleteOverrider(info, userExtend);
        info.setToken(token);
        info.setRedisKey(redisKey);
        info.setRoleSet(roleSet);
        info.setPermissionSet(permissionSet);
        info.setGroupSet(groupSet);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(roleSet)) {
            for (String roleCode : roleSet) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + roleCode));
            }
        }
        if (CollectionUtil.isNotEmpty(permissionSet)) {
            for (String roleCode : permissionSet) {
                authorities.add(new SimpleGrantedAuthority("GROUP_" + roleCode));
            }
        }
        if (CollectionUtil.isNotEmpty(groupSet)) {
            for (String roleCode : groupSet) {
                authorities.add(new SimpleGrantedAuthority("GROUP_" + roleCode));
            }
        }
        info.setAuthorities(authorities);
        return info;
    }

    /**
     * 添加一个新用户
     *
     * @param query
     */
    private void addNewUser(RegisterQuery query) {
        // 获取分布式锁
        String lockKey = REGISTER_LOCK + query.getPhone();
        boolean lock = redisService.getLock(lockKey, 60);
        if (!lock) {
            throw new ApplicationException(ErrorCode.SERVICE_ERROR, "网络忙，请稍后再试");
        }

        try {
            Integer count = baseMapper.selectCount(new QueryWrapper<User>()
                    .lambda()
                    .eq(User::getPhone, query.getPhone())
            );
            if (count != null && count > 0) {
                throw new ApplicationException(ErrorCode.RESOURCES_EXISTING, "该手机号码已注册");
            }

            // 用户基本信息
            User user = new User()
                    .setId(idGenerator.getNumberId())
                    .setPhone(query.getPhone())
                    .setAccount(query.getAccount())
                    .setPassword(BCryptUtil.hash(query.getPassword()))
                    .setEmail(query.getEmail())
                    .setUserType(query.getUserType())
                    .setStatus(User.STATUS_NORMAL);

            boolean insertUser = user.insert();
            if (!insertUser) {
                log.error("insert user error");
                throw new ApplicationException(ErrorCode.SERVICE_ERROR, "注册失败");
            }

            // 用户扩展信息
            UserExtend userExtend = new UserExtend()
                    .setUserId(user.getId())
                    .setId(idGenerator.getNumberId())
                    .setUserName(query.getUserName())
                    .setStatus(UserExtend.STATUS_NORMAL);

            boolean insertExtend = userExtend.insert();
            if (!insertExtend) {
                log.error("insert user extend error");
                throw new ApplicationException(ErrorCode.SERVICE_ERROR, "注册失败");
            }
        } finally {
            redisService.deleteLock(lockKey);
        }
    }

    @Override
    public void addBusinessUser(RegisterQuery query) {
        query.setUserType(User.USER_TYPE_BUSINESS);
        addNewUser(query);
    }

    @Override
    public void updateUser(RegisterQuery query) {
        // 修改密码
        if (StringUtil.isNotBlank(query.getPassword())) {
            User user = baseMapper.selectById(query.getId());
            if (user == null) {
                throw new ApplicationException(ErrorCode.RESOURCES_NOT_FIND, "查询用户信息失败");
            }
            user.setPassword(BCryptUtil.hash(query.getPassword()));
            boolean update = user.updateById();
            if (!update) {
                throw new ApplicationException(ErrorCode.SERVICE_ERROR, "更新用户信息失败");
            }
        }

        // 更新用户角色
        roleRelService.updateUserRole(query);
    }

    /**
     * 根据ID查询用户信息
     * @param id
     * @return
     */
    @Override
    public UserQuery queryUserById(Long id) {
        User user = baseMapper.selectById(id);
        if (user == null) {
            log.error("user query fail.");
            throw new ApplicationException(ErrorCode.SERVICE_ERROR,"用户信息查询失败");
        }
        //装配userQuery
        UserQuery query = new UserQuery();
        query.setId(id);
        query.setAccount(user.getAccount());
        query.setPhone(user.getPhone());
        return query;
    }

    /**
     * 用户注册
     */
    @Override
    public void register(RegisterQuery query) {
        boolean checkPhoneCode = smsService.checkPhoneCode(RedisConstants.SMS_KEY_REGISTER, query.getPhone(), query.getSmsCode());
        if (!checkPhoneCode) {
            throw new ApplicationException(ErrorCode.SERVICE_ERROR, "验证码不正确");
        }

        addNewUser(query);

        redisService.del(RedisConstants.SMS_KEY_REGISTER + query.getPhone());
    }

    @Override
    public PageUtil<UserListVO> listNormal(PageUtil<UserListVO> page) {
        return userMapper.listNormal(page);
    }

    @Override
    public Page<UserBusinessVO> listBusiness(PageUtil<UserBusinessVO> page) {
        return userMapper.listBusiness(page);
    }

    @Override
    public List<UserExtendVO> getUserListByRoleCode(String roleCode) {
        return userMapper.getUserListByRoleCode(roleCode);
    }

    /**
     *  根据ID 获取用户详情
     * @param id
     * @return
     */
    @Override
    public UserBusinessVO getUserBusinessVOById(Long id) {
        return userMapper.getUserBusinessVOById(id);
    }
}
