package com.cqupt.software_9.service.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RuntimeBusCreateRequest_online_RandFor {

    private String tablename;
    private String targetcolumn;
    private String[] fea;
    private Integer n_estimators;
    private Integer min_samples_leaf;
    private Integer min_samples_split;
    private String max_features;
    private boolean bootstrap;


}


