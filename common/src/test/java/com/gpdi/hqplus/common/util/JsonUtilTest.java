package com.gpdi.hqplus.common.util;

import com.alibaba.fastjson.JSON;
import com.gpdi.hqplus.common.entity.UserInfo;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class JsonUtilTest {

    @Test
    public void map2Bean() {

        Map<String,String> map  = CollectionUtil.createHashMap();

        Long[] ids = {599364284027408384l,599364284451033088l,599364284589445120l};
        map.put("meeting_room_extend_service_resources",
                JsonUtil.bean2JsonString(ids));

        System.out.println(JsonUtil.bean2JsonString(map));

        System.out.println(JsonUtil.json2Bean(null,Long[].class));

    }
}