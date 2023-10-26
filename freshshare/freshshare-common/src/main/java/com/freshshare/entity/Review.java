package com.freshshare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * This is the entity for review
 */
@Data
@TableName("`review`")
public class Review {

    @TableId(type = IdType.ASSIGN_ID)
    private String reviewId;

    @TableField("`order_id`")
    private String orderId;

    @TableField("`review_content`")
    private String reviewContent;

    @TableField("`review_score`")
    private double reviewScore;

}
