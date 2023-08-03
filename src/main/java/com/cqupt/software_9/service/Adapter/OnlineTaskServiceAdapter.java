package com.cqupt.software_9.service.Adapter;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.software_9.entity.OnlineTask;
import com.cqupt.software_9.service.OnlineTaskService;
import com.cqupt.software_9.service.Online_randforService;
import com.cqupt.software_9.service.Request.RuntimeBusCreateRequest_online_RandFor;
import com.cqupt.software_9.service.Response.OnlineServiceResponse;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class OnlineTaskServiceAdapter implements OnlineTaskService {


    @Override
    public String addTask(OnlineTask task) {
        return null;
    }

    @Override
    public List<OnlineTask> upall() {
        return null;
    }

    @Override
    public boolean saveBatch(Collection<OnlineTask> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<OnlineTask> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<OnlineTask> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(OnlineTask entity) {
        return false;
    }

    @Override
    public OnlineTask getOne(Wrapper<OnlineTask> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<OnlineTask> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<OnlineTask> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<OnlineTask> getBaseMapper() {
        return null;
    }

    @Override
    public Class<OnlineTask> getEntityClass() {
        return null;
    }
}
