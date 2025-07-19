package com.hosiky.car.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

// 列表页 VO
@Data
public class CarListVO {

    private BigDecimal id;


    private BigDecimal sellerId;
    private String title;
    private String brand;
    private String model;
    private String coverImage;
    private BigDecimal price;

}
