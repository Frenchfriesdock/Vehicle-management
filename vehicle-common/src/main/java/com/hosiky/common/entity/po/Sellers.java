package com.hosiky.common.entity.po;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hosiky.common.entity.Enum.SellerStatusEnum;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@TableName("sellers")
public class Sellers {

    private BigInteger id;
    private String email;
    private String passwordHash;
    private String companyName;
    private String contactName;
    private String phone;
    @TableField("status")
    @EnumValue          // 告诉 MP 用枚举
    private SellerStatusEnum status;
    private String token;
    private String tokenExpireAt;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
