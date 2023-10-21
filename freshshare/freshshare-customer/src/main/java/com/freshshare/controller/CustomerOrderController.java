package com.freshshare.controller;

import com.freshshare.request.*;
import com.freshshare.response.CustomerResponse;
import com.freshshare.response.CustomerResponseEnum;
import com.freshshare.service.CustomerOrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer/order")
@CrossOrigin
public class CustomerOrderController {

    @Resource
    private CustomerOrderService customerOrderService;

    @PostMapping("/addOrder")
    public CustomerResponse addOrder(@RequestBody AddOrderRequest addOrderRequest){
        String orderId = customerOrderService.addOrder(addOrderRequest);
        return new CustomerResponse(CustomerResponseEnum.ADD_ORDER_SUCCESS,orderId);
    }

    @PostMapping("/getOrders")
    public CustomerResponse getOrders(@RequestBody GetAllOrdersRequest getAllOrdersRequest){
        return new CustomerResponse(CustomerResponseEnum.GET_ORDERS_SUCCESS,customerOrderService.getOrders(getAllOrdersRequest));
    }

    @PostMapping("/getOrderDetail")
    public CustomerResponse getOrderDetail(@RequestBody GetOrderDetailRequest getOrderDetailRequest){
        return new CustomerResponse(CustomerResponseEnum.GET_ORDER_DETAIL_SUCCESS,customerOrderService.getOrderDetail(getOrderDetailRequest));
    }

    @PostMapping("/getOrderAddress")
    public CustomerResponse getOrderAddress(@RequestBody GetOrderAddressRequest getOrderAddressRequest){
        return new CustomerResponse(CustomerResponseEnum.GET_ORDER_ADDRESS_SUCCESS,customerOrderService.getOrderAddress(getOrderAddressRequest.getOrderId()));
    }

    @PostMapping("/cancelOrder")
    public CustomerResponse cancelOrder(@RequestBody CancelOrderRequest cancelOrderRequest){
        customerOrderService.cancelOrder(cancelOrderRequest);
        return new CustomerResponse(CustomerResponseEnum.CANCEL_ORDER_SUCCESS);
    }

    @PostMapping("/completeOrder")
    public CustomerResponse completeOrder(@RequestBody CompleteOrderRequest completeOrderRequest){
        customerOrderService.completeOrder(completeOrderRequest);
        return new CustomerResponse(CustomerResponseEnum.COMPLETE_ORDER_SUCCESS);
    }


}
