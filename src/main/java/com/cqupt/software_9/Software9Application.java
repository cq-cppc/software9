package com.cqupt.software_9;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cqupt.software_9.mapper")
public class Software9Application {

    public static void main(String[] args) {
        SpringApplication.run(Software9Application.class, args);
    }

}
