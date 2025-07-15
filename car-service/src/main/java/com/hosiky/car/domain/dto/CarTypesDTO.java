package com.hosiky.car.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CarTypesDTO implements Serializable {

    private Integer id;

    private String typeId;

    private String carType;
}
