package com.cqupt.software_9.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class DatabaseManager implements Serializable {
    private String diseaseName;
    private Integer num;
}
