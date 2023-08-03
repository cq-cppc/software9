package com.cqupt.software_9.service;

import com.alibaba.fastjson.JSONObject;
import com.cqupt.software_9.service.Request.RuntimeBusCreateRequest;
import com.cqupt.software_9.service.Request.RuntimeBusCreateRequestHearts;
import com.cqupt.software_9.service.Request.RuntimeBusCreateRequestSingleHeart;
import com.cqupt.software_9.service.Response.BusResponse;
import com.cqupt.software_9.service.Response.RuntimeBusServiceResponse;
import com.cqupt.software_9.service.Response.RuntimeBusServiceResponseHearts;
import com.cqupt.software_9.service.Response.RuntimeBusServiceResponseSingleHeart;

public interface RuntimeBusService {
    BusResponse queryById(Integer id) ;


    RuntimeBusServiceResponse submitBus(RuntimeBusCreateRequest request) throws Exception;

    RuntimeBusServiceResponseSingleHeart submitBus1(RuntimeBusCreateRequestSingleHeart request)throws Exception;

    RuntimeBusServiceResponseSingleHeart submitBus2(int  patientId)throws Exception;

    RuntimeBusServiceResponseHearts submitBus3(String tableName)throws Exception;

    //批量调用ckd
    RuntimeBusServiceResponseHearts submitBus4(String tableName)throws Exception;

    JSONObject queryResult(String taskId);

    Integer offLine(Integer busId);
}
