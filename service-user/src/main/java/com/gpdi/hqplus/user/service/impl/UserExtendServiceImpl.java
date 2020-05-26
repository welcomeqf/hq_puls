package com.gpdi.hqplus.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.entity.UserContext;
import com.gpdi.hqplus.common.entity.UserInfo;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.BeanPowerHelper;
import com.gpdi.hqplus.common.util.CollectionUtil;
import com.gpdi.hqplus.common.util.JsonUtil;
import com.gpdi.hqplus.user.entity.UserExtend;
import com.gpdi.hqplus.user.entity.query.UserExtendQuery;
import com.gpdi.hqplus.user.entity.vo.UserExtendVO;
import com.gpdi.hqplus.user.mapper.UserExtendMapper;
import com.gpdi.hqplus.user.service.IUserExtendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @Override
    public Map<Long, UserExtend> listByUserSet(Set<Long> userIds) {
        List<UserExtend> userExtends = baseMapper.selectList(new QueryWrapper<UserExtend>()
                .lambda()
                .in(UserExtend::getUserId, userIds)
        );
        if(CollectionUtil.isEmpty(userExtends)){
            return null;
        }

        Map<Long, UserExtend> map = CollectionUtil.createHashMap(userExtends.size());
        for (UserExtend userExtend : userExtends) {
            map.put(userExtend.getUserId(),userExtend);
        }
        return map;
    }

    /**
     * 个性化功能定制
     *
     * @author liujiahui
     * @since 2019-07-02
     */
    @Override
    public void selfMenu(UserExtendQuery query) {

        UserInfo userInfo = UserContext.get();
        Long userId = userInfo.getId();

        String s = JsonUtil.bean2JsonString(query);
        log.info(s);

        UserExtend userExtend = getByUserId(userId);
        if(userExtend==null){
            log.error("该用户信息在tb_user_exten表中不存在");
        }

        userExtend.setExtendVal(query.getPersonalFunction());
        boolean b = userExtend.updateById();

        if(!b){
            log.error("个性化功能定制更新失败"+b);
        }

    }

    /**
     * 首页功能浏览
     *
     * @author liujiahui
     * @since 2019-07-02
     */
    @Override
    public String getMenu() {

        return getByUserId(UserContext.get().getId()).getExtendVal();

    }
}
