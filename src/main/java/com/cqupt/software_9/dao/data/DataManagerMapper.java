package com.cqupt.software_9.dao.data;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.software_9.entity.DataManager;
import com.cqupt.software_9.entity.TableManager;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Mapper
public interface DataManagerMapper extends BaseMapper<DataManager> {

    List<DataManager>  getDataManagerwithoutresult();
    void updata(String tableName);
    void insertDataManager(DataManager dataManager);
}
