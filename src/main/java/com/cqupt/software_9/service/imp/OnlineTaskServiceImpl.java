package com.cqupt.software_9.service.imp;

import com.cqupt.software_9.dao.mysql.OnlineTaskMapper;
import com.cqupt.software_9.entity.OnlineTask;
import com.cqupt.software_9.service.Adapter.OnlineTaskServiceAdapter;
import com.cqupt.software_9.service.Adapter.Online_randforServiceAdapter;
import com.cqupt.software_9.service.Request.RuntimeBusCreateRequest_online_RandFor;
import com.cqupt.software_9.service.Request.RuntimeTaskRequest;
import com.cqupt.software_9.service.Response.OnlineServiceResponse;
import com.cqupt.software_9.service.Response.RuntimeTaskResponse;
import com.cqupt.software_9.service.RuntimeTaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class OnlineTaskServiceImpl extends OnlineTaskServiceAdapter {
    @Resource
    private OnlineTaskMapper onlineTaskMapper;

    @Override
    public String addTask(OnlineTask task) {
         onlineTaskMapper.addOnlineTask(task);
         return "新增成功";
    }

    @Override
    public List<OnlineTask> upall() {
        return onlineTaskMapper.upall();
    }
}
