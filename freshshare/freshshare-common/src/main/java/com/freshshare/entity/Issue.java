package com.freshshare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("`issue`")
public class Issue {

    @TableId(type = IdType.ASSIGN_ID)
    private String issueId;

    @TableField("`staff_id`")
    private String staffId;

    @TableField("`order_id`")
    private String orderId;

    @TableField("`issue_content`")
    private String issueContent;

    @TableField("`issue_status`")
    private String issueStatus;

    @TableField("`issue_create_time`")
    private LocalDateTime issueCreateTime;

    @TableField("`issue_end_time`")
    private LocalDateTime issueEndTime;

    @TableField("customer_id")
    private String customerId;
}
