package com.cqupt.software_9.controller;


import com.alibaba.fastjson.JSON;
import com.cqupt.software_9.common.Feature;
import com.cqupt.software_9.common.FeatureType;
import com.cqupt.software_9.common.Result;
import com.cqupt.software_9.entity.FeatureEntity;
import com.cqupt.software_9.service.FeatureManageService;
import com.cqupt.software_9.vo.FeatureListVo;
import com.cqupt.software_9.vo.FeatureVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.ApplicationContext;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO 公共模块新增类
@RestController
@RequestMapping("/api/feature")
public class FeatureController {
    @Autowired
    FeatureManageService featureManageService;


    private final ApplicationContext context;
    @Autowired
    public FeatureController(ApplicationContext context) {
        this.context = context;
    }

    @GetMapping("/getFeatures")
    public Result<FeatureEntity> getFeture(@RequestParam("index") Integer belongType){ // belongType说明是属于诊断类型、检查类型、病理类型、生命特征类型
        String type = null;
        for (FeatureType value : FeatureType.values()) {
            if(value.getCode() == belongType){
                type = value.getName();
            }
        }
        List<FeatureVo> list = featureManageService.getFeatureList(type);
        return Result.success("200",list);
    }


    // TODO 废弃方法
    @PostMapping("/insertFeature") // 上传特征分类结果
    public Result fieldInsert(@RequestBody FeatureListVo featureListVo){
        System.out.println("tableHeaders:"+ JSON.toJSONString(featureListVo));

        featureManageService.insertFeatures(featureListVo);
        return null;
    }



    //特征选择处使用接口
    @GetMapping("/getTreeFeatures")
    public Result getTreeFeture(@RequestParam("tablename") String tablename) throws JsonProcessingException { // belongType说明是属于诊断类型、检查类型、病理类型、生命特征类型
        String type = null;
        PythonScriptController pythonScriptController = context.getBean(PythonScriptController.class);
        String result = PythonScriptController.pyfileUpload(tablename);
        // Convert JSON string to Java object
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonMap = mapper.readValue(result, new TypeReference<Map<String,Object>>(){});
        // Extract column names and missing rates
        List<String> columnNames = (List<String>) jsonMap.get("column_name");
        List<Double> missRates = (List<Double>) jsonMap.get("miss_rate");

        // Create a map to store column names and corresponding missing rates
        Map<String, Feature> featureMap = new HashMap<>();
        Map<String, Double> columnNameMissRateMap = new HashMap<>();
        for (int i = 0; i < columnNames.size(); i++) {
            String columnName = columnNames.get(i);
            Double missRate = missRates.get(i);
            columnNameMissRateMap.put(columnName, missRate);
        }
        Integer id = 1;
        List<Feature> TreeFeatures = new ArrayList<>();

        TreeFeatures.add(new Feature(id++,"人口学",null,null,null,false,"人口学",new ArrayList<Feature>()));
        featureMap.put("diagnosis", TreeFeatures.get(0));
        TreeFeatures.add(new Feature(id++,"生理指标",null,null,null,false,"生理指标",new ArrayList<Feature>()));
        featureMap.put("pathology", TreeFeatures.get(1));
        TreeFeatures.add(new Feature(id++,"行为学",null,null,null,false,"行为学",new ArrayList<Feature>()));
        featureMap.put("vital_signs", TreeFeatures.get(2));
//        type="diagnosis";
//        List<FeatureVo> list = featureManageService.getFeatureList(type);
//        for (FeatureVo featureVo : list){
//            // Now you can access missing rate for a given column name
//            String columnNameToSearch = featureVo.getFeatureName(); // Example column name
//            Double missingRate = columnNameMissRateMap.get(columnNameToSearch);
//            Feature  feature = new Feature(id++,featureVo.getChName(),missingRate.intValue(),0,false,true,null,null);
//            Feature demographicFeatureFromMap = featureMap.get("diagnosis");
//            demographicFeatureFromMap.getChildren().add(feature);
//        }

        for (FeatureType value : FeatureType.values()) {
            type =  value.getName();
            if(type.equals("diagnosis")){
                List<FeatureVo> list = featureManageService.getFeatureList(type);
                for (FeatureVo featureVo : list){
                    // Now you can access missing rate for a given column name
                    String columnNameToSearch = featureVo.getFeatureName(); // Example column name
                    Double missingRate = columnNameMissRateMap.get(columnNameToSearch);
                    Feature  feature = new Feature(id++,featureVo.getChName(),missingRate.intValue(),0,false,true,null,null);
                    Feature demographicFeatureFromMap = featureMap.get("diagnosis");
                    demographicFeatureFromMap.getChildren().add(feature);
                }
            }
            else if(type.equals("pathology")){
                List<FeatureVo> list = featureManageService.getFeatureList(type);
                for (FeatureVo featureVo : list){
                    // Now you can access missing rate for a given column name
                    String columnNameToSearch = featureVo.getFeatureName(); // Example column name
                    Double missingRate = columnNameMissRateMap.get(columnNameToSearch);
                    Feature  feature = new Feature(id++,featureVo.getChName(),missingRate.intValue(),0,false,true,null,null);
                    Feature demographicFeatureFromMap = featureMap.get("pathology");
                    demographicFeatureFromMap.getChildren().add(feature);
                }
            }
            else if(type.equals("vital_signs")){
                List<FeatureVo> list = featureManageService.getFeatureList(type);
                for (FeatureVo featureVo : list){
                    // Now you can access missing rate for a given column name
                    String columnNameToSearch = featureVo.getFeatureName(); // Example column name
                    Double missingRate = columnNameMissRateMap.get(columnNameToSearch);
                    Feature  feature = new Feature(id++,featureVo.getChName(), missingRate.intValue(), 0,false,true,featureVo.getFeatureName(),null);
                    Feature demographicFeatureFromMap = featureMap.get("vital_signs");
                    demographicFeatureFromMap.getChildren().add(feature);
                }
            }

        }
        List<Feature> featureList = new ArrayList<>();
        for (Map.Entry<String, Feature> entry : featureMap.entrySet()) {
            featureList.add(entry.getValue());
        }
        System.out.println(featureList);
        return Result.success("200",featureList);
    }
}
