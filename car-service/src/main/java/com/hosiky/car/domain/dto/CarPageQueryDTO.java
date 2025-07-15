package com.hosiky.car.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CarPageQueryDTO implements Serializable {

    private Integer page; // 页码
    private Integer pageSize; // 每页的记录数

    private Integer state; //状态
    private String typeId; //分类ID

    private String carUserName; // 车商名称
    private String carName; // 汽车名称
    private double carPrice; //汽车价格
}
