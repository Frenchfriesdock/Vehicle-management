package com.hosiky.trade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hosiky.trade.domain.po.Order;

public interface IOrderService extends IService<Order> {

    Order getByUserId();
}
