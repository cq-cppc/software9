package com.cqupt.software_9.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnlineRequest {

    private String  taskName;
    private String  leader;
    private String   disease;
    private String   model;
    private String   remark;

    private String[]   feature;
    private String[] para;
    private String[] paraValue;

    private String   targetcolumn;
    private String   modelpath;
    private String[]  result;

}
