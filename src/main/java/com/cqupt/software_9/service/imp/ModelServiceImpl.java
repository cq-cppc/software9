package com.cqupt.software_9.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.software_9.entity.Model;
import com.cqupt.software_9.entity.trainAl;
import com.cqupt.software_9.mapper.ModelMapper;
import com.cqupt.software_9.service.ModelService;
import com.cqupt.software_9.tool.PythonRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ModelServiceImpl extends ServiceImpl<ModelMapper, Model> implements ModelService {


    @Autowired
    private PythonRun pythonRun; // 注入 PythonRun 类的实例


    @Override
    public void trainModel(trainAl trainAl) {
        // 调用训练模型算法
        String tableName = trainAl.getTableName();
        String target = trainAl.getTarget();
        String[] fea = trainAl.getFea();
        List<Map<String, Map<String, String>>> completeParameter = trainAl.getCompleteParameter();
        for (Map<String, Map<String, String>> algorithmMap : completeParameter) {
            String algorithmName = algorithmMap.keySet().iterator().next();
            Map<String, String> algorithmAttributes = algorithmMap.get(algorithmName);

            List<String> args = new ArrayList<>();
            args.add(tableName);
            args.add(target);
            for (String feature : fea) {
                args.add(feature);
            }
            args.add(algorithmName);
            for (Map.Entry<String, String> entry : algorithmAttributes.entrySet()) {
                args.add(entry.getKey() + "=" + entry.getValue());
            }

            try {
                // 执行 Python 脚本并获取结果
                String result = pythonRun.publicAl("E:\\soft\\software9-3\\software9\\src\\main\\resources\\Algorithm\\python\\publicAl.py",
                        tableName,target,fea,algorithmName,algorithmAttributes);
                // 处理脚本执行结果
                // 将结果存入 resultMap 中
                System.out.println(result);
            } catch (Exception e) {
                e.printStackTrace();
                // 处理异常
            }
        }
    }
}
