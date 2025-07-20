package com.hosiky.common.entity.po;

import com.hosiky.common.entity.Enum.SellerStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Buyers {
    private Long id;
    private String email;
    private String passwordHash;
    private String fullName;
    private String phone;
    private SellerStatusEnum status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
