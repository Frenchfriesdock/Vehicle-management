package com.hosiky.api.domain.dto;


import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class InventoriesDTO {

    private Long sellerId;

    private Long carId;
    private Integer quantity = 1;
}
