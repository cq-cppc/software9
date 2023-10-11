package com.cqupt.software_9.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.software_9.entity.dataTable;
import java.util.List;
import java.util.Map;

public interface DataTableManagerService extends IService<dataTable> {


          List<dataTable> upalldata();
          dataTable upbyid(Integer id);
          List<Map<String, Object>> getInfoByTableName(String tableName);
          String insert(dataTable datatable);
          Integer findTargetColumnIndex(String tablename, String targetcolumn);
          void updateDataTable(String table_name,String disease);
          List<String> upname();
          void deletename(String tablename);
          Boolean deletebyid(Integer id);
          void updata(dataTable a);
          List<dataTable> upallDataByUid(Integer uid);
          String getNameById(int id);
          void deleteTable(String tablename);
          void deleteTableResult(String tableresult);
}
