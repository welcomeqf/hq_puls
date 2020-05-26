package com.gpdi.hqplus.common.filter;

import com.gpdi.hqplus.common.entity.UserContext;
import com.gpdi.hqplus.common.manager.RedisService;
import com.gpdi.hqplus.common.util.JwtTokenUtil;
import com.gpdi.hqplus.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * jwt 过滤器
 *
 * @author: lianghb
 * @create: 2019-06-11 16:12
 **/
@Component
public class JwtFilter extends OncePerRequestFilter {
    private UserDetailsService userDetailsService;

    @Autowired
    public JwtFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String projectCode = request.getHeader("ProjectCode");

        String authenticationToken = request.getHeader("Authentication");
        if (StringUtil.isNotBlank(authenticationToken)) {
            String redisKey = JwtTokenUtil.getTokenFromJWT(authenticationToken);
            UserDetails userDetails = userDetailsService.loadUserByUsername(redisKey);
            if (userDetails != null) {
                UserContext.get().setProjectCode(projectCode);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);

        UserContext.remove();
    }
}
