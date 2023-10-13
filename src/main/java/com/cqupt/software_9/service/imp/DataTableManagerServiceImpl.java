package com.cqupt.software_9.service.imp;


import com.cqupt.software_9.dao.mysql.dataTableManagerMapper;
import com.cqupt.software_9.entity.dataTable;
import com.cqupt.software_9.service.Adapter.DataTableManagerServiceAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class DataTableManagerServiceImpl extends DataTableManagerServiceAdapter {
    @Value("${spring.datasource.mysql.url}")
    private String dbUrl;
    @Value("${spring.datasource.mysql.username}")
    private String dbUsername;
    @Value("${spring.datasource.mysql.password}")
    private String dbPassword;
    @Resource
    private dataTableManagerMapper dataTableManagerMapper;



    @Override
    public List<dataTable> upalldata() {
        return dataTableManagerMapper.upalldata();
    }

    @Override
    public List<Map<String, Object>> getInfoByTableName(String tableName) {
        return dataTableManagerMapper.getInfoByTableName(tableName);
    }

    @Override
    public String insert(dataTable datatable) {
        dataTableManagerMapper.save(datatable);
        return "新增成功";
    }

    @Override
    public Integer findTargetColumnIndex(String tablename, String targetcolumn) {
        return dataTableManagerMapper.findTargetColumnIndex(tablename, targetcolumn);
    }

    @Override
    public List<String> upname() {
        return dataTableManagerMapper.upname();
    }

    @Override
    public dataTable upbyid(Integer id) {
        return dataTableManagerMapper.upbyid(id);
    }

    @Override
    public void deletename(String tablename) {
        dataTableManagerMapper.deletetablename(tablename);
    }

    @Override
    public Boolean deletebyid(Integer id) {
        dataTableManagerMapper.deletebyid(id);
        return true;
    }

    @Override
    public void updata(dataTable a) {
        dataTableManagerMapper.updata(a);
    }

    @Override
    public void updateDataTable(String table_name,String disease, String user, Integer uid, String chinesename) {
        // 在这里实现计算样本数和特征数的逻辑
        int featurenumber = calculateFeatureNumber(table_name);
        int samplesize = calculateSampleSize(table_name);

        // 创建一个新的数据对象
        dataTable dataTableEntity = new dataTable();
        dataTableEntity.setTablename(table_name);
        dataTableEntity.setFeaturenumber(featurenumber);
        dataTableEntity.setDiseasename(disease); // 替换为疾病信息
        dataTableEntity.setDatanumber(samplesize);
        dataTableEntity.setLoadtime(Timestamp.valueOf(LocalDateTime.now()));
        dataTableEntity.setTabletype("数据表");
        dataTableEntity.setAffiliationdatabase("dataandresult");
        dataTableEntity.setUploadmethod("直接导入");
        dataTableEntity.setChinesename(chinesename);
        dataTableEntity.setIsProjection(0);
        dataTableEntity.setOperators(user); // 替换为创建者信息
        dataTableEntity.setUid(uid);

        // 插入新样本数据到data_table表中
        dataTableManagerMapper.insertDataTable(dataTableEntity);
    }

    private int calculateFeatureNumber(String tableName) {
        try {
            // 建立数据库连接
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            Statement statement = connection.createStatement();

            // 查询表头
            String query = "SELECT * FROM " + tableName + " LIMIT 1";
            ResultSet resultSet = statement.executeQuery(query);
            int featureNumber = resultSet.getMetaData().getColumnCount();

            // 关闭连接
            resultSet.close();
            statement.close();
            connection.close();

            return featureNumber;
        } catch (Exception e) {
            // 处理异常情况
            e.printStackTrace();
            return 0; // 或者抛出异常
        }
    }

    // 计算样本数的方法
    private int calculateSampleSize(String tableName) {
        try {
            // 建立数据库连接
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            Statement statement = connection.createStatement();

            // 查询数据行数
            String query = "SELECT COUNT(*) AS rowCount FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(query);
            int rowCount = 0;
            if (resultSet.next()) {
                rowCount = resultSet.getInt("rowCount");
            }

            // 关闭连接
            resultSet.close();
            statement.close();
            connection.close();

            // 减去表头行数（假设第一行是表头）
            return rowCount - 1;
        } catch (Exception e) {
            // 处理异常情况
            e.printStackTrace();
            return 0; // 或者抛出异常
        }
    }

    @Override
    public List<dataTable> upallDataByUid(Integer uid) {
        return dataTableManagerMapper.upallDataByUid(uid);
    }

    @Override
    public String getNameById(int id) {
        return dataTableManagerMapper.getNameById(id);
    }

    @Override
    public void deleteTable(String tablename) {
        dataTableManagerMapper.deleteTable(tablename);
    }

    @Override
    public void deleteTableResult(String tableresult) {
        dataTableManagerMapper.deleteTableResult(tableresult);
    }

    @Override
    public String getdiseasebyname(String table_name){
        return dataTableManagerMapper.getdiseasebyname(table_name);
    }
}
