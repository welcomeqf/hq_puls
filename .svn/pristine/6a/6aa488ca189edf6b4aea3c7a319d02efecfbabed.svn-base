package com.gpdi.hqplus.common.config;

import com.gpdi.hqplus.common.filter.CharsetFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author: lianghb
 * @create: 2019-06-10 14:41
 **/
@Configuration
public class DefaultWebConfig {

    /**
     * Rest 客户端
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 编码拦截器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean charsetFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new CharsetFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(0);

        return filterRegistrationBean;
    }
}
