package com.gpdi.hqplus.authentication.feign.fallback;

import com.gpdi.hqplus.authentication.feign.UserFeignClient;
import com.gpdi.hqplus.common.entity.vo.JsonResultVO;

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
