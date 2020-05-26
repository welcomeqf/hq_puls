package com.gpdi.hqplus;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: lianghb
 * @create: 2019-05-22 16:57
 **/
@EnableSwagger2
@EnableFeignClients
@EnableEurekaClient
@Import(FdfsClientConfig.class)
@MapperScan(basePackages = "com.gpdi.hqplus.file.mapper")
@SpringBootApplication
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
public class FileApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
    }
}
