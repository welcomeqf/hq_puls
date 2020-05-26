package com.gpdi.hqplus.filter;

import com.gpdi.hqplus.constants.ContextKeyConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: lianghb
 * @create: 2019-05-22 11:13
 **/
@Slf4j
@Component
public class PostFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER + 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        long startTime = (long) ctx.get(ContextKeyConstants.START_TIMESTAMP);
        Boolean isSuccess = (Boolean) ctx.get(ContextKeyConstants.REQUEST_SUCCESS_FLAG);
        long time = System.currentTimeMillis() - startTime;
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("request: isSuccess=%s time=%sms method=%s uri=%s", isSuccess, time, request.getMethod(), request.getRequestURI()));
        return null;
    }
}
