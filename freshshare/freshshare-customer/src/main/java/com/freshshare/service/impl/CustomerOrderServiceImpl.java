package com.freshshare.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.freshshare.entity.Order;
import com.freshshare.entity.OrderDetail;
import com.freshshare.exception.CustomerException;
import com.freshshare.exception.CustomerExceptionEnum;
import com.freshshare.mapper.CustomerOrderMapper;
import com.freshshare.request.*;
import com.freshshare.service.CustomerOrderDetailService;
import com.freshshare.service.CustomerOrderService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: CustomerOrderServiceImpl
 */
@Service
public class CustomerOrderServiceImpl extends ServiceImpl<CustomerOrderMapper, Order> implements CustomerOrderService {

    @Resource
    private CustomerOrderMapper customerOrderMapper;

    @Resource
    private CustomerOrderDetailService customerOrderDetailService;

    /**
     * add order
     * @param addOrderRequest
     * @return
     */
    @Override
    public String addOrder(AddOrderRequest addOrderRequest) {
        Order newOrder = new Order();
        newOrder.setCustomerId(addOrderRequest.getCustomerId());
        newOrder.setBusinessId(addOrderRequest.getBusinessId());
        newOrder.setOrderStatus("created");
        newOrder.setOrderPrice(addOrderRequest.getOrderPrice());
        newOrder.setOrderCreateTime(LocalDateTime.now());
        newOrder.setOrderEditTime(LocalDateTime.now());
        newOrder.setOrderStatus("pending");
        try {
            this.save(newOrder);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (AddOrderRequest.CartItem cartItem : addOrderRequest.getCartItems()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(newOrder.getOrderId());
            orderDetail.setCommodityId(cartItem.getCommodityId());
            orderDetail.setDetailNumBuy(cartItem.getDetailNumBuy());
            orderDetail.setDetailPrice(cartItem.getDetailPrice());
            customerOrderDetailService.save(orderDetail);
        }
        return newOrder.getOrderId();
    }

    /**
     * get all orders
     * @param getAllOrdersRequest
     * @return
     */
    @Override
    public Map<Object, Object> getOrders(GetAllOrdersRequest getAllOrdersRequest) {
//        List<Map<String,Object>> orders = this.listMaps(new LambdaQueryWrapper<Order>()
//                .eq(Order::getCustomerId,getAllOrdersRequest.getCustomerId()));

        List<Map<String,Object>> orders1 = customerOrderMapper.selectOrderWithCustomerAndBusinessInfo(getAllOrdersRequest.getCustomerId());

        Map<Object,Object> result = new HashMap<>();
        result.put("orders",orders1);
        return result;
    }

    /**
     * get order detail
     * @param getOrderDetailRequest
     * @return
     */
    @Override
    public Map<Object, Object> getOrderDetail(GetOrderDetailRequest getOrderDetailRequest) {
        Map<String,Object> orderDetail = customerOrderMapper.selectOrderDetailWithCustomerAndBusinessInfo(getOrderDetailRequest.getOrderId());

        List<Map<String, Object>> commodities = customerOrderMapper.selectOrderDetailWithCommodityInfo(getOrderDetailRequest.getOrderId());
        Map<Object,Object> result = new HashMap<>();
        result.put("orderDetail",orderDetail);
        result.put("commodities",commodities);
        return result;
    }

    /**
     * get order address
     * @param orderId
     * @return
     */
    @Override
    public Map<Object, Object> getOrderAddress(String orderId) {
        Map<String,Object> bussinessId = this.getMap(new LambdaQueryWrapper<Order>()
                .select(Order::getBusinessId)
                .eq(Order::getOrderId,orderId));

        Map<String,Object> bussinessAddress = customerOrderMapper.selectOrderAddressByBusinessId(bussinessId.get("business_id").toString());

        Map<Object,Object> result = new HashMap<>();
        result.put("address",bussinessAddress);
        return result;
    }

    /**
     * cancel order
     * @param cancelOrderRequest
     */
    @Override
    public void cancelOrder(CancelOrderRequest cancelOrderRequest) {
        try {
            boolean update = this.update(new LambdaUpdateWrapper<Order>()
                    .eq(Order::getOrderId, cancelOrderRequest.getOrderId())
                    .eq(Order::getOrderStatus, "pending")
                    .set(Order::getOrderStatus, "canceled"));
            if (!update) {
                throw new CustomerException(CustomerExceptionEnum.UPDATE_COMMODITY_ERROR);
            }
        } catch (Exception e) {
            throw new CustomerException(CustomerExceptionEnum.UPDATE_COMMODITY_ERROR);
        }
    }

    /**
     * complete order
     * @param completeOrderRequest
     */
    @Override
    public void completeOrder(CompleteOrderRequest completeOrderRequest) {
        try {
            boolean update = this.update(new LambdaUpdateWrapper<Order>()
                    .eq(Order::getOrderId, completeOrderRequest.getOrderId())
                    .eq(Order::getOrderStatus, "accepted")
                    .set(Order::getOrderStatus, "completed"));
            if (!update) {
                throw new CustomerException(CustomerExceptionEnum.UPDATE_COMMODITY_ERROR);
            }
        } catch (Exception e) {
            throw new CustomerException(CustomerExceptionEnum.UPDATE_COMMODITY_ERROR);
        }
    }


}
