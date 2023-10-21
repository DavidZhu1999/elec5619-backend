package com.freshshare.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.freshshare.entity.OrderDetail;
import com.freshshare.mapper.CustomerOrderDetailMapper;
import com.freshshare.service.CustomerOrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderDetailServiceImpl extends ServiceImpl<CustomerOrderDetailMapper, OrderDetail> implements CustomerOrderDetailService {
}
