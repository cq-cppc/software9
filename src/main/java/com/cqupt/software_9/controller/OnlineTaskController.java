package com.cqupt.software_9.controller;

import com.cqupt.software_9.common.OnlineRequest;
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


    @PostMapping("add")
    public List<OnlineTask> addTask(@RequestBody OnlineRequest onlineRequest){
         OnlineTask onlineTask = new OnlineTask();


         String[] fea;
         String fea1;

         fea=onlineRequest.getFeature();
         fea1 = String.join(",",fea);

         String[] para;
         String para1;
         para = onlineRequest.getPara();
         para1 = String.join(",",para);

        String[] paraV;
        String paraV1;
        paraV = onlineRequest.getParaValue();
        paraV1 = String.join(",",paraV);

         String[] res;
         String res1;
         res=onlineRequest.getResult();
         res1=String.join(",",res);
        onlineTask.setTaskName(onlineRequest.getTaskName());


        onlineTask.setLeader(onlineRequest.getLeader());
        onlineTask.setDisease(onlineRequest.getDisease());
        onlineTask.setModel(onlineRequest.getModel());
        onlineTask.setRemark(onlineRequest.getRemark());
        onlineTask.setFeature(fea1);
        onlineTask.setParameters(para1);
        onlineTask.setParametersValue(paraV1);
        onlineTask.setTargetcolumn(onlineRequest.getTargetcolumn());
        onlineTask.setModelpath(onlineRequest.getModelpath());
        onlineTask.setResult(res1);

        

        onlineTaskService.addTask(onlineTask);


         return onlineTaskService.upall();
     }


}
