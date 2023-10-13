package com.cqupt.software_9.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HeartManager {
    private String symp;
    private String name;
    private Integer value;
    private String rangeValue;
    private String unit;
}
