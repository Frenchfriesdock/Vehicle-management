package com.hosiky.api.client;

import com.hosiky.api.config.FeignConfig;
import com.hosiky.api.domain.dto.InventoriesDTO;
import com.hosiky.api.domain.vo.InventoriesVO;
import com.hosiky.common.domain.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "inventories-service",               // 注册中心里的服务名
        path = "/api",                   // 统一前缀
        configuration = FeignConfig.class   // 个性化配置
)


public interface InventoriesClient {


    @PostMapping("/inventories")
    public Result<InventoriesVO> create(@RequestBody InventoriesDTO inventoriesDTO);

}
