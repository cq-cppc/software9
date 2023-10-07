package com.cqupt.software_9.service.imp;


import com.cqupt.software_9.dao.disease.dataTableManagerMapper;
import com.cqupt.software_9.entity.dataTable;
import com.cqupt.software_9.service.Adapter.DataTableManagerServiceAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Service
public class DataTableManagerServiceImpl extends DataTableManagerServiceAdapter {
    @Value("${spring.datasource.disease.url}")
    private String dbUrl;
    @Value("${spring.datasource.disease.username}")
    private String dbUsername;
    @Value("${spring.datasource.disease.password}")
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
    public void deletebyid(Integer id) {
        dataTableManagerMapper.deletebyid(id);
    }

    @Override
    public void updata(dataTable a) {
        dataTableManagerMapper.updata(a);
    }

    @Override
    public void updateDataTable(String table_name,String disease) {
        // 在这里实现计算样本数和特征数的逻辑
        int featurenumber = calculateFeatureNumber(table_name);
        int samplesize = calculateSampleSize(table_name);

        // 创建一个新的数据对象
        dataTable dataTableEntity = new dataTable();
        dataTableEntity.setTable_name(table_name);
        dataTableEntity.setFeaturenumber(featurenumber);
        dataTableEntity.setSamplesize(samplesize);
//        dataTableEntity.setTableType("Your Table Type"); // 替换为表类型信息
        dataTableEntity.setDisease(disease); // 替换为疾病信息
        dataTableEntity.setCreator("陈鹏"); // 替换为创建者信息

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
}
