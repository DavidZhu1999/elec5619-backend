package com.freshshare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("`customer`")
public class Customer {

    @TableId(type = IdType.ASSIGN_ID)
    private Long customerId;

    @TableField("`customer_username`")
    private String customerUsername;

    @TableField("`customer_email`")
    private String customerEmail;

    @TableField("`customer_password`")
    private String customerPassword;

    @TableField("`customer_firstname`")
    private String customerFirstname;

    @TableField("`customer_lastname`")
    private String customerLastname;

    @TableField("`customer_phone`")
    private String customerPhone;

    @TableField("`customer_status`")
    private String customerStatus;

    @TableField("`customer_create_time`")
    private LocalDateTime customerCreateTime;

    @TableField("`customer_deleted_time`")
    private LocalDateTime customerDeletedTime;

    @TableField("`customer_address`")
    private String customerAddress;

    @TableField("`customer_geocode`")
    private String customerGeocode;

    @TableField("`customer_postcode`")
    private String customerPostcode;
}
