package com.freshshare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * This is the entity for staff
 */
@Data
@TableName("`staff`")
public class Staff {

    @TableId(type = IdType.ASSIGN_ID)
    private String staffId;

    @TableField("`job_id`")
    private String jobId;

    @TableField("`dept_id`")
    private String deptId;

    @TableField("`staff_username`")
    private String staffUsername;

    @TableField("`staff_email`")
    private String staffEmail;

    @TableField("`staff_password`")
    private String staffPassword;

    @TableField("`staff_firstname`")
    private String staffFirstname;

    @TableField("`staff_lastname`")
    private String staffLastname;

    @TableField("`staff_phone`")
    private String staffPhone;

    @TableField("`staff_create_time`")
    private LocalDateTime staffCreateTime;

    @TableField("`staff_delete_time`")
    private LocalDateTime staffDeleteTime;

    @TableField("`staff_status`")
    private String staffStatus;

}
