package com.cqupt.software_9.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName(value ="user")
@Data
public class User {
    @TableId
    private Integer uid;

    private String username;

    private String password;

    private Integer role;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
