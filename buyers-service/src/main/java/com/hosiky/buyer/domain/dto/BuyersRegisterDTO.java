package com.hosiky.buyer.domain.dto;

import lombok.Data;

@Data
public class BuyersRegisterDTO {


    private String email;
    private String password;
    private String fullName;
    private String phone;

}
