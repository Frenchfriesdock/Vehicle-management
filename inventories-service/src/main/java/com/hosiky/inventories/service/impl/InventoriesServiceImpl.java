package com.hosiky.inventories.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hosiky.common.entity.po.Inventories;
import com.hosiky.inventories.domain.dto.InventoriesDTO;
import com.hosiky.inventories.domain.vo.InventoriesDetailVO;
import com.hosiky.inventories.domain.vo.InventoriesVO;
import com.hosiky.inventories.mapper.InventoriesMapper;
import com.hosiky.inventories.service.InventoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class InventoriesServiceImpl extends ServiceImpl<InventoriesMapper, Inventories> implements InventoriesService {

    private final InventoriesMapper inventoriesMapper;

    @Override
    public InventoriesVO createInventories(InventoriesDTO inventoriesDTO) {
        Inventories entity = BeanUtil.copyProperties(inventoriesDTO, Inventories.class);
        entity.setUpdatedAt(LocalDateTime.now());
        inventoriesMapper.insert(entity);
        return BeanUtil.copyProperties(entity, InventoriesVO.class);
    }

    @Override
    public InventoriesDetailVO detail(Long id) {
        Inventories inventories = this.getById(id);
        return BeanUtil.copyProperties(inventories, InventoriesDetailVO.class);
    }
}
