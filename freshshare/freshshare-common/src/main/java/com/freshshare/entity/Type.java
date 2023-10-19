package com.freshshare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("`type`")
public class Type {

    @TableId(type = IdType.ASSIGN_ID)
    private String typeId;

    @TableField("`type_name`")
    private String typeName;
}
