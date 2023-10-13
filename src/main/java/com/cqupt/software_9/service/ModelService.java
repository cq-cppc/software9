package com.cqupt.software_9.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.software_9.entity.Model;
import com.cqupt.software_9.entity.trainAl;

public interface ModelService extends IService<Model> {
    void trainModel(trainAl trainAl);
}
