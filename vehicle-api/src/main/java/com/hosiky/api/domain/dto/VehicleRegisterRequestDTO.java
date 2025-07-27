package com.hosiky.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Data
@NoArgsConstructor      // ← 无参构造
@AllArgsConstructor     // 可选：全参构造
public class VehicleRegisterRequestDTO {

    private Long sellerId;

    private String  title;


    private String brand;

    private String model;
    private String vin;
    private String color;
    private BigDecimal price;

    private String fuelType;
    private Integer seats;

    private Long carId;
    private Integer quantity = 1;

}
