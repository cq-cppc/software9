package com.cqupt.software_9.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value ="data_manager")
public class dataTable implements Serializable {
    @TableId(type = IdType.AUTO)
    @Id
    private Integer id;
    @TableField("tablename")
    private String tablename;
    @TableField("diseasename")
    private String diseasename;
    private Integer datanumber;
    private String tabletype;
    private String uploadmethod;
    private String affiliationdatabase;
    private Timestamp loadtime;
    private String chinesename;
    private String Operators;
    private Integer featurenumber;
    private Integer isProjection;
    private Integer uid;
}

