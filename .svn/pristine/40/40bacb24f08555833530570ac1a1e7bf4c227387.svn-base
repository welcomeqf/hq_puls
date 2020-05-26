package com.gpdi.hqplus.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

/**
 * 修改spirngboot默认序列化工具
 * 从jackson改为fastJson
 *
 * @author: lianghb
 * @create: 2019-05-23 09:42
 **/
@Configuration
public class JsonFormatConfig {
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,SerializerFeature.WriteMapNullValue);
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        fastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }
}
