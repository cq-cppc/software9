package com.cqupt.software_9.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "User")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String sex;
    private Integer age;
    private String Phonenumber;
    private Integer isadmin;
}