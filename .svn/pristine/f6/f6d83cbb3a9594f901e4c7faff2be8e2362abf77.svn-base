package com.gpdi.hqplus.user.feign.fallback;

import com.gpdi.hqplus.common.entity.vo.JsonResultVO;
import com.gpdi.hqplus.user.feign.UserFeignClient;

/**
 * @author: lianghb
 * @create: 2019-05-23 10:31
 **/
public class UserFeignClientFallback implements UserFeignClient {
    @Override
    public JsonResultVO getHelloInfo(String name) {
        return  JsonResultVO.fail();
    }
}
