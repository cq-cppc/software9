package com.cqupt.software_9.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableManagerDTO {
    private String tableName;
    private List<TableHeaderDTO> tableHeaders;
    private Integer userId;


}
