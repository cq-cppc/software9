package com.cqupt.software_9.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqupt.software_9.common.R;
import com.cqupt.software_9.common.Result;
import com.cqupt.software_9.common.UploadResult;
import com.cqupt.software_9.entity.*;
import com.cqupt.software_9.mapper.DataManagerMapper;
import com.cqupt.software_9.mapper.ModelMapper;
import com.cqupt.software_9.mapper.modelResultMapper;
import com.cqupt.software_9.service.DataTableManagerService;
import com.cqupt.software_9.service.FileService;
import com.cqupt.software_9.service.ModelService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.log.LogInputStream;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/Model")
@RestController
public class ModelController {
    @Resource
    private ModelMapper modelMapper;

    @Resource
    private ModelService modelService;

    @Resource
    private modelResultMapper modelResultMapper;

    @Resource
    private DataTableManagerService dataTableManagerService;

    @Resource
    private FileService fileService;

    @Resource
    private DataManagerMapper dataManagerMapper;

    @Resource
    private com.cqupt.software_9.mapper.tTableManagerMapper tTableManagerMapper;

    @GetMapping("/getall")
    public List<Model> getallmodel(){
        return modelMapper.getallmodel();
    }

    @PostMapping("/baseInfo")
    public boolean insertBaseInfo(@RequestBody Model model) {
        return modelService.saveOrUpdate(model);
    }



    /**
     *
     * @param file 前端传来的文件
     * @param modelname 训练名称
     * @param diseasename   疾病名称
     * @return
     * @throws Exception
     */
    @PostMapping("/file")
    public UploadResult upload(@RequestParam("file") MultipartFile file,
                               @RequestParam("modelname") String modelname ,
                               @RequestParam("diseasename") String diseasename,
                               @RequestParam("publisher") String publisher,
                               @RequestParam("uid") Integer uid) throws Exception {

        try {
            String fileName = file.getOriginalFilename().replace(".csv", "");
            QueryWrapper<DataManager> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("tablename", fileName);
            Long count = dataManagerMapper.selectCount(queryWrapper);
            if (count > 0) {
                return new UploadResult(null, 400, null, null, null);
            }
            UploadResult res =  fileService.fileUpload(file, modelname,diseasename,publisher,uid);
            return res;
        } catch (Exception e) {
            UploadResult result =new UploadResult();
            result.setE(e);
            return result;
        }
    }

    /**
     * 通过uid查询该uid下的所有表
     * @param uid
     * @return
     */
    @GetMapping("/tableName/{uid}")
    public List<DataManager> getTableName(@PathVariable("uid") Integer uid) {
        return dataManagerMapper.getTableNameByUiD(uid);
    }

    /**
     *  新加3.26
     *  获得model表的所有数据
     *
     */
    @GetMapping("/ModelAll")
    public Result<Model> upall(){
        List<Model> model = modelService.upall();
        return Result.success("200",model);
    }

    /**
     * 通过tablename查询该数据表中的所有特征值
     * @param tablename
     * @return
     */
    @GetMapping("/getFeature/{tablename}")
    public List<tTableField> getFeature(@PathVariable("tablename") String tablename){
        return tTableManagerMapper.getFeatureByTableName(tablename);
    }


    /**
     * 通过uid和模型名称查询该用户所选模型所需要的特征等数据
     */
    @GetMapping("/getModelFeatureByUidAndModelName/{uid}/{modelname}")
    public List<Model> getModelFeatureByUidAndModelName(@PathVariable("uid") Integer uid,
                                       @PathVariable("modelname") String modelname){
        return modelMapper.getModelFeatureByUidAndModelName(uid, modelname);
    }

    /**
     * 通过uid和模型名称查询该用户所选模型所需要的特征等数据
     */
    @GetMapping("/getModelFeatureandResultByUidAndModelName/{uid}/{modelname}")
    public Result<List<List<Object>>>  getModelFeatureandResultByUidAndModelName(@PathVariable("uid") Integer uid,
                                                                            @PathVariable("modelname") String modelname){
        List<Model> models = modelMapper.getModelFeatureByUidAndModelName(uid, modelname);
        List<modelResult> modelResults = modelResultMapper.getModelResultByUidAndModelName(uid,modelname);
        List<List<Object>> result = new ArrayList<>();

        System.out.println(models+"1111");
        System.out.println(modelResults+"22222");

        // 检查两个列表的大小，取最小的那个
        int minLength = Math.min(models.size(), modelResults.size());

        // 遍历列表，将每个对应的元素放入新的二维列表中
        for (int i = 0; i < minLength; i++) {
            List<Object> pair = new ArrayList<>();
            pair.add(models.get(i)); // 添加models列表中的当前元素
            pair.add(modelResults.get(i)); // 添加modelResults列表中的当前元素
            result.add(pair); // 将这个pair添加到结果列表中
        }
        System.out.println(result+"00000000000000");

        return Result.success("200",result);

    }

    /**
     * 通过uid和模型名称删除数据
     */
    @GetMapping("/remove/{uid}/{modelname}")
    public Result<Model> ModelRemove(@PathVariable("uid") Integer uid,
                                                        @PathVariable("modelname") String modelname){
        modelMapper.removeModel(uid,modelname);
        modelResultMapper.removeModelResult(uid,modelname);
        List<Model> model = modelService.upall();
        return Result.success("200",model);
    }


    /**
     * 通过算法名查找算法简介和参数
     */
    @GetMapping("getAlgorithm")
    public List<publicAl> getAlgorithmByAlgorithmName(){
        return modelMapper.getAlgorithmByAlgorithmName();
    }

    /**
     * 接收前端传过来的表名，所选目标列和特征列和所选算法及算法超参
     */
    @PostMapping("/trainAl")
    public Map<String, List<modelResult>> trainAl(@RequestBody trainAl trainAl){

        return modelService.trainModel(trainAl);
    }


    /**
     * 数据选择模块中，通过病种查询对应的数据表
     */
    @GetMapping("/getTableByDisease/{diseasename}")
    public  List<String> getTableByDisease(@PathVariable("diseasename")String diseasename){
        return dataManagerMapper.getTableByDisease(diseasename);
    }

    /**
     * 根据数据表明获取到列名作为特证名
     */
    @GetMapping("/getFeaByTableName/{tableName}")
    public List<String> getFeaByTableName(@PathVariable("tableName")String tableName){
        return modelMapper.getFeaByTableName(tableName);
    }


    @GetMapping("/getInfoByTableName/{tableName}")
    public R<List<Map<String,Object>>> getInfoByTableName(@PathVariable("tableName") String tableName){
        tableName = tableName.replace("\"", "");
        List<Map<String, Object>> res = dataTableManagerService.getInfoByTableName(tableName);
        return new R<>(200, "成功", res);
    }



}