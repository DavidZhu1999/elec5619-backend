package com.freshshare.controller.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShopReq {
    private String business_id;
    private String business_username;
    private String business_email;
    private String business_password;
    private String business_name;
    private String business_phone;
    private String business_address;
    private String business_postcode;
    private String business_state;
    private LocalDateTime business_time_from;
    private LocalDateTime business_time_to;
    private String business_status;
    private LocalDateTime business_register_time;
    private String satokenBusiness;
}
