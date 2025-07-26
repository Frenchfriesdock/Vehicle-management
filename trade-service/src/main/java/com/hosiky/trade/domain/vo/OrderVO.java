package com.hosiky.trade.domain.vo;

import com.hosiky.common.entity.Enum.OrderStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class OrderVO {

    private BigInteger id;
    private String orderNo;
    private BigInteger buyerId;
    private BigInteger sellerId;
    private BigInteger carId;
    private BigDecimal amount;
    private OrderStatusEnum orderStatus;
    private LocalDateTime createdAt;
}
