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
@TableName(value = "Symp")
public class Symp {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String code;
    private String name;
    private Boolean isGet;
    private String partName;
    private String partCode;
}
