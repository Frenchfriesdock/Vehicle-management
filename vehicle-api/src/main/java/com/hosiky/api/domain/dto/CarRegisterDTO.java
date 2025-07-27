package com.hosiky.api.domain.dto;



import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Data
public class CarRegisterDTO {

    private Long sellerId;

    private String  title;


    private String brand;

    private String model;
    private String vin;
    private String color;

    private BigDecimal price;

    private String fuelType;
    private Integer seats;
}
