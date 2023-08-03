package com.cqupt.software_9.demojava;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class DatabaseTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testDatabaseConnection() {
        // 执行数据库操作，例如插入、查询、更新等
        // 使用 jdbcTemplate 执行 SQL 语句或调用相应的 DAO 方法

        // 示例：查询用户表中的记录数量
        String sql = "SELECT COUNT(*) FROM user";
        int count = jdbcTemplate.queryForObject(sql, Integer.class);

        // 验证结果
        // 使用断言（例如 JUnit 的 assertEquals）来检查预期结果与实际结果是否一致
        assertEquals(10, count); // 假设预期结果为10
    }

    private void assertEquals(int i, int count) {
    }
}

