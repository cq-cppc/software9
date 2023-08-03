package com.cqupt.software_9.service.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OnlineServiceResponse {

    private List<String> res;
    private Exception e;

}