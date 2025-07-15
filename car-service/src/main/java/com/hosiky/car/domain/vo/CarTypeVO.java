package com.hosiky.car.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CarTypeVO implements Serializable {

    private String typeId;

    private String carType;
}
