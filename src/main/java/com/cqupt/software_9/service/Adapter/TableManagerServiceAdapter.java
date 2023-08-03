package com.cqupt.software_9.service.Adapter;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.software_9.entity.TableManager;
import com.cqupt.software_9.service.TableManagerService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class TableManagerServiceAdapter  implements TableManagerService {
    @Override
    public List<TableManager> getAllData() {
        return null;
    }

    @Override
    public List<String> getFiledByTableName(String tableName) {
        return null;
    }

    @Override
    public List<String> getCommentsByTableName(String tableName) {
        return null;
    }

    @Override
    public  List<Map<String, Object>> getInfoByTableName(String tableName) {
        return null;
    }

    @Override
    public boolean[] getInfoByFiled(String param) {
        return new boolean[0];
    }

    @Override
    public List<TableManager> getAllTableManagersByFiledName(List<String> tableNames) {
        return null;
    }

    @Override
    public List<String> findTablesHeartWithoutResult() {
        return null;
    }

    @Override
    public List<String> findTablesWithHeartAndResult() {
        return null;
    }

    @Override
    public List<String> findTablesCkdWithoutResult() {
        return null;
    }

    @Override
    public List<String> findTablesWithCkdAndResult() {
        return null;
    }

    @Override
    public List<Map<String, Object>> findTablesHeartWithoutResultWithCount() {
        return null;
    }

    @Override
    public List<Map<String, Object>> findTablesckdWithoutResultWithCount() {
        return null;
    }

    @Override
    public boolean saveBatch(Collection<TableManager> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<TableManager> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<TableManager> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(TableManager entity) {
        return false;
    }

    @Override
    public TableManager getOne(Wrapper<TableManager> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<TableManager> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<TableManager> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<TableManager> getBaseMapper() {
        return null;
    }

    @Override
    public Class<TableManager> getEntityClass() {
        return null;
    }
}
