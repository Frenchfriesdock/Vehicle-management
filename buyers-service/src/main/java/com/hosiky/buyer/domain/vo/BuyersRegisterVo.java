package com.hosiky.buyer.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BuyersRegisterVo {
    private Long id;
    private String email;
    private String fullName;
    private String phone;
    private String status;
    private LocalDateTime createdAt;
}
