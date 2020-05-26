package com.gpdi.hqplus.common.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 编码过滤器
 *
 * @author: lianghb
 * @create: 2019-06-11 16:12
 **/
public class CharsetFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/json;charset=utf-8");
        servletResponse.setCharacterEncoding("utf-8");

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
