package com.hosiky.car.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hosiky.api.client.CarClient;
import com.hosiky.car.domain.dto.CarDTO;
import com.hosiky.car.domain.dto.CarPageQueryDTO;
import com.hosiky.car.domain.po.Car;
import com.hosiky.car.mapper.CarMapper;
import com.hosiky.car.service.ICarService;
import com.hosiky.common.domain.PageResult;
import com.hosiky.common.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements ICarService {

    private final CarMapper carMapper;

    private final CarClient carClient;

    @Override
    public void save(CarDTO carDTO) {
        Long carUserId = UserContext.getCurrentId();

        Car car = new Car();

        BeanUtils.copyProperties(carDTO, car);

        car.setCreateTime(LocalDateTime.now());
        car.setCarUserId(Math.toIntExact(carUserId));
        this.save(car);
    }

    @Override
    public PageResult pageSelect(CarPageQueryDTO carPageQueryDTO) {

        PageHelper.startPage(carPageQueryDTO.getPage(),carPageQueryDTO.getPageSize());

        List<Car> carList =  carMapper.pageQuery(carPageQueryDTO);

        Page<Car> page = (Page<Car>) carList;

        long total = page.getTotal();

        List<Car> records = page.getResult();

        return new PageResult(total,records);
    }

    @Override
    public void update(CarDTO carDTO) {
        carMapper.update(carDTO);
    }


}
