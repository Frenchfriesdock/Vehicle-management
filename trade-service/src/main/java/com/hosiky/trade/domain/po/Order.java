package com.hosiky.trade.domain.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("orders")
public class Order implements Serializable {

    @TableId(value = "orders_id")
    private Integer ordersId;

    private Integer userId;

    private Integer carId;

    private Integer carUserId;

    private double carPrice;

    private int quantity; // 数量(这个的默认值是1）

    private double money;

    private LocalDateTime orderTime;
}
