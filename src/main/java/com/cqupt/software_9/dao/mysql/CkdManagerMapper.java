package com.cqupt.software_9.dao.mysql;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.software_9.entity.DataManager;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface CkdManagerMapper extends BaseMapper<DataManager> {

    List<DataManager>  getckdManagerwithoutresult();
    void updata(String tableName);
}
