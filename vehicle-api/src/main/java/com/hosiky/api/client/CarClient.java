package com.hosiky.api.client;



import com.hosiky.api.config.FeignConfig;
import com.hosiky.api.domain.dto.CarRegisterDTO;
import com.hosiky.api.domain.vo.CarVO;
import com.hosiky.common.domain.Result;
import com.hosiky.common.entity.po.Cars;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

/**
 * openFeign的远程调用
 * 把远程调用组件变成本地调用
 * 向其他服务体统Cars相关接口
 *
 */
@FeignClient(
        name = "car-service",               // 注册中心里的服务名
        path = "/api",                   // 统一前缀
        configuration = FeignConfig.class   // 个性化配置
)

public interface CarClient {

    @PostMapping("/car")
    public Result<CarVO> carRegister(@RequestBody CarRegisterDTO carRegisterDTO);

}
