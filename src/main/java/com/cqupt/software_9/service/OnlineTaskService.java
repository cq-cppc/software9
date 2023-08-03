package com.cqupt.software_9.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.software_9.entity.OnlineTask;
import com.cqupt.software_9.service.Request.RuntimeBusCreateRequest_online_RandFor;
import com.cqupt.software_9.service.Response.OnlineServiceResponse;

import java.util.List;

public interface OnlineTaskService extends IService<OnlineTask> {
   String addTask(OnlineTask task);
   List<OnlineTask> upall();
}
