package com.hosiky.api.client;


import com.hosiky.api.config.FeignConfig;
import com.hosiky.api.dto.CarDTO;
import com.hosiky.common.domain.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * openFeign的远程调用
 * 把远程调用组件变成本地调用
 *
 */
@FeignClient(
        name = "car-service",               // 注册中心里的服务名
        path = "/api",                   // 统一前缀
        configuration = FeignConfig.class   // 个性化配置
)
public interface CarClient {

    @GetMapping("/cars/{id}")
    Result<CarDTO> getCar(@PathVariable Long id);

    @PostMapping("/cars")
    Result<CarDTO> createCar(@RequestBody CarDTO dto);
}
