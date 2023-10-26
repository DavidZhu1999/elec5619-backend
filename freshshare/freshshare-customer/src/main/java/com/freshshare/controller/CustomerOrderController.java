package com.freshshare.controller;

import com.freshshare.request.*;
import com.freshshare.response.CustomerResponse;
import com.freshshare.response.CustomerResponseEnum;
import com.freshshare.service.CustomerOrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @description: CustomerOrderController
 */
@RestController
@RequestMapping("/customer/order")
@CrossOrigin
public class CustomerOrderController {

    @Resource
    private CustomerOrderService customerOrderService;

    /**
     * Add order
     * @param addOrderRequest
     * @return
     */
    @PostMapping("/addOrder")
    public CustomerResponse addOrder(@RequestBody AddOrderRequest addOrderRequest){
        String orderId = customerOrderService.addOrder(addOrderRequest);
        return new CustomerResponse(CustomerResponseEnum.ADD_ORDER_SUCCESS,orderId);
    }

    /**
     * get all order
     * @param getAllOrdersRequest
     * @return
     */
    @PostMapping("/getOrders")
    public CustomerResponse getOrders(@RequestBody GetAllOrdersRequest getAllOrdersRequest){
        return new CustomerResponse(CustomerResponseEnum.GET_ORDERS_SUCCESS,customerOrderService.getOrders(getAllOrdersRequest));
    }

    /**
     * get order detail
     * @param getOrderDetailRequest
     * @return
     */
    @PostMapping("/getOrderDetail")
    public CustomerResponse getOrderDetail(@RequestBody GetOrderDetailRequest getOrderDetailRequest){
        return new CustomerResponse(CustomerResponseEnum.GET_ORDER_DETAIL_SUCCESS,customerOrderService.getOrderDetail(getOrderDetailRequest));
    }

    /**
     * get order address
     * @param getOrderAddressRequest
     * @return
     */
    @PostMapping("/getOrderAddress")
    public CustomerResponse getOrderAddress(@RequestBody GetOrderAddressRequest getOrderAddressRequest){
        return new CustomerResponse(CustomerResponseEnum.GET_ORDER_ADDRESS_SUCCESS,customerOrderService.getOrderAddress(getOrderAddressRequest.getOrderId()));
    }

    /**
     * cancel order
     * @param cancelOrderRequest
     * @return
     */
    @PostMapping("/cancelOrder")
    public CustomerResponse cancelOrder(@RequestBody CancelOrderRequest cancelOrderRequest){
        customerOrderService.cancelOrder(cancelOrderRequest);
        return new CustomerResponse(CustomerResponseEnum.CANCEL_ORDER_SUCCESS);
    }

    /**
     * complete order
     * @param completeOrderRequest
     * @return
     */
    @PostMapping("/completeOrder")
    public CustomerResponse completeOrder(@RequestBody CompleteOrderRequest completeOrderRequest){
        customerOrderService.completeOrder(completeOrderRequest);
        return new CustomerResponse(CustomerResponseEnum.COMPLETE_ORDER_SUCCESS);
    }


}
