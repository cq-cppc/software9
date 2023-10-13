package com.cqupt.software_9.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.software_9.common.TableManagerDTO;
import com.cqupt.software_9.entity.tTableField;
import com.cqupt.software_9.entity.tTableManager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface tTableManagerMapper extends BaseMapper<tTableManager> {
    List<String> getTableNames(Integer uid);
    List<tTableField> getFiledNamesByTableName(String table_name);
    void updateFieldValues(String table_name, String fieldName, String demography, String physiological, String sociology, String label);
    void insertTableManager(TableManagerDTO tableManagerDTO);
    void deletebyname(String tableName);
}
