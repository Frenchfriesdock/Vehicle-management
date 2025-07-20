package com.hosiky.inventories.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InventoriesDTO {

    @NotNull
    private Long sellerId;
    @NotNull
    private Long carId;
    @Min(0)
    private Integer quantity = 1;
}
