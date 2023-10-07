package com.cqupt.software_9.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.software_9.entity.TableManager;
import java.util.List;
import java.util.Map;

public interface TableManagerService extends IService<TableManager> {

    List<TableManager> getAllData();

    List<String> getFiledByTableName(String tableName);

    List<String> getCommentsByTableName(String tableName);

//    List<Object> getInfoByTableName(String tableName);
    List<Map<String, Object>> getInfoByTableName(String tableName);

    boolean[] getInfoByFiled(String param);

    List<TableManager> getAllTableManagersByFiledName(List<String> tableNames);

     List<String> findTablesHeartWithoutResult() ;
     List<String> findTablesWithHeartAndResult() ;

    List<String> findTablesCkdWithoutResult() ;
    List<String> findTablesWithCkdAndResult() ;
    public List<Map<String, Object>> findTablesHeartWithoutResultWithCount();
    public List<Map<String, Object>> findTablesckdWithoutResultWithCount();

}