package com.gpdi.hqplus.resources.feign.fallback;

import com.gpdi.hqplus.common.entity.vo.JsonResultVO;
import com.gpdi.hqplus.resources.entity.query.UserQuery;
import com.gpdi.hqplus.resources.feign.UserFeignClient;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author qf
 * @Date 2019/7/15
 * @Version 1.0
 */
@Slf4j
public class UserFeignClientFallback implements UserFeignClient {


    @Override
    public JsonResultVO<UserQuery> getUserById(Long id) {
        log.error("UserFeignClientFallback.getUserById:get user fail");
        return null;
    }
}
