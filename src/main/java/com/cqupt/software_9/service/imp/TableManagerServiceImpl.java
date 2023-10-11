package com.cqupt.software_9.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqupt.software_9.dao.mysql.TableManagerMapper;
import com.cqupt.software_9.entity.TableManager;
import com.cqupt.software_9.service.Adapter.TableManagerServiceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TableManagerServiceImpl extends TableManagerServiceAdapter {

    @Autowired
    private TableManagerMapper tableManagerMapper;

    @Override
    public List<TableManager> getAllData() {


        List<TableManager> tableManager = tableManagerMapper.selectList(null);


        return tableManager;
    }

    @Override
    public List<String> getFiledByTableName(String tableName) {


        List<String> tableNames = tableManagerMapper.getFiledByTableName(tableName);

        return tableNames;
    }

    @Override
    public List<String> getCommentsByTableName(String tableName) {

        List<String> comments = tableManagerMapper.getCommentsByTableName(tableName);

        return comments;
    }

    @Override
    public  List<Map<String, Object>> getInfoByTableName(String tableName) {



        List<Map<String,Object>> res = tableManagerMapper.getInfoByTableName(tableName);

        return res;
    }

    @Override
    public boolean[] getInfoByFiled(String param) {


        boolean r1 = false,r2 = false, r3 = false;
        TableManager tableManager = tableManagerMapper.getInfoByFiled(param);

        if (tableManager != null){

            r1 = Objects.equals(tableManager.getIsDemography(), "1");
            r2 = Objects.equals(tableManager.getIsPhysiological(), "1");
            r3 = Objects.equals(tableManager.getIsSociology(), "1");
        }

        return new boolean[]{r1,r2,r3};
    }

    @Override
    public List<TableManager> getAllTableManagersByFiledName(List<String> tableNames) {

        List<TableManager> res= new ArrayList<>();

        for (int i = 0; i< tableNames.size() ; i++) {
            QueryWrapper queryWrapper = new QueryWrapper();

            queryWrapper.eq("field_name", tableNames.get(i));
            TableManager tableManager = tableManagerMapper.selectOne(queryWrapper);
            res.add(tableManager);
        }

        return res;
    }

    public List<String> findTablesHeartWithoutResult() {
        return tableManagerMapper.findTablesHeartWithoutResult();
    }
    public List<String> findTablesWithHeartAndResult() {
        return tableManagerMapper.findTablesWithHeartAndResult();
    }

    public List<String> findTablesCkdWithoutResult() {
        return tableManagerMapper.findTablesCkdWithoutResult();
    }
    public List<String> findTablesWithCkdAndResult() {
        return tableManagerMapper.findTablesWithCkdAndResult();
    }



    public List<Map<String, Object>> findTablesHeartWithoutResultWithCount() {
        List<Map<String, Object>> tablesWithCount = new ArrayList<>();

        List<String> tableNames = findTablesHeartWithoutResult();
        for (String tableName : tableNames) {
            int count = tableManagerMapper.getSampleCount(tableName);

            Map<String, Object> tableInfo = new HashMap<>();
            tableInfo.put("tableName", tableName);
            tableInfo.put("count", count);

            tablesWithCount.add(tableInfo);
        }

        return tablesWithCount;
    }

    public List<Map<String, Object>> findTablesckdWithoutResultWithCount() {
        List<Map<String, Object>> tablesWithCount = new ArrayList<>();

        List<String> tableNames = findTablesCkdWithoutResult();
        for (String tableName : tableNames) {
            int count = tableManagerMapper.getSampleCount(tableName);

            Map<String, Object> tableInfo = new HashMap<>();
            tableInfo.put("tableName", tableName);
            tableInfo.put("count", count);

            tablesWithCount.add(tableInfo);
        }

        return tablesWithCount;
    }
}
