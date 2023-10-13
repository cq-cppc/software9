package com.cqupt.software_9.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.software_9.entity.Model;
import com.cqupt.software_9.entity.trainAl;
import com.cqupt.software_9.mapper.ModelMapper;
import com.cqupt.software_9.service.ModelService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ModelServiceImpl extends ServiceImpl<ModelMapper, Model> implements ModelService {


    @Override
    public void trainModel(trainAl trainAl) {
    // 调用训练模型算法
        String tableName = trainAl.getTableName();
        String target = trainAl.getTarget();
        String[] fea = trainAl.getFea();
        List<Map<String, Map<String, String>>> completeParameter = trainAl.getCompleteParameter();
        for (Map<String, Map<String,String>> algorithmMap : completeParameter) {
            String algorithmName = algorithmMap.keySet().iterator().next();
            Map<String,String> algorithmAttributes = algorithmMap.get(algorithmName);

// 构建命令行参数列表
            ProcessBuilder pb = new ProcessBuilder("python", "E:\\soft\\software9-3\\software9\\src\\main\\resources\\Algorithm\\publicAl.py", tableName, target);
            for (String feature : fea) {
                pb.command().add(feature);
            }
            pb.command().add(algorithmName);
            for (Map.Entry<String, String> entry : algorithmAttributes.entrySet()) {
                pb.command().add(entry.getKey() + "=" + entry.getValue());
            }

            // 启动进程并等待其结束
            try {
                Process process = pb.start();
                int exitCode = process.waitFor();

                if (exitCode == 0) {
                    System.out.println("Python script executed successfully.");
                } else {
                    System.out.println("Error executing Python script. Exit code: " + exitCode);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        }
    }



}
