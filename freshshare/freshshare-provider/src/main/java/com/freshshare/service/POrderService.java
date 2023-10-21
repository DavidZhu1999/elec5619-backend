package com.freshshare.service;

import com.freshshare.controller.Dto.OrderDto;

import java.util.List;
import java.util.Map;

public interface POrderService {
    Integer acceptOrder(OrderDto orderDto);
    List<Map<String,Object>> selectAllOrder();
    List<Map<String,Object>> selectOneOrderByDetail(OrderDto orderDto);
    Integer  cancelOrder(OrderDto orderDto);
    Integer completeOrder(OrderDto orderDto);
}
