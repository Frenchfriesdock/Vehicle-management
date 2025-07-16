package com.hosiky.sellers.domain.vo;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.hosiky.common.entity.Enum.SellerStatusEnum;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * 管理员端的查看看信息
 */
@Data
public class SellerProfileVO {

    private BigInteger id;
    private String email;
    private String companyName;
    private String contactName;
    private String phone;
    @TableField("status")
    @EnumValue
    private SellerStatusEnum status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
