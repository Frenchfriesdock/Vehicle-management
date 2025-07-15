package com.hosiky.car.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hosiky.car.domain.dto.CarDTO;
import com.hosiky.car.domain.dto.CarPageQueryDTO;
import com.hosiky.car.domain.po.Car;
import com.hosiky.common.domain.PageResult;

public interface ICarService extends IService<Car> {

    void save(CarDTO carDTO);

    PageResult pageSelect(CarPageQueryDTO carPageQueryDTO);

    void update(CarDTO carDTO);
}
