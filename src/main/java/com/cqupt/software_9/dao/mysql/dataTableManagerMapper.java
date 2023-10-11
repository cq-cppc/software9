package com.cqupt.software_9.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.software_9.entity.dataTable;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;
@Mapper

public interface dataTableManagerMapper extends BaseMapper<dataTable> {


            List<dataTable> upalldata();
            List<String> upname();
            dataTable upbyid(Integer id);
            void save(dataTable datatable);
            List<Map<String, Object>> getInfoByTableName(String tableName);
            Integer findTargetColumnIndex( String tablename, String targetcolumn);
            void insertDataTable(dataTable dataTable);
            void deletetablename(String tablename);
            void deletebyid(Integer id);
            void updata(dataTable a);
            List<dataTable> upallDataByUid(Integer uid);
            String getNameById(int id);
            void deleteTable(String tablename);
            void deleteTableResult(String tableresult);
}
