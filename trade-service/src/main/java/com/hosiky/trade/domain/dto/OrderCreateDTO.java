package com.hosiky.trade.domain.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderCreateDTO {

    @NotNull(message = "买家ID不能为空")
    private Long buyerId;

    @NotNull(message = "卖家ID不能为空")
    private Long sellerId;

    @NotNull(message = "车辆ID不能为空")
    private Long carId;

    @NotNull(message = "订单金额不能为空")
    private BigDecimal amount;
}