package com.freshshare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.freshshare.entity.Order;
import org.apache.ibatis.annotations.Select;

public interface OrderMapper extends BaseMapper<Order> {


    @Select("select * from order_detail where order_id = #{orderId}")
    Order getOrderDetailsByOrderId(String orderId);
}
