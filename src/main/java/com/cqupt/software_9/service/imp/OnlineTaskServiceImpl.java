package com.cqupt.software_9.service.imp;

import com.cqupt.software_9.mapper.OnlineTaskMapper;
import com.cqupt.software_9.entity.OnlineTask;
import com.cqupt.software_9.service.Adapter.OnlineTaskServiceAdapter;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
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
