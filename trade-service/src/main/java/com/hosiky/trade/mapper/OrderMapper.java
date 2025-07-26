package com.hosiky.trade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hosiky.common.entity.po.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {

}
