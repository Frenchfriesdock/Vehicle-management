package com.hosiky.common.entity.po;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hosiky.common.entity.Enum.CarStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@TableName("cars")
public class Cars {
    @TableId(type = IdType.AUTO)
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
    private LocalDateTime updatedAt;

}
