package com.cqupt.software_9.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.ResultMap;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "PatientHeartAll")
@Data
public class PatientHeartAll {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer patientId;
    private String name;
    private Integer age;
    private String address;
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
    private Integer ca;
    private Integer thal;
    private LocalDateTime createTime;

    public static List<String> getAllPropertyNames() {
        List<String> propertyNames = new ArrayList<>();
        Field[] fields = PatientHeartAll.class.getDeclaredFields();
        for (Field field : fields) {
            propertyNames.add(field.getName());
        }
        return propertyNames;
    }

}
