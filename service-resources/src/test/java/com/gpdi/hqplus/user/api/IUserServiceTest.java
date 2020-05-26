package com.gpdi.hqplus.user.api;

import com.gpdi.hqplus.common.constants.RedisConstants;
import com.gpdi.hqplus.common.manager.RedisService;
import com.gpdi.hqplus.common.util.IdGenerator;
import com.gpdi.hqplus.common.util.JwtTokenUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IUserServiceTest {
    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private RedisService redisService;

    @Test
    public void tree(){
        String token = JwtTokenUtil.getTokenFromJWT("eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1NjI1NzE5MzcsInN1YiI6ImFjZDMzYjJjMmIwZTRiMmM5N2ZhZDE2MzYxYzk5ZmZlIiwiY3JlYXRlZCI6MTU2MTk2NzEzNzMwM30.i1PQUomcrkRdFeVTfHEG9fGQzv2vsIEYzBNzlKsX36AK1K5geFRAaSTstWXyrKBIlLFLNM2S26yDzQO1dHs70Q");
        System.out.println(token);
        Object o = redisService.get(RedisConstants.USER_INFO + token);
        System.out.println(o);
    }
}