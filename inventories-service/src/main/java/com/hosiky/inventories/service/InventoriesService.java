package com.hosiky.inventories.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hosiky.common.entity.po.Inventories;
import com.hosiky.inventories.domain.dto.InventoriesDTO;
import com.hosiky.inventories.domain.vo.InventoriesDetailVO;
import com.hosiky.inventories.domain.vo.InventoriesVO;

public interface InventoriesService extends IService<Inventories> {

    InventoriesVO createInventories(InventoriesDTO inventoriesDTO);

    InventoriesDetailVO detail(Long id);
}
