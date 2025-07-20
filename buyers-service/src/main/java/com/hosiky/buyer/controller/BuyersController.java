package com.hosiky.buyer.controller;


import com.hosiky.buyer.domain.dto.BuyersRegisterDTO;
import com.hosiky.buyer.domain.vo.BuyersRegisterVo;
import com.hosiky.buyer.service.IBuyerService;
import com.hosiky.common.domain.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "buyer管理")
@RestController
@RequestMapping("/api/buyer")
@Slf4j
@CrossOrigin

@RequiredArgsConstructor
public class BuyersController {

    private final IBuyerService buyerService;

    @PostMapping("/register")
    public Result<BuyersRegisterVo> register(@Valid @RequestBody BuyersRegisterDTO buyersRegisterDTO) {

        return Result.success(buyerService.register(buyersRegisterDTO));
    }

}
