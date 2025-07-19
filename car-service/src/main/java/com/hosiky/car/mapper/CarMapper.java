package com.hosiky.car.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.hosiky.common.entity.po.Cars;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CarMapper extends BaseMapper<Cars> {

}
