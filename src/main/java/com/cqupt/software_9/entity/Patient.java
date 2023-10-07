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
@TableName(value = "Patient")
public class Patient {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer patientId;
    private String name;
    private Integer age;
    private String gender;
    private String address;


    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("patientId", patientId);
        map.put("name", name);
        map.put("age", age);
        map.put("gender", gender);
        map.put("address", address);
        // Add any additional fields you have in the Patient class
        return map;
    }
}