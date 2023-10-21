package com.freshshare.controller.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class OrderReq {
    private String order_id;
    private String customer_id;
    private BigDecimal order_price;
    private LocalDateTime order_create_time;
    private LocalDateTime order_end_time;
    private LocalDateTime order_edit_time;
    private String order_status;
    private String business_id;
   // private String satokenCustomer;
}
