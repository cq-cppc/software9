package com.cqupt.software_9.dao.ckd;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.software_9.entity.TableManager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Mapper
public interface CkdTableManagerMapper extends BaseMapper<TableManager> {



    List<Map<String, Object>> getInfoByTableName(String tableName);

}
