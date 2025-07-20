package com.hosiky.inventories.domain.vo;

import lombok.Data;

import java.math.BigInteger;

@Data
public class InventoriesDetailVO {

    private BigInteger id;
    private Long sellerId;
    private Long carId;
    private Integer quantity;
}
