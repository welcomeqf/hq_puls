package com.gpdi.hqplus.base.feign;

import com.gpdi.hqplus.base.feign.fallback.UserFeignClientFallback;
import com.gpdi.hqplus.common.entity.vo.JsonResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: lianghb
 * @create: 2019-05-23 10:28
 **/
@FeignClient(value = "service-user", fallback = UserFeignClientFallback.class)
public interface UserFeignClient {

    @RequestMapping("/user/{name}")
    JsonResultVO getHelloInfo(@PathVariable("name") String name);
}
