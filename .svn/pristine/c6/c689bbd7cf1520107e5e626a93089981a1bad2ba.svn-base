package com.gpdi.hqplus.common.config.security;

import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.entity.vo.JsonResultVO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 身份验证失败类
 *
 * @author: lianghb
 * @create: 2019-06-30 22:12
 **/
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setStatus(401);

        JsonResultVO vo = JsonResultVO.fail();
        vo.setCode(ErrorCode.AUTHENTICATION_ERROR.getCode());
        vo.setMsg("身份验证失败");
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.getWriter().write(vo.toJSON());
    }
}
