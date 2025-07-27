package com.hosiky.car.controller;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hosiky.car.domain.dto.CarRegisterDTO;
import com.hosiky.car.domain.dto.CarUpdateDTO;
import com.hosiky.car.domain.vo.CarDetailVO;
import com.hosiky.car.domain.vo.CarListVO;
import com.hosiky.car.domain.vo.CarVO;
import com.hosiky.car.service.ICarImageService;
import com.hosiky.car.service.ICarService;
import com.hosiky.common.client.MyMinioClient;
import com.hosiky.common.domain.Result;
import com.hosiky.common.entity.po.Cars;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.List;

@Tag(name = "车辆管理")
@RestController
@RequestMapping("/api/car")
@Slf4j
@CrossOrigin

@RequiredArgsConstructor
public class CarController {

    private final ICarService carService;

    private final MyMinioClient minioClient;

    private final ICarImageService carImageService;

    @Operation(summary = "注册carRegister")
    @PostMapping
    public Result<CarVO> carRegister(@RequestBody CarRegisterDTO carRegisterDTO) {
        CarVO carVO = carService.carRegister(carRegisterDTO);
        return Result.success(carVO);
    }

    @Operation(summary = "update Car message")
    @PutMapping("/{id}")
    public Result carUpdate (BigInteger id , @RequestBody CarUpdateDTO carUpdateDTO) {
        carService.updateCar(id, carUpdateDTO);
        return Result.success("修改成功");
    }

    @Operation(summary = "获取所有的cars信息")
    @GetMapping("/all")
    public Result<IPage<CarListVO>> getAllCarList (@RequestParam(defaultValue = "1") int current,
                                                   @RequestParam(defaultValue = "10") int size) {

        IPage<CarListVO> carListVOIPage = carService.getAllCars(current,size);
        return Result.success(carListVOIPage);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除car信息")
    public Result deleteCar (@PathVariable  BigInteger id) {
        carService.removeById(id);
        return Result.success("根据id删除car成功");
    }

    @Operation(summary = "get the car detailMessage")
    @GetMapping("/{id}")
    public Result<CarDetailVO> getCarDetail (@PathVariable BigInteger id) {
        CarDetailVO carDetailVO = carService.getCarDetailMessage(id);
        return Result.success(carDetailVO);
    }

    @Operation(summary = "upload file")
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws FileUploadException {
        String upload = minioClient.upload(file);
        return Result.success(upload);
    }

    @PostMapping("/{carId}/images")
    public Result<List<String>> addImages(@PathVariable Long carId,
                                          @RequestParam("files") MultipartFile[] files) {
        List<String> ImagesList = carService.addImages(carId,files);
        return Result.success(ImagesList);
    }

    @Operation(summary = "删除汽车的照片")
    @DeleteMapping("/{carId}/{sortId}")
    public Result deleteCarImages(@PathVariable Long carId, @PathVariable Integer sortId) {
        carImageService.deleteCarImages(carId,sortId);
        return Result.success("删除成功");
    }

}
