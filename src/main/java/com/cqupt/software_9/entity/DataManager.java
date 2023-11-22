package com.cqupt.software_9.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@TableName(value ="data_manager")
@Data
public class DataManager implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer datanumber;
    private String  operators;
    private String  chinesename;
    private Integer featurenumber;
    private int isProjection;
    private String tablename;
    private String diseasename;
    private String affiliationdatabase;
    private String tabletype;
    private String uploadmethod;
    private Integer uid;


}
