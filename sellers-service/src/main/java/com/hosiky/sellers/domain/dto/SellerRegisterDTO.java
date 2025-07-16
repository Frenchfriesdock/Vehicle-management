package com.hosiky.sellers.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SellerRegisterDTO {

    @Email
    private String email;
    @NotBlank
    @Size(min = 6, max = 32)
    private String passwordHash;
    @NotBlank
    private String companyName;
    private String contactName;

    private String phone;
}
