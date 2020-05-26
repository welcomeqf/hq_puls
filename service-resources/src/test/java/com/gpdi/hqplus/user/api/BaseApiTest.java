package com.gpdi.hqplus.user.api;

import com.gpdi.hqplus.common.manager.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseApiTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void hello() {
        boolean v1 = redisService.set("v1", "lianghb");
        System.out.println(v1);

        Object v11 = redisService.get("v1");
        System.out.println(v11);
    }
}