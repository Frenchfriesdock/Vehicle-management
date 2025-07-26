package com.hosiky.common.entity.po;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class CarImages {
    private BigInteger id;
    private BigInteger carId;
    private String url;
//    用于图片排序（例如显示顺序），数值越小越靠前。
    private Integer sort;
    private LocalDateTime createdAt;
}
