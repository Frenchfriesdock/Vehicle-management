package com.hosiky.inventories.controller;

import com.hosiky.common.domain.Result;
import com.hosiky.inventories.domain.dto.InventoriesDTO;
import com.hosiky.inventories.domain.vo.InventoriesDetailVO;
import com.hosiky.inventories.domain.vo.InventoriesVO;
import com.hosiky.inventories.service.InventoriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "库存管理")
@RestController
@RequestMapping("/api/inventories")
@Slf4j
@CrossOrigin
@RequiredArgsConstructor

public class InventoriesController {

    private final InventoriesService inventoriesService;

    @Operation(summary = "添加库存")
    @PostMapping
    public Result<InventoriesVO> create(@RequestBody InventoriesDTO inventoriesDTO) {
        InventoriesVO inventoriesVO = inventoriesService.createInventories(inventoriesDTO);
        return Result.success(inventoriesVO);
    }

    @Operation(summary = "查看信息")
    @GetMapping("/{id}")
    public InventoriesDetailVO detail(@PathVariable Long id) {
        return inventoriesService.detail(id);
    }

}
