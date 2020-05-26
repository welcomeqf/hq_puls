package com.gpdi.hqplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: lianghb
 * @create: 2019-05-22 16:57
 **/
@EnableSwagger2
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@MapperScan(basePackages = "com.gpdi.hqplus.authentication.mapper")
public class AuthenticationApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }
}
