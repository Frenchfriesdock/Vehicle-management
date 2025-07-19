package com.hosiky.car.domain.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarUpdateDTO {
    @NotBlank
    private String  title;

    @Size(max = 50)
    private String brand;

    private String model;
    private String vin;
    private String color;
    @NotNull
    @DecimalMin(value = "0.00")
    private BigDecimal price;

    private String fuelType;
    private Integer seats;
}
