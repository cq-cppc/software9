package com.cqupt.software_9.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value ="data_table")
public class dataTable implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("tablename")
    private String tablename;
    @TableField("diseasename")
    private String diseasename;
    private Integer datanumber;
    private String Operators;
    private Integer featurenumber;
    private Integer uid;
}

