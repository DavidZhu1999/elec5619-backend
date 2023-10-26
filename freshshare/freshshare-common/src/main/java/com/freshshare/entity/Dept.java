package com.freshshare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * This is the entity for dept
 */
@Data
@TableName("`dept`")
public class Dept {

    @TableId(type = IdType.ASSIGN_ID)
    private String deptId;

    @TableField("`dept_name`")
    private String deptName;
}
