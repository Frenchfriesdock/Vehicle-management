package com.hosiky.api.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InventoriesVO {

    private Long id;
    private Long sellerId;
    private Long carId;
    private Integer quantity;
    private LocalDateTime updatedAt;

}
