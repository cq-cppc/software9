package com.cqupt.software_9.controller;

import com.cqupt.software_9.entity.Symp;
import com.cqupt.software_9.service.SympService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/Symp")
@RestController
public class SympController {

    @Resource
    private SympService sympService;



    @GetMapping("/api/symp/loadall")
    public List<Symp> getAllsymp() {
        return sympService.getAllsymp();
    }

    @GetMapping("/api/symp/Sploadall")
    public String getAllspsymp(){

        List<Symp> symp = sympService.getAllsymp();
        List<JsonObject> result = new ArrayList<>();

// 创建一个 Map 用于按照 partCode 分组存储数据
        Map<String, List<JsonObject>> groupedData = new HashMap<>();
        for (Symp item : symp) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("code", item.getCode());
            jsonObject.addProperty("name", item.getName());
            jsonObject.addProperty("isget", item.getIsGet());


            if (!groupedData.containsKey(item.getPartCode())) {
                groupedData.put(item.getPartCode(), new ArrayList<>());
            }

            groupedData.get(item.getPartCode()).add(jsonObject);
        }



        for (String partCode : groupedData.keySet()) {
            Map<String, String> partNameMap = new HashMap<>();
            partNameMap.put("face", "五官");
            partNameMap.put("back", "背部");
            partNameMap.put("arms", "四肢");
            partNameMap.put("belly", "腹部");
            partNameMap.put("head", "头部");
            JsonObject partObject = new JsonObject();
            partObject.addProperty("partCode", partCode);
            // 根据partCode获取相应的partName，如果没有映射关系则使用默认值
            String partName = partNameMap.getOrDefault(partCode, "");
            partObject.addProperty("partName", partName);

            JsonArray sympArray = new JsonArray();
            for (JsonObject jsonObject : groupedData.get(partCode)) {
                sympArray.add(jsonObject);
            }


            partObject.add("symp", sympArray);
            result.add(partObject);
        }
        Gson gson = new Gson();
        String jsonString = gson.toJson(result);


        return jsonString;
    }


}
