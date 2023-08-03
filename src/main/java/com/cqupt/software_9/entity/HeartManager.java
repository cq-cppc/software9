package com.cqupt.software_9.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "heartManager")
@Data
public class HeartManager {
    private String symp;
    private String name;
    private Integer value;
    private String rangeValue;
    private String unit;


}
