package com.hosiky.carUser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hosiky.carUser.domain.po.CarUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CarUserMapper extends BaseMapper <CarUser> {

}
