package com.cqupt.software_9.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("algorithm")
public class publicAl {
    private String name;
    private String introduction;
    private String myparameter;
    public Map<String, Object> getJsonAsMap() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(myparameter, new TypeReference<Map<String, Object>>() {});
    }


}
