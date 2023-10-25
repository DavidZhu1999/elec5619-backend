package com.freshshare.controller.Dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("`commodity`")
public class ProductDto {
    @TableField("`commodity_id`")
    private String commodity_id;
    @TableField("`business_id`")
    private String business_id;//Find items based on the business id
    @TableField("`commodity_name`")
    private String commodity_name;
    @TableField("`commodity_price`")
    private Double commodity_price;
    @TableField("`commodity_num`")
    private Integer commodity_num;
    @TableField("`commodity_sell_num`")
    private Integer commodity_sell_num;
    @TableField("`commodity_upload_time`")
    private LocalDateTime commodity_upload_time;
    @TableField("`commodity_down_time`")
    private LocalDateTime commodity_down_time;
    @TableField("`commodity_edit_time`")
    private LocalDateTime commodity_edit_time;
    @TableField("`commodity_status`")
    private String commodity_status;
    @TableField("`type_id`")
    private  String type_id;


}
