package com.freshshare.mapper;

import com.freshshare.controller.Dto.OrderDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface POrderMapper {
    Integer acceptOrder(OrderDto orderDto);
    List<Map<String,Object>>  selectOrderStatus(OrderDto orderDto);
    List<Map<String,Object>>  selectAllOrder();
    List<Map<String,Object>> selectOneOrderDetail(OrderDto orderDto);
    Integer  cancelOrder(OrderDto orderDto);
    Integer completeOrder(OrderDto orderDto);
}
