package com.hosiky.api.client;

import com.hosiky.common.domain.Result;
import com.hosiky.common.entity.po.Sellers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (
        name = "sellers-service",
        path = "/api",
        configuration = FeignClient.class
)

public interface SellersClient {

    @GetMapping("/seller/{id}")
    Result<Sellers> getSellers(@PathVariable Long id);

}
