package com.hosiky.carUser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hosiky.carUser.domain.dto.CarUserDTO;
import com.hosiky.carUser.domain.dto.CarUserLoginDTO;
import com.hosiky.carUser.domain.po.CarUser;
import com.hosiky.carUser.mapper.CarUserMapper;
import com.hosiky.carUser.service.ICarUserService;
import com.hosiky.common.constant.MessageConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
@RequiredArgsConstructor
public class CarUserServiceImpl extends ServiceImpl<CarUserMapper, CarUser> implements ICarUserService {


    @Override
    public CarUser login(CarUserLoginDTO carUserDTO) {

        Integer carUserId = carUserDTO.getCarUserId();
        String password = carUserDTO.getPassword();

        CarUser carUser = getById(carUserId);

//        判断账号是否存在
        if(carUser == null) {
            throw new RuntimeException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

//        密码对比
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!password.equals(carUser.getPassword())) {
            throw new RuntimeException(MessageConstant.PASSWORD_ERROR);
        }
        return carUser;
    }
}
