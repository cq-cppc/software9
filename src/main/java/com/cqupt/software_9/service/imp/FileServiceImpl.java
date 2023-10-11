package com.cqupt.software_9.service.imp;

import com.cqupt.software_9.common.UploadResult;
import com.cqupt.software_9.service.Adapter.FileServiceAdapter;
import com.cqupt.software_9.service.DataTableManagerService;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@Service
public class FileServiceImpl extends FileServiceAdapter {

    @Resource
    private DataTableManagerService dataTableManagerService;

    @Value("${spring.datasource.mysql.url}")
    private String dbUrl;
    @Value("${spring.datasource.mysql.username}")
    private String dbUsername;
    @Value("${spring.datasource.mysql.password}")
    private String dbPassword;


    @Override
    public UploadResult fileUpload(MultipartFile file, String newName, String disease) throws IOException {
        System.out.println(file.getOriginalFilename());
        if (!file.getOriginalFilename().endsWith(".csv")) {
            throw new IllegalArgumentException("Only CSV files are supported.");
        }
//         调用dataTableManagerService.upallnamedata()来获取已用的表名列表
        List<String> usedTableNames = dataTableManagerService.upname();

        // 判断新表名是否重名
        if (usedTableNames.contains(newName)) {
            throw new IllegalArgumentException("Table name is already in use. Please enter a different table name.");
        }

        String tableName = newName;

//        新增的部分
        List<String> tableHeaders;
        //

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] columnNames = csvReader.readNext();

            // 新增
            tableHeaders = Arrays.asList(columnNames); // 将表头转换为List<String>
            // 移动指针到数据行的位置
            csvReader.readNext();


            try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
                createTable(connection, tableName, columnNames, csvReader);

                String insertQuery = generateInsertQuery(tableName, columnNames);

                String[] data;
                int batchCount = 0;
                CSVReader csvReaderForData = new CSVReader(new InputStreamReader(file.getInputStream()));
                csvReaderForData.readNext();
                while ((data = csvReaderForData.readNext()) != null) {
                    batchCount++;
                    try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                        for (int i = 0; i < data.length; i++) {
                            statement.setString(i + 1, data[i]);
                        }
                        statement.addBatch();
                        if (batchCount % 1000 == 0) {
                            statement.executeBatch();
                        }
                        statement.executeUpdate();

                    } catch (SQLException e) {
                        throw new RuntimeException("Failed to insert data into the database.", e);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Failed to create table or establish connection to the database.", e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV file.", e);
        }

        List<Map<String, Object>> res =dataTableManagerService.getInfoByTableName(tableName);

//        return tableName;
        // 创建并返回UploadResult对象
        UploadResult result = new UploadResult();
        result.setTableName(tableName);
        result.setTableHeaders(tableHeaders);
        result.setRes(res);
        return result;

    }

//    @Override
//    public UploadResult fileUpload(MultipartFile file, String newName, String disease) throws IOException {
//        System.out.println(file.getOriginalFilename());
//        if (!file.getOriginalFilename().endsWith(".csv")) {
//            throw new IllegalArgumentException("Only CSV files are supported.");
//        }
////         调用dataTableManagerService.upallnamedata()来获取已用的表名列表
//        List<String> usedTableNames = dataTableManagerService.upname();
//
//        // 判断新表名是否重名
//        if (usedTableNames.contains(newName)) {
//            throw new IllegalArgumentException("Table name is already in use. Please enter a different table name.");
//        }
//
//        String tableName = newName;
//
////        新增的部分
//        List<String> tableHeaders;
//        //
//
//        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
//            String[] columnNames = csvReader.readNext();
//
//            // 新增
//            tableHeaders = Arrays.asList(columnNames); // 将表头转换为List<String>
//            // 移动指针到数据行的位置
//            csvReader.readNext();
//
//
//            try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
//                Map<String, String> columnTypes = determineColumnTypes(columnNames, file.getInputStream());
//
//                createTable(connection, tableName, columnNames, columnTypes);
//
//                String loadDataSql = "LOAD DATA LOCAL INFILE ? INTO TABLE " + tableName +
//                        " FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\r\n' IGNORE 1 LINES";
//
//
////                String insertQuery = generateInsertQuery(tableName, columnNames);
//
//                try (PreparedStatement statement = connection.prepareStatement(loadDataSql)) {
//                    // 将文件路径传递给LOAD DATA命令
//                    statement.setBinaryStream(1, file.getInputStream());
//
//                    // 执行LOAD DATA命令导入数据
//                    statement.executeUpdate();
//                } catch (SQLException e) {
//                    throw new RuntimeException("Failed to insert data into the database.", e);
//                    }
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException("Failed to create table or establish connection to the database.", e);
//            }
//
//
//
//        List<Map<String, Object>> res =dataTableManagerService.getInfoByTableName(tableName);
//
////        return tableName;
//        // 创建并返回UploadResult对象
//        UploadResult result = new UploadResult();
//        result.setTableName(tableName);
//        result.setTableHeaders(tableHeaders);
//        result.setRes(res);
//        return result;
//
//    }

//    private String getTableName(String newName) {
//        String timestamp = getCurrentUploadTime();
//        return newName + "_" + timestamp;
//    }

    private void createTable(Connection connection, String tableName, String[] columnNames, CSVReader csvReader) throws SQLException, IOException {
        StringBuilder createTableQuery = new StringBuilder();
        createTableQuery.append("CREATE TABLE ").append(tableName).append(" (");



        Map<String, String> columnTypes = determineColumnTypes(columnNames, csvReader);

        for (int i = 0; i < columnNames.length; i++) {
            String columnName = columnNames[i];
            String columnType = columnTypes.get(columnName);

            // 使用反引号包裹列名
            createTableQuery.append("`").append(columnName).append("`").append(" ").append(columnType);

            if (i < columnNames.length - 1) {
                createTableQuery.append(", ");
            }
        }

        createTableQuery.append(")");

        try (PreparedStatement statement = connection.prepareStatement(createTableQuery.toString())) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create table.", e);
        }
    }

//    //load data方法
//    private void createTable(Connection connection, String tableName, String[] columnNames, Map<String, String> columnTypes) throws SQLException {
//        StringBuilder createTableQuery = new StringBuilder();
//        createTableQuery.append("CREATE TABLE ").append(tableName).append(" (");
//
//        for (int i = 0; i < columnNames.length; i++) {
//            String columnName = columnNames[i];
//            String columnType = columnTypes.get(columnName);
//
//            // 使用反引号包裹列名
//            createTableQuery.append("`").append(columnName).append("`").append(" ").append(columnType);
//
//            if (i < columnNames.length - 1) {
//                createTableQuery.append(", ");
//            }
//        }
//
//        createTableQuery.append(")");
//
//        try (PreparedStatement statement = connection.prepareStatement(createTableQuery.toString())) {
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("Failed to create table.", e);
//        }
//    }
//
////loda data fangfa
//    private Map<String, String> determineColumnTypes(String[] columnNames, InputStream inputStream) throws IOException {
//        Map<String, String> columnTypes = new HashMap<>();
//
//        try (CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream))) {
//            String[] sampleData = csvReader.readNext(); // 获取第一行作为样本数据
//
//            // 确保样本数据的长度和 columnNames 的长度一致
//            int sampleLength = Math.min(sampleData.length, columnNames.length);
//
//            for (int i = 0; i < sampleLength; i++) {
//                String columnName = columnNames[i];
//                String columnType = determineColumnType(sampleData[i]);
//                columnTypes.put(columnName, columnType);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to read CSV file.", e);
//        }
//
//        return columnTypes;
//    }


    private Map<String, String> determineColumnTypes(String[] columnNames, CSVReader csvReader) throws IOException {
        Map<String, String> columnTypes = new HashMap<>();

        try {
            List<String[]> allLines = csvReader.readAll();
            if (allLines.size() > 1) {
                String[] sampleData = allLines.get(1); // 获取第二行作为样本数据

                // 确保样本数据的长度和 columnNames 的长度一致
                int sampleLength = Math.min(sampleData.length, columnNames.length);

                System.out.println("ee" + Arrays.toString(sampleData));
                System.out.println(sampleData.length);
                System.out.println(columnNames.length);

                for (int i = 0; i < sampleLength; i++) {
                    String columnName = columnNames[i];
                    String columnType = determineColumnType(sampleData[i]);
                    columnTypes.put(columnName, columnType);
                }
            }
        } catch (IOException e) { // 添加对 IOException 的处理
            throw new RuntimeException("Failed to read CSV file.", e);
        }

        return columnTypes;
    }
//
//
    //    private String determineColumnType(String data) {
//        // Add your logic here to determine the appropriate column type based on the data
//        return "VARCHAR(255)";
//    }

private String determineColumnType(String data) {
    // 判断数据是否为整数
    try {
        Integer.parseInt(data);
        return "INT"; // 如果是整数，返回INT类型
    } catch (NumberFormatException e) {
        // 不是整数，继续判断其他类型
    }

    // 判断数据是否为浮点数
    try {
        Double.parseDouble(data);
        return "DOUBLE"; // 如果是浮点数，返回DOUBLE类型
    } catch (NumberFormatException e) {
        // 不是浮点数，继续判断其他类型
    }

    // 判断数据是否为日期，这里假设日期格式为"yyyy-MM-dd"，你可以根据实际情况修改格式
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    try {
        LocalDate.parse(data, dateFormatter);
        return "DATE"; // 如果是日期，返回DATE类型
    } catch (DateTimeParseException e) {
        // 不是日期，继续判断其他类型
    }

    // 如果以上条件都不满足，则返回默认的VARCHAR类型，长度为255
    return "VARCHAR(255)";
}


    private String generateInsertQuery(String tableName, String[] columnNames) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(tableName);
        sb.append(" (");

        for (int i = 0; i < columnNames.length; i++) {
            sb.append("`"); // 添加反引号（`）开始包围列名
            sb.append(columnNames[i]);
            sb.append("`"); // 添加反引号（`）结束包围列名

            if (i < columnNames.length - 1) {
                sb.append(", ");
            }
        }

        sb.append(") VALUES (");

        for (int i = 0; i < columnNames.length; i++) {
            sb.append("?");

            if (i < columnNames.length - 1) {
                sb.append(", ");
            }
        }

        sb.append(")");
        return sb.toString();
    }

}
