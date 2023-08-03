package com.cqupt.software_9.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadResult {
    private String tableName;
    private List<String> tableHeaders;
    private List<Map<String, Object>> res;
    private Exception e;

    // 构造函数、getter和setter方法省略
}
