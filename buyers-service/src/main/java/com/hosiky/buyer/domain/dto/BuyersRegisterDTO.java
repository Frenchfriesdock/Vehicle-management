package com.hosiky.buyer.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BuyersRegisterDTO {

    @Email
    @NotBlank
    private String email;
    @Size(min = 6, max = 20)
    private String password;
    private String fullName;
    private String phone;

}
