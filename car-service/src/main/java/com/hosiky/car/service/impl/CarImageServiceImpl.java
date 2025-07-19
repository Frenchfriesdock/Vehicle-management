package com.hosiky.car.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hosiky.car.mapper.CarImageMapper;
import com.hosiky.car.service.ICarImageService;
import com.hosiky.common.entity.po.CarImages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class CarImageServiceImpl extends ServiceImpl<CarImageMapper, CarImages> implements ICarImageService {
}
