package com.hosiky.trade.controller;

import com.hosiky.common.domain.Result;
import com.hosiky.trade.domain.dto.OrderCreateDTO;
import com.hosiky.trade.domain.dto.OrderUpdateStatusDTO;
import com.hosiky.trade.domain.vo.OrderVO;
import com.hosiky.trade.service.IOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "订单管理")
@RequiredArgsConstructor
@Slf4j


public class OrderController {

    private final IOrderService orderService;

    @PostMapping
    @Operation(summary = "创建订单")
    public Result createOrder(@RequestBody @Valid OrderCreateDTO createDTO) {
        orderService.createOrder(createDTO);
        return Result.success();
    }

    @PutMapping("/status")
    @Operation(summary = "更新订单状态")
    public Result updateOrderStatus(@RequestBody @Valid OrderUpdateStatusDTO updateDTO) {
        orderService.updateOrderStatus(updateDTO);
        return Result.success("修改成功");
    }


    @GetMapping("/{orderId}")
    @Operation(summary = "获取订单详情")
    public Result<OrderVO> getOrder(@PathVariable Long orderId) {
        return Result.success(orderService.getOrderVOById(orderId));
    }

    @GetMapping("/user/{buyerId}")
    @Operation(summary = "获取用户订单列表")
    public Result<List<OrderVO>> getOrdersByUserId(@PathVariable Long buyerId) {

        return Result.success(orderService.getOrdersByUserId(buyerId));
    }

    @GetMapping("/seller/{sellerId}")
    @Operation(summary = "获取卖家订单列表")
    public Result<List<OrderVO>> getOrdersBySellerId(@PathVariable Long sellerId) {

        return Result.success(orderService.getOrdersBySellerId(sellerId));
    }

    @DeleteMapping("/{orderId}")
    @Operation(summary = "删除订单")
    public Result deleteOrder(@PathVariable Long orderId) {

        return Result.success(orderService.removeById(orderId));
    }
}
