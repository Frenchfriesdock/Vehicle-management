package com.hosiky.inventories.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hosiky.common.entity.po.Inventories;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InventoriesMapper extends BaseMapper<Inventories> {
}