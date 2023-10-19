package com.freshshare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("`job`")
public class Job {

    @TableId(type = IdType.ASSIGN_ID)
    private String jobId;

    @TableField("`job_name`")
    private String jobName;

}
