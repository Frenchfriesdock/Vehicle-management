package com.hosiky.trade.controller;

import com.hosiky.trade.domain.po.Order;
import com.hosiky.trade.service.IOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Tag(name = "订单管理")
@RequiredArgsConstructor
@Slf4j


public class OrderController {
    private final IOrderService orderService;

    @Operation(summary = "查询订单")
    @GetMapping("{ordersId}")
    public Order queryOrder(@PathVariable Long ordersId) {
        return orderService.getById(ordersId);
    }

    @Operation(summary = "根据userId查询订单")
    @GetMapping
    public Order getOrder(){
        return orderService.getByUserId();
    }

}
