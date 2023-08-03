package com.cqupt.software_9.service.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RuntimeBusCreateRequestSingleHeart {
    // private String icdCode;
    // private Integer algorithmId;
    //  private int busId;
    //  private String busName;
    //  private String busDescription;
    //  private String label;
    //  private int tableId;
    //  private int factorCount;
    private String age;
    private String sex;
    private String cp;
    private String trestbps;
    private String chol;
    private String fbs;
    private String restecg;
    private String thalach;
    private String exang;
    private String oldpeak;
    private String slope;
    private String ca;
    private String thal;



}
