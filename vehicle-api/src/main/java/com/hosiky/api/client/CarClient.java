package com.hosiky.api.client;


import com.hosiky.api.dto.CarDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

/**
 * openFeign的远程调用
 * 把远程调用组件变成本地调用
 *
 */
@FeignClient("car-service")
public interface CarClient {

    @GetMapping("/car/listTest")
    List<CarDTO> CarListByIds(@RequestParam("ids") Collection<Long> ids);


}
