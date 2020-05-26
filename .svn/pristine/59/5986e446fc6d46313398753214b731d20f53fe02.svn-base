package com.gpdi.hqplus.resources.feign;

import com.gpdi.hqplus.common.entity.vo.JsonResultVO;
import com.gpdi.hqplus.resources.entity.query.UserQuery;
import com.gpdi.hqplus.resources.feign.fallback.UserFeignClientFallback;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author qf
 * @Date 2019/7/15
 * @Version 1.0
 */
@FeignClient(value = "service-user",fallback = UserFeignClientFallback.class)
public interface UserFeignClient {

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @GetMapping("/v1/app/user/getUserById/{id}")
    JsonResultVO<UserQuery> getUserById(@PathVariable("id") Long id);
}
