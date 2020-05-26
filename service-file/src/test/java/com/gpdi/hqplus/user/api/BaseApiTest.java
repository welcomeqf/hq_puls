package com.gpdi.hqplus.user.api;

import com.gpdi.hqplus.common.manager.RedisService;
import com.gpdi.hqplus.file.entity.FileResource;
import com.gpdi.hqplus.file.service.IFileResourceService;
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
    @Autowired
    private IFileResourceService fileResourceService;

    @Test
    public void hello() {
        FileResource fileResource = new FileResource();
        fileResource.setId(328382756387l);
        try {

            fileResource.insert();
            System.out.println(1);
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            fileResourceService.save(fileResource);
            System.out.println(1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}