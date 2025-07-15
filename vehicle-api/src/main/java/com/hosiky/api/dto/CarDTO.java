package com.hosiky.api.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CarDTO implements Serializable {

    private String carId;

    private String carName;

    private String state;

    private String typeId;

    private String image;

    private double carPrice;

    private String description;

    private boolean isDeleted;
}
