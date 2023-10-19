package com.freshshare.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.freshshare.entity.OrderDetail;
import com.freshshare.mapper.OrderDetailMapper;
import com.freshshare.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
