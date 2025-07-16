package com.hosiky.sellers.domain.vo;

import lombok.Data;

@Data
public class SellerLoginVO {
    private String token;
    private SellerProfileVO profileVO;
}
