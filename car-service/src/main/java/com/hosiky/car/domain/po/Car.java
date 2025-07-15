package com.hosiky.car.domain.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("car")
public class Car {

    @TableId(value = "carId")
    private String carId;

    private String carName;

    private String state;

    private String typeId;

    private Integer carUserId;

    private String carUserName;

    private String image;

    private boolean isDeleted;

    private String description;

    private double carPrice;

    private LocalDateTime createTime;
}
