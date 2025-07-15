package com.hosiky.carUser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hosiky.carUser.domain.dto.CarUserDTO;
import com.hosiky.carUser.domain.dto.CarUserLoginDTO;
import com.hosiky.carUser.domain.po.CarUser;
import com.hosiky.carUser.domain.vo.CarUserVO;

public interface ICarUserService extends IService<CarUser> {

    CarUser login(CarUserLoginDTO carUserLoginDTO);
}
