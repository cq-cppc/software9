package com.cqupt.software_9.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.software_9.entity.StasticOne;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface StasticOneMapper extends BaseMapper<StasticOne> {
    Integer getDieaseCount();

    Integer getSampleCount(String tableName);

    List<String> getTableNames();

    Date getEarlyDate(String tableName);

    Date getLastDate(String tableName);

    Integer getTaskCount();

    List<String> getUserBuildTableNames();
}
