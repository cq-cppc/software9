package com.cqupt.software_9.dao.data;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.software_9.entity.DataManager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface DataManagerMapper extends BaseMapper<DataManager> {

    List<DataManager>  getDataManagerwithoutresult();
    void updata(String tableName);
}
