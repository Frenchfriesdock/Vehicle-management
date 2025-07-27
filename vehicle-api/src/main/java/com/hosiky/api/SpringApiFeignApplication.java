package com.hosiky.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 这个注解就是禁用数据库
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = "com.hosiky.api.client") // 扫描CarClient所在的包
public class SpringApiFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringApiFeignApplication.class, args);
    }
}
