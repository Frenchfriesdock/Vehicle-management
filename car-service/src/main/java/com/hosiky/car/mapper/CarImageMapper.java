package com.hosiky.car.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hosiky.common.entity.po.CarImages;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CarImageMapper extends BaseMapper<CarImages> {

    @Select("SELECT MAX(sort) FROM car_images WHERE car_id = #{carId}")
    Integer selectMaxSortByCarId(Long carId);
}
