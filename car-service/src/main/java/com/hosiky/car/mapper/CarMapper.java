package com.hosiky.car.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.hosiky.car.domain.dto.CarDTO;
import com.hosiky.car.domain.dto.CarPageQueryDTO;
import com.hosiky.car.domain.po.Car;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CarMapper extends BaseMapper<Car> {


    Page<Car> pageQuery(CarPageQueryDTO carPageQueryDTO);

    @Update("update vehicle_system.car set vehicle_system.car.carName = #{carName}, car.state = #{state}, vehicle_system.car.typeId = #{typeId}, description = #{description}, isDeleted = #{isDeleted}, car.carPrice = #{carPrice} where vehicle_system.car.carId = #{carId}")
    void update(CarDTO carDTO);


}
