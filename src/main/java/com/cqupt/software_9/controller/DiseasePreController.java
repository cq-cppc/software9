package com.cqupt.software_9.controller;

import com.cqupt.software_9.entity.AlgorithmData;
import com.cqupt.software_9.entity.AlgorithmDataRequest;
import com.cqupt.software_9.mapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/DiseasePre")
public class DiseasePreController {

    @Resource
    private ModelMapper modelMapper;




    @PostMapping("/algorithmData")
    public String  algorithmData(@RequestBody List<AlgorithmDataRequest> requestList)
            throws IOException, InterruptedException {
       for(AlgorithmDataRequest request : requestList){
           // 处理接收到的数据，可以保存到数据库或进行其他业务逻辑处理
           String algorithmName = "";
           Map<String, Float> features = null;
           Integer uid = request.getUid();
           List<AlgorithmData> algorithmDataList  = request.getAlgorithmData();
           for (AlgorithmData algorithmData : algorithmDataList) {
               algorithmName = algorithmData.getAlgorithm();
               features = algorithmData.getFeatures();
               // 在这里可以对 algorithmName 和 features 进行操作
           }
           String modelUrl = modelMapper.getUrlByalgorithmNameAndUid(uid, algorithmName);
//           pythonExecutor.executePythonScript(modelUrl, features);
       }

        return "Data saved successfully!";
    }

}
