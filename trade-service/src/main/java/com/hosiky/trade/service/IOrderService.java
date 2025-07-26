package com.hosiky.trade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hosiky.common.entity.po.Orders;
import com.hosiky.trade.domain.dto.OrderCreateDTO;
import com.hosiky.trade.domain.dto.OrderUpdateStatusDTO;
import com.hosiky.trade.domain.vo.OrderVO;
import jakarta.validation.Valid;

import java.util.List;

public interface IOrderService extends IService<Orders> {

    void createOrder(@Valid OrderCreateDTO createDTO);

    void updateOrderStatus(@Valid OrderUpdateStatusDTO updateDTO);

    OrderVO getOrderVOById(Long orderId);

    List<OrderVO> getOrdersByUserId(Long email);

    List<OrderVO> getOrdersBySellerId(Long sellerId);
}
