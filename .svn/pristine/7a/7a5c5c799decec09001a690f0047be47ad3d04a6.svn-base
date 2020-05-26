package com.gpdi.hqplus.file.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @author: lianghb
 * @create: 2019-05-24 15:04
 **/
@Configuration
public class WebConfig {
    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(100 * 1024 * 1024);
        return resolver;
    }
}
