package com.cqupt.software_9.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.software_9.entity.TableManager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Mapper
public interface TableManagerMapper extends BaseMapper<TableManager> {



    List<String> findTablesHeartWithoutResult();
    List<String> findTablesWithHeartAndResult();
    List<String> findTablesCkdWithoutResult();
    List<String> findTablesWithCkdAndResult();
    List<Map<String, BigInteger>> countPredictedTargetValuesByTableName(@Param("tableName") String tableName);
    List<String> getFiledByTableName(String tableName);

    List<String> getCommentsByTableName(String tableName);

    List<Map<String, Object>> getInfoByTableName(String tableName);

    TableManager getInfoByFiled(String param);

    int getSampleCount(@Param("tableName") String tableName);

    List<Map<String, Object>> countTargetValues();
}
