package com.hosiky.sellers;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.hosiky.sellers", "com.hosiky.common"})
public class SellersUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(SellersUserApplication.class, args);
    }
}
