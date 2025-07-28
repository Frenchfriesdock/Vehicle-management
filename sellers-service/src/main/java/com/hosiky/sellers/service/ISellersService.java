package com.hosiky.sellers.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hosiky.sellers.domain.dto.SellerLoginDTO;
import com.hosiky.sellers.domain.dto.SellerRegisterDTO;
import com.hosiky.sellers.domain.dto.SellerUpdateDTO;
import com.hosiky.sellers.domain.vo.SellerLoginVO;
import com.hosiky.common.entity.po.Sellers;
import com.hosiky.sellers.domain.vo.SellerProfileVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.math.BigInteger;


public interface ISellersService extends IService<Sellers> {

    void register(SellerRegisterDTO sellerRegisterDTO);

    SellerLoginVO login(SellerLoginDTO sellerLoginDTO);

    IPage<SellerProfileVO> getAllSellers(int current, int size);

    SellerProfileVO getBySellerId(Long id);

    SellerProfileVO updateSellers(BigInteger id, @Valid SellerUpdateDTO sellerUpdateDTO);

    void sendCode(@NotBlank String email);
}
