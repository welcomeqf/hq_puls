package com.gpdi.hqplus.resources.config;

import com.gpdi.hqplus.common.entity.UserContext;
import com.gpdi.hqplus.common.entity.UserInfo;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

/**
 * fegin 调用携带 header
 * @author: lianghb
 * @create: 2019-07-04 17:28
 **/
@Component
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        UserInfo userInfo = UserContext.get();
        if(userInfo==null){
            return;
        }

        requestTemplate.header("Authentication", userInfo.getToken());
        requestTemplate.header("ProjectCode", userInfo.getProjectCode());
    }
}
