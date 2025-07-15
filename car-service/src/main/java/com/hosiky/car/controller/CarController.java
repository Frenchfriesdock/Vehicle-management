package com.hosiky.car.controller;



import com.hosiky.common.domain.PageResult;
import com.hosiky.car.domain.dto.CarDTO;
import com.hosiky.car.domain.dto.CarPageQueryDTO;
import com.hosiky.car.domain.po.Car;
import com.hosiky.car.service.ICarService;
import com.hosiky.common.domain.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "车辆管理")
@RestController
@RequestMapping("/car")
@Slf4j
@CrossOrigin

@RequiredArgsConstructor
public class CarController {

    private final ICarService carService;

    @Operation(summary = "添加汽车功能")
    @PostMapping()
    public void addCar(@RequestBody CarDTO carDTO) {
        carService.save(carDTO);
    }

    @GetMapping("/list")
    @Operation(summary = "展示所有的汽车信息")
    public Result<List<Car>> showCar() {
        List<Car> carList = carService.list();
        return Result.success(carList);
    }

    @GetMapping("/listPage")
    @Operation(summary = "分页查询")
    public Result<PageResult> pageSelect(@RequestParam("page") Integer page,
                                         @RequestParam("pageSize") Integer pageSize,
                                         @RequestParam(required = false) Integer state,
                                         @RequestParam(required = false) String typeId) {
        CarPageQueryDTO carPageQueryDTO = new CarPageQueryDTO();
        carPageQueryDTO.setPage(page);
        carPageQueryDTO.setPageSize(pageSize);
        carPageQueryDTO.setState(state);  // 设置状态参数
        carPageQueryDTO.setTypeId(typeId);  // 设置类型ID参数

        log.info("分页查询：{}", carPageQueryDTO);
        PageResult pageResult = carService.pageSelect(carPageQueryDTO);
        return Result.success(pageResult);
    }

    @Operation(summary = "汽车信息修改")
    @PutMapping("/update")
    public Result updateCar(@RequestBody CarDTO carDTO) {
        log.info("汽车修改:{}",carDTO);
        carService.update(carDTO);
        return Result.success();
    }

    @Operation(summary = "汽车信息删除")
    @DeleteMapping
    public Result deleteCar(@RequestParam Integer carId) {
        log.info("根据carId信息删除汽车{}",carId);
        carService.removeById(carId);
        return Result.success();
    }
}
