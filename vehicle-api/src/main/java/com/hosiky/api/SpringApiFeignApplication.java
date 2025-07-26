package com.hosiky.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 这个注解就是禁用数据库
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringApiFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringApiFeignApplication.class, args);
    }
}
