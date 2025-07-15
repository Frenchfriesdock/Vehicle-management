//package com.hosiky.carUser.controller;
//
//import com.hosiky.carUser.domain.dto.CarUserLoginDTO;
//import com.hosiky.carUser.domain.po.CarUser;
//import com.hosiky.carUser.domain.vo.CarUserLoginVo;
//import com.hosiky.carUser.service.ICarUserService;
//import com.hosiky.carUser.utils.JwtUtils;
//import com.hosiky.common.constant.JwtClaimsConstant;
//import com.hosiky.common.domain.Result;
//import com.hosiky.common.utils.UserContext;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@Tag(name = "车商用户管理")
//@RequestMapping("/carUser")
//@RequiredArgsConstructor
//@Slf4j
//
//public class CarUserController {
//
//    private final ICarUserService carUserService;
//
//    @Autowired
//    private JwtUtils jwtUtils;
//
//    @GetMapping("/login")
//    @Operation(summary = "车商登录")
//    public Result<CarUserLoginVo> login(/*@RequestBody CarUserLoginDTO carUserLoginDTO*/) {
//        CarUserLoginDTO carUserLoginDTO = new CarUserLoginDTO();
//        carUserLoginDTO.setCarUserName("刘鹏");
//        carUserLoginDTO.setPassword("123456");
//        carUserLoginDTO.setCarUserId(123456789);
//
//
//        log.info("登录车商的信息：{}",carUserLoginDTO);
//
//        CarUser carUser = carUserService.login(carUserLoginDTO);
//
//        //        登录成功后，生成jwt令牌
//        Map<String, Object> claims = new HashMap<>();
//        claims.put(JwtClaimsConstant.CARUSER_ID, carUser.getCarUserId());
//
//        String jwt = jwtUtils.generateJwt(claims);
//
//
//        CarUserLoginVo carUserLoginVo = CarUserLoginVo.builder()
//                .carUserId(carUser.getCarUserId())
//                .carUserName(carUser.getCarUserName())
//                .token(jwt)
//                .build();
//
//        Long currentId = UserContext.getCurrentId();
//        System.out.println(currentId);
//        return Result.success(carUserLoginVo);
//    }
//
//
//    @GetMapping("/test")
//    @Operation(summary = "测试代码")
//    public Result<String> test() {
//        Long currentId = UserContext.getCurrentId();
//        return Result.success(currentId.toString());
//    }
//
//    public int add(int a, int b) {
//        return a + b;
//    }
//}
//
