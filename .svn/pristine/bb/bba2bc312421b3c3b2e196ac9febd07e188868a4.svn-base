package com.gpdi.hqplus;

import com.gpdi.hqplus.common.util.JsonUtil;
import lombok.Data;

/**
 * @author: lianghb
 * @create: 2019-06-27 21:24
 **/
public class BitmapTest {
    public static void main(String[] args) {
        BitmapDemo demo = new BitmapDemo();
        demo.setId("12315648");
        int[] map = new int[24];

        map[7-1]=1;
        map[8-1]=1;
        map[9-1]=1;

        demo.setMap(map);

        String jsonString = JsonUtil.bean2JsonString(demo);
        System.out.println(jsonString);

        BitmapDemo demo1 = JsonUtil.json2Bean(jsonString, BitmapDemo.class);
        System.out.println(demo1.toString());
    }

    @Data
    static class BitmapDemo{
        private String id;
        private int[] map;
    }
}
