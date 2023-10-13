package com.cqupt.software_9.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class tTableField implements Serializable {
    private String field_name;
    private String  is_demography;
    private String  is_physiological;
    private String  is_sociology;
    private String  is_label;
}
