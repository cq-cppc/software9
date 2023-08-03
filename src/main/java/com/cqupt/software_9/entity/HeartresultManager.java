package com.cqupt.software_9.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "heartresult")
public class HeartresultManager {
    private String predictedTtarget;
    private Integer age;
    private Integer sex;
    private Integer cp;
    private Integer trestbps;
    private Integer chol;
    private Integer fbs;
    private Integer restecg;
    private Integer thalach;
    private Integer exang;
    private float oldpeak;
    private Integer slope;
    private float ca;
    private float thal;

}
