package com.cqupt.software_9.service.Adapter;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.software_9.entity.dataTable;

import com.cqupt.software_9.service.DataTableManagerService;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class DataTableManagerServiceAdapter implements DataTableManagerService {


    @Override
    public List<dataTable> upalldata() {
        return null;
    }

    @Override
    public dataTable upbyid(Integer id) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getInfoByTableName(String tableName) {
        return null;
    }

    @Override
    public String insert(dataTable datatable) {
        return null;
    }

    @Override
    public Integer findTargetColumnIndex(String tablename, String targetcolumn) {
        return null;
    }


    @Override
    public boolean saveBatch(Collection<dataTable> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<dataTable> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<dataTable> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(dataTable entity) {
        return false;
    }

    @Override
    public dataTable getOne(Wrapper<dataTable> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<dataTable> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<dataTable> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<dataTable> getBaseMapper() {
        return null;
    }

    @Override
    public Class<dataTable> getEntityClass() {
        return null;
    }
    @Override
    public void updateDataTable(String table_name,String disease) {

    }

    @Override
    public List<String> upname() {
        return null;
    }

    @Override
    public void deletename(String tablename) {

    }

    @Override
    public Boolean deletebyid(Integer id) {
        return null;
    }

    @Override
    public void updata(dataTable a) {

    }

    @Override
    public List<dataTable> upallDataByUid(Integer uid) {
        return null;
    }

    @Override
    public String getNameById(int id) {
        return null;
    }

    @Override
    public void deleteTable(String tablename) {

    }

    @Override
    public void deleteTableResult(String tableresult) {

    }
}
