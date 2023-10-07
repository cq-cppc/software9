package com.cqupt.software_9.controller;

import com.alibaba.fastjson.JSONObject;
import com.cqupt.software_9.common.R;
import com.cqupt.software_9.dao.ckd.CkdManagerMapper;
import com.cqupt.software_9.dao.data.DataManagerMapper;
import com.cqupt.software_9.dao.mysql.HeartManagerMapper;
import com.cqupt.software_9.service.DiseasesService;
import com.cqupt.software_9.service.Request.RuntimeBusCreateRequest;
import com.cqupt.software_9.service.Request.RuntimeBusCreateRequestSingleHeart;
import com.cqupt.software_9.service.Response.CustomResponse;
import com.cqupt.software_9.service.Response.RuntimeBusServiceResponse;
import com.cqupt.software_9.service.Response.RuntimeBusServiceResponseHearts;
import com.cqupt.software_9.service.Response.RuntimeBusServiceResponseSingleHeart;
import com.cqupt.software_9.service.RuntimeBusService;
import com.cqupt.software_9.service.TableManagerService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

    @Log4j2
@RestController
@RequestMapping("/runtime_bus")

class RuntimeBusController {
    @Autowired
    private TableManagerService tableManagerService;
    @Resource
    private RuntimeBusService runtimeBusService;
    private DataManagerMapper dataManagerMapper;

    private CkdManagerMapper ckdManagerMapper;
    @Resource
    private  HeartManagerMapper heartManagerMapper;
    @Resource
    private     DiseasesService diseasesService;

    @Autowired
    public RuntimeBusController(DiseasesService diseasesService,RuntimeBusService runtimeBusService,TableManagerService tableManagerService,DataManagerMapper dataManagerMapper,CkdManagerMapper ckdManagerMapper) {
        this.diseasesService = diseasesService;
        this.runtimeBusService=runtimeBusService;
        this.tableManagerService=tableManagerService;
        this.dataManagerMapper=dataManagerMapper;
        this.ckdManagerMapper=ckdManagerMapper;
    }


        /**
         * Author:陈鹏
         *时间：2023.6
         *调用多疾病模型
         * @return
         * */
        @PostMapping("/submit")
        public List<CustomResponse> busSubmit(@RequestBody RuntimeBusCreateRequest request) throws Exception {
            log.info("busSubmit："+ JSONObject.toJSONString(request));
            RuntimeBusServiceResponse res=runtimeBusService.submitBus(request);
            List<CustomResponse> customResponses = new ArrayList<>();

            for (String jsonString : res.getRes()) {
                JSONObject jsonObject = JSONObject.parseObject(jsonString);
                String code = jsonObject.getString("code");
                // 根据 code 查询数据库信息
                // 假设查询结果为 info1 和 info2

                String part = diseasesService.findpartNamebyCode(code);
                String name = diseasesService.findnamebyCode(code);
                String symp = diseasesService.findsympbyCode(code);
                String dptment = diseasesService.finddptmentbyCode(code);
                String prevent = diseasesService.findpreventbyCode(code);



                CustomResponse customResponse = new CustomResponse();
                customResponse.setCode(code);
                customResponse.setPart(part);
                customResponse.setName(name);
                customResponse.setSymp(symp);
                customResponse.setPrevent(prevent);
                customResponse.setDptment(dptment);

                customResponses.add(customResponse);
            }

            return customResponses;
        }
        /**
         * Author:陈鹏
         *时间：2023.6
         *调用心脏病模型、单例预测。
         *
         * @return
         * */
        @PostMapping("/submit-sp.heart")
        public List<String> busSubmit(@RequestBody RuntimeBusCreateRequestSingleHeart request) throws Exception {
            log.info("busSubmit："+ JSONObject.toJSONString(request));
            RuntimeBusServiceResponseSingleHeart res= runtimeBusService.submitBus1(request);
//            System.out.println(res);
            List<String> resList = res.getRes();
            List<String> updatedResList = new ArrayList<>();

            for (String element : resList) {
                if (element.startsWith("{\"probability\"")) {
                    // probability 元素，保持不变
                    updatedResList.add(element);
                } else if (element.startsWith("{\"contributions\"")) {
                    // contributions 元素，进行进一步处理
                    Map<String, Map<String, String>> contributions = new Gson().fromJson(element, new TypeToken<Map<String, Map<String, String>>>(){}.getType());

                    Map<String, Map<String, String>> updatedContributions = new HashMap<>();
                    for (Map.Entry<String, Map<String, String>> entry : contributions.entrySet()) {
                        String key = entry.getKey();
                        Map<String, String> value = entry.getValue();

                        Map<String, String> updatedValue = new HashMap<>();
                        for (Map.Entry<String, String> innerEntry : value.entrySet()) {
                            String innerKey = innerEntry.getKey();
                            String innerValue = innerEntry.getValue();

                            // 根据键值查询对应的名称
                            String name = heartManagerMapper.findnamebysymp(innerKey);
                            updatedValue.put(name, innerValue);
                        }

                        updatedContributions.put(key, updatedValue);
                    }

                    // 将处理后的 contributions 添加到更新后的结果列表中
                    updatedResList.add(new Gson().toJson(updatedContributions));
                }
            }

            return updatedResList;
        }



        /**
         * Author:陈鹏
         *时间：2023.6
         *调用心脏病模型、单例预测、调用数据库数据
         *
         * @return
         * */
        @PostMapping("/submit-sp.heart1")
        public List<String> busSubmit2(@RequestBody Integer patientId) throws Exception {

            System.out.println(patientId);
            log.info("busSubmit："+ JSONObject.toJSONString(patientId));
            RuntimeBusServiceResponseSingleHeart res = runtimeBusService.submitBus2(patientId);
            List<String> resList = res.getRes();
            List<String> updatedResList = new ArrayList<>();

            for (String element : resList) {
                if (element.startsWith("{\"probability\"")) {
                    // probability 元素，保持不变
                    updatedResList.add(element);
                } else if (element.startsWith("{\"contributions\"")) {
                    // contributions 元素，进行进一步处理
                    Map<String, Map<String, String>> contributions = new Gson().fromJson(element, new TypeToken<Map<String, Map<String, String>>>(){}.getType());

                    Map<String, Map<String, String>> updatedContributions = new HashMap<>();
                    for (Map.Entry<String, Map<String, String>> entry : contributions.entrySet()) {
                        String key = entry.getKey();
                        Map<String, String> value = entry.getValue();

                        Map<String, String> updatedValue = new HashMap<>();
                        for (Map.Entry<String, String> innerEntry : value.entrySet()) {
                            String innerKey = innerEntry.getKey();
                            String innerValue = innerEntry.getValue();

                            // 根据键值查询对应的名称
                            String name = heartManagerMapper.findnamebysymp(innerKey);
                            updatedValue.put(name, innerValue);
                        }

                        updatedContributions.put(key, updatedValue);
                    }

                    // 将处理后的 contributions 添加到更新后的结果列表中
                    updatedResList.add(new Gson().toJson(updatedContributions));
                }
            }
            return updatedResList;
        }
        /**
         * Author:陈鹏
         *时间：2023.6
         *调用心脏病模型、批量预测。
         *
         * @return
         * */
        @PostMapping("/submit-hearts")
        public R<List<Map<String,Object>>> submitHearts(@RequestBody String tableName ) throws Exception {
            System.out.println(tableName+"ooooo");
            String tableNameWithoutQuotes = tableName.replaceAll("\"", "");
            System.out.println(tableName+"ppppp");
          dataManagerMapper.updata(tableNameWithoutQuotes);
            RuntimeBusServiceResponseHearts response = runtimeBusService.submitBus3(tableName);
            List<String> res = response.getRes();
            if (res == null || res.isEmpty()) {
                // 处理无法获取输出表名的情况，可能需要返回适当的错误响应
                return new R<>(400, "无法获取输出表名", null);
            }

            String outputTableNameJson = res.get(0);

            // 解析JSON字符串
            JsonParser parser = new JsonParser();
            JsonObject json = parser.parse(outputTableNameJson).getAsJsonObject();
            String outputTableName = json.get("Output table name").getAsString();
//            DataManager data = new DataManager();
//            data.setTablename(outputTableName);
//            dataManagerMapper.insertDataManager(data);
            List<Map<String, Object>> result = tableManagerService.getInfoByTableName(outputTableName);
            return new R<>(200, "成功", result);

        }
//    @PostMapping("/getInfoByTableName")
//    public R<List<Map<String,Object>>> getInfoByTableName(@RequestBody String tableName){
//        tableName = tableName.replace("\"", "");
//        List<Map<String, Object>> res = tableManagerService.getInfoByTableName(tableName);
//        return new R<>(200, "成功", res);
//    }
        /**
         * Author:陈鹏
         *时间：2023.6
         *调用CKD模型、批量预测。
         *
         * @return
         * */
        @PostMapping("/submit-ckds")
        public RuntimeBusServiceResponseHearts submitCkds(@RequestParam String tableName ) throws Exception {

            return runtimeBusService.submitBus4(tableName);

        }


//    public R<Map> submitCKD(@RequestBody String tableName) {
//        Map<String, Object> json = null;
//        String pythonScriptPath = "F:/Code/CKD-Prediction-master/mutli_prediction.py"; // Python脚本的路径
//        String[] command = {"python", pythonScriptPath};
//
//        try {
//            // 设置环境变量
//            ProcessBuilder processBuilder = new ProcessBuilder(command);
//            Map<String, String> environment = processBuilder.environment();
//            environment.put("TABLE_NAME", tableName);
//
//            // 执行命令行命令
//            Process process = processBuilder.start();
//
//            // 读取命令行输出
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            StringBuilder output = new StringBuilder();
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//                output.append(line);
//            }
//            System.out.println(line);
//            // 解析JSON字符串为Java对象
//            Gson gson = new Gson();
//            json = gson.fromJson(output.toString(), Map.class);
//
//            // 等待命令执行完成
//            int exitCode = process.waitFor();
//            System.out.println("Command exited with code: " + exitCode);
//
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//        return new R<>(200, "成功", json);
//    }

}
