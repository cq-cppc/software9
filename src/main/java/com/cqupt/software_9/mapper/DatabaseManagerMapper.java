package com.cqupt.software_9.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.software_9.entity.DatabaseManager;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface DatabaseManagerMapper extends BaseMapper<DatabaseManager> {
    List<DatabaseManager> getall();
}
