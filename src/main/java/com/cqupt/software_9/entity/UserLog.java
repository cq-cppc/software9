package com.cqupt.software_9.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName(value ="user_log")
@Data
public class UserLog {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer uid;

    private String username;

    private Date opTime;

    private String opType;

    private static final long serialVersionUID = 1L;
}
