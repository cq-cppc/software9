package com.cqupt.software_9.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.software_9.common.Result;
import com.cqupt.software_9.entity.StasticOne;

import java.util.List;


public interface StasticOneService extends IService<StasticOne> {
    StasticOne getStasticOne();

    Result getDiseases();

    List<String> getAllTableNames();
}
