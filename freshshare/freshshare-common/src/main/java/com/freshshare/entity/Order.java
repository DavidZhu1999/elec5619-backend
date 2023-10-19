package com.freshshare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("`transaction`")
public class Order {

    @TableId(type = IdType.ASSIGN_ID)
    private String orderId;

    @TableField("`customer_id`")
    private String customerId;

    @TableField("`business_id`")
    private String businessId;

    @TableField("`order_status`")
    private String orderStatus;

    @TableField("`order_create_time`")
    private LocalDateTime orderCreateTime;

    @TableField("`order_end_time`")
    private LocalDateTime orderEndTime;

    @TableField("`order_price`")
    private String orderPrice;

    @TableField("`order_edit_time`")
    private LocalDateTime orderEditTime;

}
