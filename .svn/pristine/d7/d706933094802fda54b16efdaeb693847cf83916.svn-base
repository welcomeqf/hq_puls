package com.gpdi.hqplus.pass.scheduled;

import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.manager.RedisService;
import com.gpdi.hqplus.pass.service.IProductPassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: zake
 * @Description: 清楚物业放行状态定时器   每天凌晨执行一次
 * @Date: Created in 15:27 2019-07-12
 **/
@Component
@Slf4j
public class ProductPassScheduled {

    @Autowired
    private IProductPassService passService;

    @Autowired
    private RedisService redisService;

    private static final String REDIS_KEY="REDIS_KEY_PRODUCT_PASS_LOSE";


    @Scheduled(cron = "0 0 0 * * ?")
    private void productPassLose(){
        //获取分布式锁
        boolean lock = redisService.getLock(REDIS_KEY, 600);
        if (!lock) {
            throw new ApplicationException(ErrorCode.SERVICE_ERROR, "网络忙，请稍后再试");
        }
        try {
            passService.changeStatus();
        }finally {
            redisService.deleteLock(REDIS_KEY);
        }

    }

}
