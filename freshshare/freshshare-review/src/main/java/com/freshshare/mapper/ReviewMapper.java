package com.freshshare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.freshshare.entity.Review;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface ReviewMapper extends BaseMapper<Review> {


    @Select("SELECT `order_id` FROM `order` WHERE `business_id` = #{businessId}")
    List<String> getOrdersByBusinessId(String businessId);


    @Update("UPDATE `transaction` SET `order_status` = 'commented' WHERE `order_id` = #{orderId}")
    void updateOrderStatus(String orderId);
}
