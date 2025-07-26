package com.hosiky.car.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hosiky.common.entity.po.CarImages;

public interface ICarImageService extends IService<CarImages> {
    void deleteCarImages(Long carId, Integer sortId);
}
