package com.gpdi.hqplus.common.advice;

import com.gpdi.hqplus.common.anntation.IgnoreResponseAdvice;
import com.gpdi.hqplus.common.entity.vo.JsonResultVO;
import com.gpdi.hqplus.common.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 全局统一信息返回包装
 *
 * @author: lianghb
 * @create: 2019-06-03 16:39
 **/
@Slf4j
@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class) ||
                aClass.isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        return true;
    }

    @Override
    public JsonResultVO beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof ApplicationException) {
            ApplicationException exception = (ApplicationException) o;
            JsonResultVO vo = new JsonResultVO();
            vo.setCode(exception.getCode());
            vo.setMsg(exception.getMsg());

            return vo;
        }
        
        return JsonResultVO.success(o);
    }
}
