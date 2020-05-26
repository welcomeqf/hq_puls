package com.gpdi.hqplus.common.config.security;

import com.gpdi.hqplus.common.filter.JwtFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author: lianghb
 * @create: 2019-06-30 00:12
 **/
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()

                // 下载文件不要求身份验证
                .antMatchers("/*/*/file/byId").permitAll()
                .antMatchers("/*/*/file/byName").permitAll()

                // 短信服务不要求身份验证
                .antMatchers("/*/*/message/**").permitAll()

                // 登录请求不进行身份验证
                .antMatchers("/*/*/authentication/login").permitAll()
                // 注册请求
                .antMatchers("/*/*/user/register").permitAll()

                .anyRequest().authenticated()

                .and()
                .headers().cacheControl();

        // 添加jwt token过滤器
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity.exceptionHandling()
                .authenticationEntryPoint(new EntryPointUnauthorizedHandler())
                .accessDeniedHandler(new RestAccessDeniedHandler());
    }
}
