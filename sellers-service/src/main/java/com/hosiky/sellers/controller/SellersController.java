package com.hosiky.sellers.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hosiky.sellers.domain.dto.SellerLoginDTO;
import com.hosiky.sellers.domain.dto.SellerRegisterDTO;
import com.hosiky.sellers.domain.dto.SellerUpdateDTO;
import com.hosiky.sellers.domain.vo.SellerLoginVO;
import com.hosiky.sellers.domain.vo.SellerProfileVO;
import com.hosiky.sellers.service.ISellersService;
import com.hosiky.sellers.utils.JwtUtils;
import com.hosiky.common.domain.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@Tag(name = "Seller管理")
@RestController
@RequestMapping("/api/seller")
@Slf4j
@CrossOrigin(maxAge = 3600)

@RequiredArgsConstructor
public class SellersController {


    private ISellersService sellersService;

    private JwtUtils jwtUtils;

    /**
     * TODO
     * 可以优化一下，优化一个邮箱验证
     * 还可以加上redis操作
     * @param sellerRegisterDTO
     * @return
     */
    @Operation(summary = "添加Sellers")
    @PostMapping("/register")
    public Result<SellerProfileVO> register(@RequestBody SellerRegisterDTO sellerRegisterDTO) {
        sellersService.register(sellerRegisterDTO);
        return Result.success();
    }

    @Operation(summary = "登录Sellers")
    @GetMapping("/login")
    public Result<SellerLoginVO> login(@RequestBody SellerLoginDTO sellerLoginDTO) {
        SellerLoginVO vo = sellersService.login(sellerLoginDTO);
        return Result.success(vo);
    }

    @Operation(summary = "getAllSellers")
    @GetMapping("/all")
    public Result<IPage<SellerProfileVO>> getAllSellers(@RequestParam(defaultValue = "1") int current,
                                                        @RequestParam(defaultValue = "10") int size) {
        IPage<SellerProfileVO> sellerProfileVOList = sellersService.getAllSellers(current,size);
        return Result.success(sellerProfileVOList);
    }

    @Operation(summary = "获取单个Sellers信息")
    @GetMapping("/id")
    public Result<SellerProfileVO> getSellerById(Long id) {

        SellerProfileVO sellerLoginVO = sellersService.getBySellerId(id);
        return Result.success(sellerLoginVO);
    }

    @Operation(summary = "删除sellers信息")
    @DeleteMapping("/{id}")
    public Result deleteSellerById(@PathVariable Long id) {

        sellersService.removeById(id);
        return Result.success("根据id删除seller成功");
    }

    @PutMapping("/{id}")
    public Result<SellerProfileVO> updateSellerById(@PathVariable BigInteger id,
                                                    @RequestBody @Valid SellerUpdateDTO sellerUpdateDTO) {
        SellerProfileVO sellerProfileVO = sellersService.updateSellers(id,sellerUpdateDTO);
        return Result.success(sellerProfileVO);
    }
}
