package com.freshshare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.freshshare.entity.Order;
import com.freshshare.request.AcceptOrderRequest;
import com.freshshare.request.CancelOrderRequest;
import com.freshshare.request.CompleteOrderRequest;
import com.freshshare.request.RejectOrderRequest;

import java.util.Map;

public interface OrderService extends IService<Order> {

    void acceptOrder(AcceptOrderRequest acceptOrderRequest);

    void cancelOrder(CancelOrderRequest cancelOrderRequest);

    void rejectOrder(RejectOrderRequest rejectOrderRequest);

    void completeOrder(CompleteOrderRequest completeOrderRequest);

    Map<Object,Object> getOrderListById(String businessId);

    Map<Object,Object> getOneOrderById(String orderId);
}
