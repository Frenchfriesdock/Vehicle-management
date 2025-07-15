package com.hosiky.trade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hosiky.common.utils.UserContext;
import com.hosiky.trade.domain.po.Order;
import com.hosiky.trade.mapper.OrderMapper;
import com.hosiky.trade.service.IOrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Override
    public Order getByUserId() {
        Long userId = UserContext.getCurrentId();
        return getById(userId);
    }
}
