package com.cqupt.software_9.common;

import com.cqupt.software_9.entity.dataTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadResult {
    private String tableName;
    private Integer code;
    private List<String> tableHeaders;
    private List<dataTable> res;
    private Exception e;
}
