package com.hosiky.buyer.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hosiky.buyer.domain.dto.BuyersRegisterDTO;
import com.hosiky.buyer.domain.vo.BuyersRegisterVo;
import com.hosiky.buyer.mapper.BuyersMapper;
import com.hosiky.buyer.service.IBuyerService;
import com.hosiky.common.entity.Enum.SellerStatusEnum;
import com.hosiky.common.entity.po.Buyers;
import com.hosiky.common.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class BuyersServiceImpl extends ServiceImpl<BuyersMapper, Buyers> implements IBuyerService {

    private final BuyersMapper buyersMapper;

    @Override
    public BuyersRegisterVo register(BuyersRegisterDTO buyersRegisterDTO) {
        // 检查邮箱是否已注册
        if (lambdaQuery().eq(Buyers::getEmail, buyersRegisterDTO.getEmail()).exists()) {
            throw new RuntimeException("邮箱已经被注册");
        }

        // 检查密码是否为空
        if (buyersRegisterDTO.getPasswordHash() == null) {
            throw new IllegalArgumentException("密码不能为空");
        }

        Buyers buyers = new Buyers();
        BeanUtils.copyProperties(buyersRegisterDTO, buyers);

        // 加密密码
        buyers.setPasswordHash(BCrypt.hashpw(buyersRegisterDTO.getPasswordHash(), BCrypt.gensalt()));
        buyers.setStatus(SellerStatusEnum.ACTIVE);
        buyers.setCreatedAt(LocalDateTime.now());
        buyers.setUpdatedAt(LocalDateTime.now());

        buyersMapper.insert(buyers);
        return BeanUtils.copyProperties(buyers, BuyersRegisterVo.class);
    }

}
