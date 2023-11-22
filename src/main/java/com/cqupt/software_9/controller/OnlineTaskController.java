package com.cqupt.software_9.controller;

import com.cqupt.software_9.common.OnlineRequest;
import com.cqupt.software_9.dao.mysql.OnlineTaskMapper;
import com.cqupt.software_9.entity.OnlineTask;
import com.cqupt.software_9.service.OnlineTaskService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/OnlineTask")
@CrossOrigin
public class OnlineTaskController {


     @Resource
     private OnlineTaskService onlineTaskService;

     @Resource
     private OnlineTaskMapper onlineTaskMapper;

     public OnlineTaskController(OnlineTaskService onlineTaskService){
         this.onlineTaskService=onlineTaskService;
     }



//    @PostMapping("add")
//    public List<OnlineTask> addTask(@RequestBody OnlineRequest onlineRequest){
//         OnlineTask onlineTask = new OnlineTask();
//
//
//         String[] fea;
//         String fea1;
//
//         fea=onlineRequest.getFeature();
//         fea1 = String.join(",",fea);
//
//         String[] para;
//         String para1;
//         para = onlineRequest.getPara();
//         para1 = String.join(",",para);
//
//        String[] paraV;
//        String paraV1;
//        paraV = onlineRequest.getParaValue();
//        paraV1 = String.join(",",paraV);
//
//         String[] res;
//         String res1;
//         res=onlineRequest.getResult();
//         res1=String.join(",",res);
//        onlineTask.setTaskName(onlineRequest.getTaskName());
//
//
//        onlineTask.setLeader(onlineRequest.getLeader());
//        onlineTask.setDisease(onlineRequest.getDisease());
//        onlineTask.setModel(onlineRequest.getModel());
//        onlineTask.setRemark(onlineRequest.getRemark());
//        onlineTask.setFeature(fea1);
//        onlineTask.setParameters(para1);
//        onlineTask.setParametersValue(paraV1);
//        onlineTask.setTargetcolumn(onlineRequest.getTargetcolumn());
//        onlineTask.setModelpath(onlineRequest.getModelpath());
//        onlineTask.setResult(res1);
//        onlineTask.setUid(onlineRequest.getUid());
//
//
//
//        onlineTaskService.addTask(onlineTask);
//
//
//         return onlineTaskService.upall();
//     }
    @PostMapping("/add")
    public List<OnlineTask> addTask(@RequestBody OnlineRequest onlineRequest) {
        OnlineTask onlineTask = new OnlineTask();
        System.out.println(onlineRequest);
        // 检查 onlineRequest 是否为 null
        if (onlineRequest != null) {
            onlineTask.setTaskName(onlineRequest.getTaskName());
            onlineTask.setLeader(onlineRequest.getLeader());
            onlineTask.setDisease(onlineRequest.getDisease());
            onlineTask.setModel(onlineRequest.getModelname());
            onlineTask.setRemark(onlineRequest.getRemark());

            // 检查 fea 是否为 null
            String[] fea = onlineRequest.getFeature();
            String fea1 = fea != null ? String.join(",", fea) : null;

            // 检查 para 是否为 null
            String[] para = onlineRequest.getPara();
            String para1 = para != null ? String.join(",", para) : null;

            // 检查 paraV 是否为 null
            String[] paraV = onlineRequest.getParaValue();
            String paraV1 = paraV != null ? String.join(",", paraV) : null;

            // 检查 res 是否为 null
            String[] res = onlineRequest.getResult();
            String res1 = res != null ? String.join(",", res) : null;

            onlineTask.setFeature(fea1);
            onlineTask.setParameters(para1);
            onlineTask.setParametersValue(paraV1);
            onlineTask.setTargetcolumn(onlineRequest.getTargetcolumn());
            onlineTask.setModelpath(onlineRequest.getModelpath());
            onlineTask.setResult(res1);
            onlineTask.setUid(onlineRequest.getUid());

            onlineTaskService.addTask(onlineTask);
        } else {
            // 处理 onlineRequest 为 null 的情况
            // 这里可以抛出异常或者返回适当的错误响应
        }

        return onlineTaskService.upall();
    }


    @GetMapping("/getAllById/{uid}")
    public List<OnlineTask>  getAllById(@PathVariable("uid") Integer uid){
         return onlineTaskMapper.getAllById(uid);
     }

    /**
     * 通过taskid删除模型
     * @param taskId
     * @RETURN
     *
     */
    @DeleteMapping("/delByTaskId/{taskId}")
    public int delByTaskId(@PathVariable("taskId") Integer taskId){
        return onlineTaskMapper.deleteById(taskId);
    }

}
