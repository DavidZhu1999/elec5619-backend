package com.freshshare.controller.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProfileDto {

    private String business_id;
    private String business_username;
    private String business_email;
    private String business_password;
    private String business_shopname;
    private String business_firstname;
    private String business_lastname;
    private String business_phone;
    private String business_address;
    private String business_postcode;
    private String business_state;
    private LocalDateTime business_time_from;
    private LocalDateTime business_time_to;
    private String business_status;
    private String business_geocode;
    private LocalDateTime business_register_time;
    private byte[] business_license_picture;
    private int business_is_open;
    private String satokenBusiness;
}
