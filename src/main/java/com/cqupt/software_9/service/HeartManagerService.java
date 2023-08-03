package com.cqupt.software_9.service;

import com.cqupt.software_9.entity.HeartManager;

import java.util.List;

public interface HeartManagerService {

    List<String> getinforbytablename(String tablename);
    List<HeartManager> getall();
}
