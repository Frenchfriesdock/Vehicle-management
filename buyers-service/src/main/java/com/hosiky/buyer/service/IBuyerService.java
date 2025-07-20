package com.hosiky.buyer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hosiky.buyer.domain.dto.BuyersRegisterDTO;
import com.hosiky.buyer.domain.vo.BuyersRegisterVo;
import com.hosiky.common.entity.po.Buyers;
import jakarta.validation.Valid;

public interface IBuyerService extends IService<Buyers> {

    BuyersRegisterVo register(@Valid BuyersRegisterDTO buyersRegisterDTO);
}
