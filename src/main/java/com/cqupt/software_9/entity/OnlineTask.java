package com.cqupt.software_9.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("onlinetask")
public class OnlineTask implements Serializable {

    @TableId(value = "taskId", type = IdType.AUTO)
    private Integer taskId;
    private String  taskName;
    private String  leader;
    private LocalDateTime createtime;
    private String   disease;
    private String   model;
    private String   remark;
    private String   feature;
    private String   parameters;
    private String   parametersValue;
    private String   targetcolumn;
    private String   modelpath;
    private String   result;


}
