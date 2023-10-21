package com.freshshare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.freshshare.entity.Order;
import com.freshshare.request.*;

import java.util.Map;

public interface CustomerOrderService extends IService<Order> {
    String addOrder(AddOrderRequest addOrderRequest);

    Map<Object,Object> getOrders(GetAllOrdersRequest getAllOrdersRequest);

    Map<Object,Object> getOrderDetail(GetOrderDetailRequest getOrderDetailRequest);

    Map<Object,Object> getOrderAddress(String orderId);

    void cancelOrder(CancelOrderRequest cancelOrderRequest);

    void completeOrder(CompleteOrderRequest completeOrderRequest);
}
