package com.gpdi.hqplus.common.config.security;

import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.entity.vo.JsonResultVO;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限不足
 * @author: lianghb
 * @create: 2019-06-30 22:16
 **/
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setStatus(403);

        JsonResultVO vo = JsonResultVO.fail();
        vo.setCode(ErrorCode.AUTHENTICATION_ERROR.getCode());
        vo.setMsg("权限不足");
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.getWriter().write(vo.toJSON());
    }
}
