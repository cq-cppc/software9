package com.cqupt.software_9.service.Adapter;

import com.alibaba.fastjson.JSONObject;
import com.cqupt.software_9.service.Request.RuntimeBusCreateRequest;
import com.cqupt.software_9.service.Request.RuntimeBusCreateRequestHearts;
import com.cqupt.software_9.service.Request.RuntimeBusCreateRequestSingleHeart;
import com.cqupt.software_9.service.Response.BusResponse;
import com.cqupt.software_9.service.Response.RuntimeBusServiceResponse;
import com.cqupt.software_9.service.Response.RuntimeBusServiceResponseHearts;
import com.cqupt.software_9.service.Response.RuntimeBusServiceResponseSingleHeart;
import com.cqupt.software_9.service.RuntimeBusService;

public class RuntimeBusServiceAdapter implements RuntimeBusService {
    @Override
    public BusResponse queryById(Integer id) {
        return null;
    }

    @Override
    public RuntimeBusServiceResponse submitBus(RuntimeBusCreateRequest request) throws Exception {
        return null;
    }

    @Override
    public RuntimeBusServiceResponseSingleHeart submitBus1(RuntimeBusCreateRequestSingleHeart request) throws Exception {
        return null;
    }

    @Override
    public RuntimeBusServiceResponseSingleHeart submitBus2(int patientId) throws Exception {
        return null;
    }

    @Override
    public RuntimeBusServiceResponseHearts submitBus3(String tableName) throws Exception {
        return null;
    }

    @Override
    public RuntimeBusServiceResponseHearts submitBus4(String tableName) throws Exception {
        return null;
    }


    @Override
    public JSONObject queryResult(String taskId) {
        return null;
    }

    @Override
    public Integer offLine(Integer busId) {
        return null;
    }
}
