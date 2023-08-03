package com.cqupt.software_9.service.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RuntimeBusServiceResponseHearts {

    private List<String> res;

    public String getOutputTableName() {
        if (res != null && !res.isEmpty()) {
            // 假设列表中的第一个元素是"Output table name"的值
            return res.get(0);
        }
        return null; // 或者根据需求返回适当的默认值
    }


}