package com.gpdi.hqplus.base.feign.fallback;

import com.gpdi.hqplus.base.feign.UserFeignClient;
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
