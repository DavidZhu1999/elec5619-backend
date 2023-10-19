package com.freshshare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("`commodity`")
public class Commodity {

    @TableId(type = IdType.ASSIGN_ID)
    private String commodityId;

    @TableField("`business_id`")
    private String businessId;

    @TableField("`commodity_name`")
    private String commodityName;

    @TableField("`commodity_price`")
    private String commodityPrice;

    @TableField("`commodity_status`")
    private String commodityStatus;

    @TableField("`commodity_upload_time`")
    private LocalDateTime commodityUploadTime;

    @TableField("`commodity_down_time`")
    private LocalDateTime commodityDownTime;

    @TableField("`commodity_num`")
    private String commodityNum;

    @TableField("`commodity_sell_num`")
    private String commoditySellNum;

    @TableField("`commodity_edit_time`")
    private LocalDateTime commodityEditTime;

    @TableField("`type_id`")
    private String typeId;



}
