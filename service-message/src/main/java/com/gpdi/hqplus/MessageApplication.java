package com.gpdi.hqplus;

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
public class MessageApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class, args);
    }
}
