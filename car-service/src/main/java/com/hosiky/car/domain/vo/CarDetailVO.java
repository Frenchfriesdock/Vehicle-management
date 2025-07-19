package com.hosiky.car.domain.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CarDetailVO {

    private Long id;
    private String title;
    private String brand;
    private String model;
    private String vin;
    private String color;
    private BigDecimal price;
    private String fuelType;
    private Integer seats;
    private String status;
    private List<String> images;
//    private String sellerCompanyName;
}
