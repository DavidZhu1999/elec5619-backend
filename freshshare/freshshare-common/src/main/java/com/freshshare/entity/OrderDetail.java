package com.freshshare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("`order_detail`")
public class OrderDetail {

    @TableId(type = IdType.ASSIGN_ID)
    private String orderDetailId;

    @TableField("`order_id`")
    private String orderId;

    @TableField("`commodity_id`")
    private String commodityId;

    @TableField("`detail_num_buy`")
    private String detailNumBuy;

    @TableField("`detail_price`")
    private String detailPrice;

}
