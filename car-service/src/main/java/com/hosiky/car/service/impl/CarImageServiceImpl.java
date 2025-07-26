package com.hosiky.car.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hosiky.car.mapper.CarImageMapper;
import com.hosiky.car.mapper.CarMapper;
import com.hosiky.car.service.ICarImageService;
import com.hosiky.common.entity.po.CarImages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class CarImageServiceImpl extends ServiceImpl<CarImageMapper, CarImages> implements ICarImageService {

    private final CarImageMapper carImageMapper;

    private final CarMapper carMapper;

    @Override
    public void deleteCarImages(Long carId, Integer sortId) {
        if(carMapper.selectById(carId) == null) {
            throw new RuntimeException("该汽车不存在，不能删除");
        }

        lambdaUpdate()
        .eq(CarImages::getCarId, carId)
                .eq(CarImages::getSort,sortId)
                .remove();
    }
}
