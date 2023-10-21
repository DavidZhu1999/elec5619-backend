package com.freshshare.controller.Dto;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data

public class OrderDto {
    private String order_id;
    private String customer_id;
    private BigDecimal order_price;
    private LocalDateTime order_create_time;
    private LocalDateTime order_end_time;
    private LocalDateTime order_edit_time;
    private String order_status;
    private String business_id;
    private String customer_username;
    private String business_username;


}
