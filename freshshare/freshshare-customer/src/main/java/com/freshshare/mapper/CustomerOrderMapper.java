package com.freshshare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.freshshare.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface CustomerOrderMapper extends BaseMapper<Order> {

    /**
     * Select all orders with customer and business info
     * @param customerId
     * @return
     */
    @Select("SELECT b.business_id, b.business_shopname, t.order_id, t.order_status, t.order_price, r.review_score \n" +
            "FROM transaction t\n" +
            "JOIN customer c ON t.customer_id = c.customer_id\n" +
            "JOIN business b ON t.business_id = b.business_id\n" +
            "LEFT JOIN review r ON t.order_id = r.order_id\n" +
            "WHERE c.customer_id = #{customerId}" +
            "ORDER BY t.order_create_time DESC;")
    List<Map<String,Object>> selectOrderWithCustomerAndBusinessInfo(String customerId);

    /**
     * Select order detail with customer and business info
     * @param orderId
     * @return
     */
    @Select("SELECT t.*, c.*, b.*, r.* " +
            "FROM `transaction` t " +
            "JOIN `customer` c ON t.customer_id = c.customer_id " +
            "JOIN `business` b ON t.business_id = b.business_id " +
            "LEFT JOIN `review` r ON t.order_id = r.order_id " +
            "WHERE t.order_id = #{orderId}")
    Map<String,Object> selectOrderDetailWithCustomerAndBusinessInfo(String orderId);

    /**
     * Select order detail with commodity info
     * @param orderId
     * @return
     */
    @Select("SELECT * FROM order_detail od " +
            "JOIN commodity cm ON od.commodity_id = cm.commodity_id " +
            "WHERE od.order_id = #{orderId}")
    List<Map<String,Object>> selectOrderDetailWithCommodityInfo(String orderId);

    /**
     * Select order address by business id
     * @param businessId
     * @return
     */
    @Select("SELECT business_address FROM `business` WHERE business_id = #{businessId}")
    Map<String,Object> selectOrderAddressByBusinessId(String businessId);

}
