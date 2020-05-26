package com.gpdi.hqplus.authentication.service;

import com.gpdi.hqplus.authentication.entity.User;
import com.gpdi.hqplus.authentication.entity.UserExtend;
import com.gpdi.hqplus.authentication.entity.query.LoginQuery;
import com.gpdi.hqplus.authentication.entity.vo.LoginInfoVO;
import com.gpdi.hqplus.common.constants.RedisConstants;
import com.gpdi.hqplus.common.manager.RedisService;
import com.gpdi.hqplus.common.util.BCryptUtil;
import com.gpdi.hqplus.common.util.IdGenerator;
import com.gpdi.hqplus.common.util.JwtTokenUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IUserServiceTest {
    @Autowired
    private IUserService userService;

    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private RedisService redisService;

    @Test
    public void add() {
        int count = 1067;
        for (int i = 0; i < 1000; i++) {
            String phone = "1567777" + count++;

            User user = new User();
            user.setId(idGenerator.getNumberId());
            user.setPhone(phone);
            user.setPassword(BCryptUtil.hash("123456"));
            user.setStatus(User.STATUS_NORMAL);
            user.setUserType(User.STATUS_NORMAL);
            System.out.println(phone + " : " + user.insert());

            UserExtend extend = new UserExtend();
            extend.setId(idGenerator.getNumberId());
            extend.setUserName("测试 " + count);
            extend.setUserId(user.getId());
            extend.setDefaultProjectCode("sz");
            System.out.println(phone + " extend : " + extend.insert());
        }

    }

    @Test
    public void login() {
        LoginQuery query = new LoginQuery();
        query.setPhone("15688888888");
        query.setPassword("123456");

        LoginInfoVO login = userService.login(query);
        System.out.println(login);
    }

    @Test
    public void tree() {
        String token = JwtTokenUtil.getTokenFromJWT("eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1NjI1NzE5MzcsInN1YiI6ImFjZDMzYjJjMmIwZTRiMmM5N2ZhZDE2MzYxYzk5ZmZlIiwiY3JlYXRlZCI6MTU2MTk2NzEzNzMwM30.i1PQUomcrkRdFeVTfHEG9fGQzv2vsIEYzBNzlKsX36AK1K5geFRAaSTstWXyrKBIlLFLNM2S26yDzQO1dHs70Q");
        System.out.println(token);
        Object o = redisService.get(RedisConstants.USER_INFO + token);
        System.out.println(o);
    }
}