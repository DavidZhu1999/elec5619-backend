package com.freshshare.service.impl;

import com.freshshare.controller.Dto.OrderDto;
import com.freshshare.mapper.POrderMapper;
import com.freshshare.service.POrderService;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class POrderServiceImpl implements POrderService {
    @Resource
    private POrderMapper POrderMapper;
    @Override
    public Integer acceptOrder(OrderDto orderDto) {
        List<Map<String,Object>>  tmp= POrderMapper.selectOrderStatus(orderDto);
        //if(tmp.get(0).entrySet()..equals("create")){//这个操作就是从create改成pending 如果之前不是create 那就无权修改

        return POrderMapper.acceptOrder(orderDto) ;


    }

    @Override
    public  List<Map<String,Object>> selectAllOrder(OrderDto orderDto) {
        return POrderMapper.selectAllOrder(orderDto);
    }

    @Override
    public List<Map<String, Object>> selectOneOrderByDetail(OrderDto orderDto) {
        return POrderMapper.selectOneOrderDetail(orderDto);
    }

    @Override
    public Integer cancelOrder(OrderDto orderDto) {
        return POrderMapper.cancelOrder(orderDto);
    }

    @Override
    public Integer completeOrder(OrderDto orderDto) {
        return POrderMapper.completeOrder(orderDto);
    }
}
