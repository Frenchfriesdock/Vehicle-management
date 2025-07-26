package com.hosiky.trade.domain.dto;

import com.hosiky.common.entity.Enum.OrderStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderUpdateStatusDTO {

    @NotNull(message = "订单ID不能为空")
    private Long orderId;

    @NotNull(message = "订单状态不能为空")
    private OrderStatusEnum status;
}
