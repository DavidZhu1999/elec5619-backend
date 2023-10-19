package com.freshshare.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("`business`")
public class Business {

    @TableId(type = IdType.ASSIGN_ID)
    private String businessId;

    @TableField("`business_username`")
    private String businessUsername;

    @TableField("`business_email`")
    private String businessEmail;

    @TableField("`business_password`")
    private String businessPassword;

    @TableField("`business_shopname`")
    private String businessShopname;

    @TableField("`business_firstname`")
    private String businessFirstname;

    @TableField("`business_lastname`")
    private String businessLastname;

    @TableField("`business_phone`")
    private String businessPhone;

    @TableField("`business_address`")
    private String businessAddress;

    @TableField("`business_postcode`")
    private String businessPostcode;

    @TableField("`business_state`")
    private String businessState;

    @TableField("`business_time_from`")
    private LocalDateTime businessTimeFrom;

    @TableField("`business_time_to`")
    private LocalDateTime businessTimeTo;

    @TableField("`business_status`")
    private String businessStatus;

    @TableField("`business_geocode`")
    private String businessGeocode;  // NOTE: If you use an ORM that supports JSON, you might want to use a proper data type.

    @TableField("`business_register_time`")
    private LocalDateTime businessRegisterTime;

    @TableField("`business_license_picture`")
    private byte[] businessLicensePicture;

    @TableField("`business_is_open`")
    private int businessIsOpen;

}
