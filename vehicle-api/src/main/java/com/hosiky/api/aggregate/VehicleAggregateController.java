package com.hosiky.api.aggregate;

import com.hosiky.api.client.CarClient;
import com.hosiky.api.client.InventoriesClient;
import com.hosiky.api.domain.dto.CarRegisterDTO;
import com.hosiky.api.domain.dto.InventoriesDTO;
import com.hosiky.api.domain.dto.VehicleRegisterRequestDTO;
import com.hosiky.api.domain.vo.CarVO;
import com.hosiky.common.domain.Result;
import com.hosiky.common.entity.po.Cars;
import com.hosiky.common.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicle")
@Slf4j
@RequiredArgsConstructor


public class VehicleAggregateController {

    private final CarClient carClient;

    private final InventoriesClient inventoriesClient;

    @PostMapping
    public Result<Void> registerWithInventory(@RequestBody VehicleRegisterRequestDTO vehicleRegisterRequestDTO) {

//        添加汽车信息
        log.info("dto的信息为", vehicleRegisterRequestDTO);
        CarRegisterDTO carRegisterDTO = new CarRegisterDTO();
        BeanUtils.copyProperties(vehicleRegisterRequestDTO, carRegisterDTO);
        Result<CarVO> carsResult = carClient.carRegister(carRegisterDTO);

        Long carId = carsResult.getData().getId();

//          库存信息
        InventoriesDTO inventoriesDTO = new InventoriesDTO();
        BeanUtils.copyProperties(vehicleRegisterRequestDTO, inventoriesDTO);
        inventoriesDTO.setCarId(carId);
        inventoriesClient.create(inventoriesDTO);

        return Result.success();
    }

}
