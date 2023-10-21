package com.freshshare.controller;

import com.freshshare.request.*;
import com.freshshare.reponse.BusinessResponse;
import com.freshshare.reponse.BusinessResponseEnum;
import com.freshshare.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/acceptOrder")
    public BusinessResponse updateOrder(@RequestBody AcceptOrderRequest acceptOrderRequest){
        orderService.acceptOrder(acceptOrderRequest);
        return BusinessResponse.success(BusinessResponseEnum.UPDATE_ORDER_SUCCESS);
    }

    @PostMapping("/cancelOrder")
    public BusinessResponse cancelOrder(@RequestBody CancelOrderRequest cancelOrderRequest){
        orderService.cancelOrder(cancelOrderRequest);
        return BusinessResponse.success(BusinessResponseEnum.UPDATE_ORDER_SUCCESS);
    }

    @PostMapping("/rejectOrder")
    public BusinessResponse rejectOrder(@RequestBody RejectOrderRequest rejectOrderRequest){
        orderService.rejectOrder(rejectOrderRequest);
        return BusinessResponse.success(BusinessResponseEnum.UPDATE_ORDER_SUCCESS);
    }

    @PostMapping("/completeOrder")
    public BusinessResponse completeOrder(@RequestBody CompleteOrderRequest completeOrderRequest){
        orderService.completeOrder(completeOrderRequest);
        return BusinessResponse.success(BusinessResponseEnum.UPDATE_ORDER_SUCCESS);
    }

    @PostMapping("/getOrderListById")
    public BusinessResponse getOrderListById(@RequestBody GetOrderListByIdRequest getOrderListByIdRequest){
        return BusinessResponse.success(BusinessResponseEnum.GET_ORDER_LIST_SUCCESS,
                orderService.getOrderListById(getOrderListByIdRequest.getBusinessId()));
    }

    @PostMapping("/getOneOrderById")
    public BusinessResponse getOneOrderById(@RequestBody GetOneOrderByIdRequest getOneOrderByIdRequest){
        return BusinessResponse.success(BusinessResponseEnum.GET_ONE_ORDER_SUCCESS,
                orderService.getOneOrderById(getOneOrderByIdRequest.getOrderId()));
    }
}
