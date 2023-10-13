package com.cqupt.software_9.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class trainAl implements Serializable {
    private String tableName;
    private String target;
    private String[] fea;
    private List<Map<String, Map<String, String>>> completeParameter;

}
