package com.hosiky.car.domain.vo;

import com.hosiky.common.entity.Enum.CarStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CarVO {


    private Long id;
    private Long sellerId;
    private String title;
    private String brand;
    private String model;
    private String vin;
    private String color;
    private BigDecimal price;
    private String fuelType;
    private Integer seats;
    private CarStatusEnum status;

    private LocalDateTime createdAt;

}
