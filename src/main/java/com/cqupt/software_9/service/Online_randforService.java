package com.cqupt.software_9.service;

import com.alibaba.fastjson.JSONObject;
import com.cqupt.software_9.service.Request.RuntimeBusCreateRequest;
import com.cqupt.software_9.service.Request.RuntimeBusCreateRequestSingleHeart;
import com.cqupt.software_9.service.Request.RuntimeBusCreateRequest_online_RandFor;
import com.cqupt.software_9.service.Response.*;

public interface Online_randforService {
   OnlineServiceResponse online_Randfor(RuntimeBusCreateRequest_online_RandFor request)throws Exception;
}
