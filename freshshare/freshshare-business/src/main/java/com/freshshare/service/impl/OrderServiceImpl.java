package com.freshshare.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.freshshare.entity.Order;
import com.freshshare.entity.OrderDetail;
import com.freshshare.exception.BusinessException;
import com.freshshare.exception.BusinessExceptionEnum;
import com.freshshare.mapper.OrderMapper;
import com.freshshare.request.AcceptOrderRequest;
import com.freshshare.request.CancelOrderRequest;
import com.freshshare.request.CompleteOrderRequest;
import com.freshshare.request.RejectOrderRequest;
import com.freshshare.service.OrderDetailService;
import com.freshshare.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private OrderDetailService orderDetailService;

//    @Override
//    public void acceptOrder(UpdateOrderRequest updateOrderRequest) {
//        if (!updateOrderRequest.getOrderStatus().equals("accepted") && !updateOrderRequest.getOrderStatus().equals("rejected")
//                && !updateOrderRequest.getOrderStatus().equals("canceled")
//        ) {
//            throw new BusinessException(BusinessExceptionEnum.UPDATE_ORDER_ERROR);
//        }
//
//        if (updateOrderRequest.getOrderStatus().equals("accepted")||updateOrderRequest.getOrderStatus().equals("rejected")) {
//            try {
//                boolean update = this.update(new LambdaUpdateWrapper<Order>()
//                        .eq(Order::getOrderId, updateOrderRequest.getOrderId())
//                        .eq(Order::getOrderStatus, "pending")
//                        .set(Order::getOrderStatus, updateOrderRequest.getOrderStatus()));
//                if (!update) {
//                    throw new BusinessException(BusinessExceptionEnum.UPDATE_COMMODITY_ERROR);
//                }
//            } catch (Exception e) {
//                throw new BusinessException(BusinessExceptionEnum.UPDATE_ORDER_ERROR);
//            }
//        } else {
//            try {
//                boolean update = this.update(new LambdaUpdateWrapper<Order>()
//                        .eq(Order::getOrderId, updateOrderRequest.getOrderId())
//                        .eq(Order::getOrderStatus, "accepted")
//                        .set(Order::getOrderStatus, updateOrderRequest.getOrderStatus()));
//                if (!update) {
//                    throw new BusinessException(BusinessExceptionEnum.UPDATE_COMMODITY_ERROR);
//                }
//            } catch (Exception e) {
//                throw new BusinessException(BusinessExceptionEnum.UPDATE_ORDER_ERROR);
//            }
//        }
//    }

    @Override
    public void acceptOrder(AcceptOrderRequest acceptOrderRequest) {
        try {
            boolean update = this.update(new LambdaUpdateWrapper<Order>()
                    .eq(Order::getOrderId, acceptOrderRequest.getOrderId())
                    .eq(Order::getOrderStatus, "pending")
                    .set(Order::getOrderStatus, "accepted"));
            if (!update) {
                throw new BusinessException(BusinessExceptionEnum.UPDATE_ORDER_ERROR);
            }
        } catch (Exception e) {
            throw new BusinessException(BusinessExceptionEnum.UPDATE_ORDER_ERROR);
        }
    }

    @Override
    public void cancelOrder(CancelOrderRequest cancelOrderRequest) {
        try {
            boolean update = this.update(new LambdaUpdateWrapper<Order>()
                    .eq(Order::getOrderId, cancelOrderRequest.getOrderId())
                    .eq(Order::getOrderStatus, "accepted")
                    .set(Order::getOrderStatus, "canceled"));
            if (!update) {
                throw new BusinessException(BusinessExceptionEnum.UPDATE_ORDER_ERROR);
            }
        } catch (Exception e) {
            throw new BusinessException(BusinessExceptionEnum.UPDATE_ORDER_ERROR);
        }
    }

    @Override
    public void rejectOrder(RejectOrderRequest rejectOrderRequest) {
        try {
            boolean update = this.update(new LambdaUpdateWrapper<Order>()
                    .eq(Order::getOrderId, rejectOrderRequest.getOrderId())
                    .eq(Order::getOrderStatus, "pending")
                    .set(Order::getOrderStatus, "rejected"));
            if (!update) {
                throw new BusinessException(BusinessExceptionEnum.UPDATE_ORDER_ERROR);
            }
        } catch (Exception e) {
            throw new BusinessException(BusinessExceptionEnum.UPDATE_ORDER_ERROR);
        }
    }

    @Override
    public void completeOrder(CompleteOrderRequest completeOrderRequest) {
        try {
            boolean update = this.update(new LambdaUpdateWrapper<Order>()
                    .eq(Order::getOrderId, completeOrderRequest.getOrderId())
                    .eq(Order::getOrderStatus, "accepted")
                    .set(Order::getOrderStatus, "completed"));
            if (!update) {
                throw new BusinessException(BusinessExceptionEnum.UPDATE_ORDER_ERROR);
            }
        } catch (Exception e) {
            throw new BusinessException(BusinessExceptionEnum.UPDATE_ORDER_ERROR);
        }
    }

    @Override
    public Map<Object, Object> getOrderListById(String businessId) {
        try {
            List<Map<String,Object>> orderList = this.listMaps(new LambdaUpdateWrapper<Order>()
                    .eq(Order::getBusinessId,businessId));

            Map<Object,Object> resultMap = new HashMap<>();
            resultMap.put("orderList",orderList);
            return resultMap;
        } catch (Exception e) {
            throw new BusinessException(BusinessExceptionEnum.GET_ORDER_LIST_ERROR);
        }
    }

    @Override
    public Map<Object, Object> getOneOrderById(String orderId) {
        List<Map<String,Object>> orderDetailList = orderDetailService.listMaps(new LambdaQueryWrapper<OrderDetail>()
        .eq(OrderDetail::getOrderId,orderId));

        Map<String,Object> order = this.getMap(new LambdaQueryWrapper<Order>()
        .eq(Order::getOrderId,orderId));

        Map<Object,Object> resultMap = new HashMap<>();
        resultMap.put("orderDetailList",orderDetailList);
        resultMap.put("order",order);
        return resultMap;
    }
}
