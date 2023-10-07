package com.cqupt.software_9.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "PatientHeartDisease")
public class PatientHeartDisease {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer patientId;
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


    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("patientId", patientId);
        map.put("age", age);
        map.put("sex", sex);
        map.put("cp", cp);
        map.put("trestbps", trestbps);
        map.put("chol", chol);
        map.put("fbs", fbs);
        map.put("restecg", restecg);
        map.put("thalach", thalach);
        map.put("exang", exang);
        map.put("oldpeak", oldpeak);
        map.put("slope", slope);
        map.put("ca", ca);
        map.put("thal", thal);
        // Add any additional fields you have in the PatientHeartDisease class
        return map;
    }

}
