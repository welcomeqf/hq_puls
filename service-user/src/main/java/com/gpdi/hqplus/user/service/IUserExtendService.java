package com.gpdi.hqplus.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gpdi.hqplus.user.entity.UserExtend;
import com.gpdi.hqplus.user.entity.query.UserExtendQuery;
import com.gpdi.hqplus.user.entity.vo.UserExtendVO;

import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 用户扩展信息表 服务类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-01
 */
public interface IUserExtendService extends IService<UserExtend> {
    /**
     * 根据 id 获取用户信息
     * @param userId
     * @return
     */
    UserExtend getByUserId(Long userId);

    /**
     * 根据 id 获取用户信息
     * @param userIds
     * @return
     */
    Map<Long,UserExtend> listByUserSet(Set<Long> userIds);

    /**
     * 个性化功能定制
     * @author liujiahui
     * @since 2019-07-02
     * @param query
     */
    void selfMenu(UserExtendQuery query);

    /**
     * 首页功能浏览
     * @return
     */
    String getMenu();
}
