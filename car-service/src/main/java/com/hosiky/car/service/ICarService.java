package com.hosiky.car.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hosiky.car.domain.dto.CarRegisterDTO;
import com.hosiky.car.domain.dto.CarUpdateDTO;
import com.hosiky.car.domain.vo.CarDetailVO;
import com.hosiky.car.domain.vo.CarListVO;
import com.hosiky.common.entity.po.Cars;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.List;

public interface ICarService extends IService<Cars> {

    void carRegister(CarRegisterDTO carRegisterDTO);

    void updateCar(BigInteger id, CarUpdateDTO carUpdateDTO);

    IPage<CarListVO> getAllCars(int current, int size);

    CarDetailVO getCarDetailMessage(BigInteger id);

    List<String> addImages(Long carId, MultipartFile[] files);
}
