package com.cqupt.software_9.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.software_9.entity.OnlineTask;
import java.util.List;

public interface OnlineTaskService extends IService<OnlineTask> {
   String addTask(OnlineTask task);
   List<OnlineTask> upall();
}
