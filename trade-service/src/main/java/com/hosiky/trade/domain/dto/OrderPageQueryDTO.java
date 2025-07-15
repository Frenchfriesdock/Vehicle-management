package com.hosiky.trade.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderPageQueryDTO implements Serializable {
    private Integer page; // 页码
    private Integer pageSize; // 每页的记录数

    private Long userId;
}
