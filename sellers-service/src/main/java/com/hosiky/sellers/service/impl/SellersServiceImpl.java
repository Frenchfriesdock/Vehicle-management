package com.hosiky.sellers.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hosiky.common.entity.Enum.SellerStatusEnum;
import com.hosiky.sellers.domain.dto.SellerLoginDTO;
import com.hosiky.sellers.domain.dto.SellerRegisterDTO;
import com.hosiky.sellers.domain.dto.SellerUpdateDTO;
import com.hosiky.sellers.domain.vo.SellerLoginVO;
import com.hosiky.sellers.domain.vo.SellerProfileVO;
import com.hosiky.sellers.mapper.SellersMapper;
import com.hosiky.sellers.properties.JwtProperties;
import com.hosiky.sellers.service.ISellersService;
import com.hosiky.sellers.utils.JwtUtils;
import com.hosiky.common.entity.po.Sellers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SellersServiceImpl extends ServiceImpl<SellersMapper, Sellers> implements ISellersService {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public void register(SellerRegisterDTO sellerRegisterDTO) {
        if(lambdaQuery().eq(Sellers::getEmail,sellerRegisterDTO.getEmail()).exists()){
            throw new RuntimeException("邮箱已经被注册");
        }

        Sellers sellers = new Sellers();
        BeanUtils.copyProperties(sellerRegisterDTO,sellers);

        sellers.setPasswordHash(BCrypt.hashpw(sellerRegisterDTO.getPasswordHash(),BCrypt.gensalt()));

        sellers.setStatus(SellerStatusEnum.ACTIVE);
        sellers.setCreatedAt(LocalDateTime.now());
        sellers.setUpdatedAt(LocalDateTime.now());

        this.save(sellers);

    }

    @Override
    public SellerLoginVO login(SellerLoginDTO sellerLoginDTO) {
//        1 查账号
        Sellers seller = lambdaQuery()
                .eq(Sellers::getEmail, sellerLoginDTO.getEmail())
                .eq(Sellers::getStatus, SellerStatusEnum.ACTIVE)
                //.oneOpt()：返回 Optional<Sellers>，避免空指针异常
                .oneOpt()
                .orElseThrow(() -> new RuntimeException("账号不存在或者已经禁用"));

//      2  检验密码
        if (sellerLoginDTO.getPassword() == null ||
                !BCrypt.checkpw(sellerLoginDTO.getPassword(), seller.getPasswordHash())) {
            throw new RuntimeException("密码错误");
        }

//      3 生成jwt 携带的信息
        Map<String,Object> claims = Map.of(
                "sub",seller.getEmail(),
                "sellerId",seller.getId(),
                "type","SELLER"
        );

        String token = jwtUtils.generateJwt(claims);

        LocalDateTime expireAt = LocalDateTime.now().plusSeconds(jwtProperties.getTtl() / 1000);


        boolean updated = lambdaUpdate()
                .eq(Sellers::getId, seller.getId())
                .set(Sellers::getToken, token)
                .set(Sellers::getTokenExpireAt, expireAt)
                .update();

        if(!updated){
            throw new RuntimeException("登录信息更新失败");
        }

//      组装 vo
        SellerProfileVO profileVO = new SellerProfileVO();
        BeanUtils.copyProperties(seller, profileVO);

        SellerLoginVO sellerLoginVO = new SellerLoginVO();
        sellerLoginVO.setToken(token);
        sellerLoginVO.setProfileVO(profileVO);

        return sellerLoginVO;
    }

    @Override
    public IPage<SellerProfileVO> getAllSellers(int current, int size) {
//        创建分页对象
        Page<Sellers> page = new Page<>(current, size);

        Page<Sellers> sellersPage = lambdaQuery()
                .select(Sellers::getId, Sellers::getEmail, Sellers::getCompanyName,
                        Sellers::getContactName, Sellers::getStatus)
                .page(page);

        // 使用 IPage.convert 转换 VO
        return sellersPage.convert(seller -> {
            SellerProfileVO vo = new SellerProfileVO();
            BeanUtils.copyProperties(seller, vo);
            return vo;
        });
    }

    @Override
    public SellerProfileVO getBySellerId(Long id) {
        Sellers sellers = this.getById(id);

        SellerProfileVO profileVO = new SellerProfileVO();
        BeanUtils.copyProperties(sellers, profileVO);

        return profileVO;
    }

    @Override
    public SellerProfileVO updateSellers(BigInteger id, SellerUpdateDTO sellerUpdateDTO) {
        boolean update = lambdaUpdate()
                .eq(Sellers::getId, id)
                .set(sellerUpdateDTO.getCompanyName() != null, Sellers::getCompanyName, sellerUpdateDTO.getCompanyName())
                .set(sellerUpdateDTO.getContactName() != null, Sellers::getContactName, sellerUpdateDTO.getContactName())
                .set(sellerUpdateDTO.getPhone() != null, Sellers::getPhone, sellerUpdateDTO.getPhone())
                .update();

        if(!update){
            throw new RuntimeException("更新失败，sellers不存在");
        }

        Sellers sellers = getById(id);
        SellerProfileVO profileVO = new SellerProfileVO();
        BeanUtils.copyProperties(sellers, profileVO);
        return profileVO;

    }


}
