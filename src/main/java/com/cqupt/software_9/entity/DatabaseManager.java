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
@TableName(value ="databasemanager")
@Data
public class DatabaseManager implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String  databasename;
    private Integer  tablenumber;
    private String  operators;
    private String  disease;
}
