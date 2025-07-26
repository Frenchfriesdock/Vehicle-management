package com.hosiky.car;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.hosiky.car.mapper")
@SpringBootApplication
@EnableFeignClients(basePackages = "com.hosiky.api.client")
@ComponentScan(basePackages = {"com.hosiky.car", "com.hosiky.common"})
public class CarApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarApplication.class, args);
    }
}
