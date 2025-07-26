package com.hosiky.trade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hosiky.common.entity.Enum.OrderStatusEnum;
import com.hosiky.common.entity.po.Orders;
import com.hosiky.common.utils.BeanUtils;
import com.hosiky.common.utils.UserContext;
import com.hosiky.trade.domain.dto.OrderCreateDTO;
import com.hosiky.trade.domain.dto.OrderUpdateStatusDTO;
import com.hosiky.trade.domain.vo.OrderVO;
import com.hosiky.trade.mapper.OrderMapper;
import com.hosiky.trade.service.IOrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements IOrderService {


    @Override
    public void createOrder(OrderCreateDTO createDTO) {

        String orderNo = generateOrderNo();

        Orders orders = new Orders();
        orders.setOrderNo(orderNo);
        orders.setStatus(OrderStatusEnum.UNPAID);
        BeanUtils.copyProperties(createDTO, orders);
        this.save(orders);
    }

    @Override
    public void updateOrderStatus(OrderUpdateStatusDTO updateDTO) {
        Long orderId = updateDTO.getOrderId();

        OrderStatusEnum status = updateDTO.getStatus();

        Orders orders = this.getById(orderId);
        if(orders == null){
            throw new RuntimeException("订单不存在");
        }

//        转态流转校验
        validateStatusTransition(orders.getStatus(),status);

        orders.setStatus(status);
        this.updateById(orders);
    }



    @Override
    public OrderVO getOrderVOById(Long orderId) {
        Orders orders = this.getById(orderId);
        if(orders == null){
            return null;
        }

        return BeanUtils.copyProperties(orders, OrderVO.class);
    }

    @Override
    public List<OrderVO> getOrdersByUserId(Long buyerId) {
        List<Orders> ordersList = lambdaQuery()
                .eq(Orders::getBuyerId, buyerId)
                .list();

        return ordersList.stream()
                .map(this::convertToVo)
                .collect(Collectors.toList());
    }


    @Override
    public List<OrderVO> getOrdersBySellerId(Long sellerId) {
        List<Orders> ordersList = lambdaQuery()
                .eq(Orders::getSellerId, sellerId)
                .list();

        return ordersList.stream()
                .map(this::convertToVo)
                .collect(Collectors.toList());
    }


    /**
     * 生成订单号
     * @return
     */
    private String generateOrderNo() {

        return "ORD" +  System.currentTimeMillis() + UUID.randomUUID().toString().substring(0,8);
    }

    /**
     * 转态流校验
     * 逻辑方面可以自定义的
     * @param
     * @param
     */

    private void validateStatusTransition(OrderStatusEnum currentStatus, OrderStatusEnum newStatus) {
        // 简单示例，实际业务中可能有更复杂的状态流转规则
        switch (currentStatus) {
            case UNPAID:
                if (newStatus != OrderStatusEnum.PAID && newStatus != OrderStatusEnum.CANCEL) {
                    throw new RuntimeException("非法状态转换：从" + currentStatus.getDesc() + "只能转换到已支付或已取消");
                }
                break;
            case PAID:
                if (newStatus != OrderStatusEnum.SHIPPED) {
                    throw new RuntimeException("非法状态转换：从" + currentStatus.getDesc() + "只能转换到已发货");
                }
                break;
            case SHIPPED:
                if (newStatus != OrderStatusEnum.DONE) {
                    throw new RuntimeException("非法状态转换：从" + currentStatus.getDesc() + "只能转换到已完成");
                }
                break;
            case DONE:
            case CANCEL:
                throw new RuntimeException(currentStatus.getDesc() + "状态不可再变更");
            default:
                throw new RuntimeException("未知状态：" + currentStatus);
        }
    }

    private OrderVO convertToVo(Orders orders) {

        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(orders, orderVO);
        return orderVO;
    }

}
