package com.hosiky.common.entity.po;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@TableName("cars")
public class Cars {
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

    private DateTime createdAt;
    private DateTime updatedAt;

}
