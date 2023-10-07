package com.cqupt.software_9.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value ="data_table")
public class dataTable implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("table_desc")
    private String table_desc;
    @TableField("table_name")
    private String table_name;
    private Integer featurenumber;
    private Integer samplesize;
    private LocalDateTime createtime;
    @TableField("tableType")
    private String table_type;
    private String disease;
    private String creator;
}

