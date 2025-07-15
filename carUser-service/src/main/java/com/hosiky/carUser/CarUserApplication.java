package com.hosiky.carUser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.hosiky.carUser.mapper")
@ComponentScan(basePackages = {"com.hosiky.carUser", "com.hosiky.common"})
@SpringBootApplication
public class CarUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarUserApplication.class, args);
    }
}
