package com.cqupt.software_9.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.software_9.entity.OnlineTask;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface OnlineTaskMapper extends BaseMapper<OnlineTask> {
    void addOnlineTask(OnlineTask onlineTask);
    List<OnlineTask> upall();
    List<OnlineTask> getAllById(Integer uid);
}
