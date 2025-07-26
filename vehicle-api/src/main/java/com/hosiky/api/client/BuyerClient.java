package com.hosiky.api.client;


import com.hosiky.api.config.FeignConfig;
import com.hosiky.common.domain.Result;
import com.hosiky.common.entity.po.Buyers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "buyers-service",               // 注册中心里的服务名
        path = "/api",                   // 统一前缀
        configuration = FeignConfig.class   // 个性化配置
)


public interface BuyerClient {

    @GetMapping("/buyers/{id}")
    public Result<Buyers> getBuyers(@PathVariable Long id);

}
