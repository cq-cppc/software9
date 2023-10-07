package com.cqupt.software_9.service.imp;

import com.cqupt.software_9.service.Adapter.Online_randforServiceAdapter;
import com.cqupt.software_9.service.Request.RuntimeBusCreateRequest_online_RandFor;
import com.cqupt.software_9.service.Request.RuntimeTaskRequest;
import com.cqupt.software_9.service.Response.OnlineServiceResponse;
import com.cqupt.software_9.service.Response.RuntimeBusServiceResponse;
import com.cqupt.software_9.service.Response.RuntimeTaskResponse;
import com.cqupt.software_9.service.RuntimeTaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class Online_randforServiceImpl extends Online_randforServiceAdapter {
    @Resource
    private RuntimeTaskService runtimeTaskService;
    @Resource
    private DataTableManagerServiceImpl dataTableManagerService;
    @Override
    public OnlineServiceResponse online_Randfor(RuntimeBusCreateRequest_online_RandFor request) throws Exception {
        OnlineServiceResponse response=new OnlineServiceResponse();
        BeanUtils.copyProperties(request,response);
        List<String> args=new LinkedList<>();

        int targetcolumn = dataTableManagerService.findTargetColumnIndex(request.getTablename(),request.getTargetcolumn());

        List<Integer> indexes = new ArrayList<>();
        String[] CalculatedColumn = request.getFea();
        for (String CalculatedColumns : CalculatedColumn) {
            Integer index = dataTableManagerService.findTargetColumnIndex(request.getTablename(), CalculatedColumns);
            if (index != null) {
                indexes.add(index);
            }
        }
        List<String> indexStrings = new ArrayList<>();
        for (Integer index : indexes) {
            indexStrings.add(String.valueOf(index));
        }

// 将字符串列表用空格连接成一个完整的参数字符串
        String feature = "--feature=" + String.join(" ", indexStrings);

// 然后将这个参数字符串作为一个整体添加到 args 中
        args.add(feature);
        //TableResponse tableResponse=tableService.findById(request.getTableId());
        args.add("--tableName="+request.getTablename());
        args.add("--target="+targetcolumn);
        args.add("--n_estimators="+request.getN_estimators());
        args.add("--min_samples_leaf="+request.getMin_samples_leaf());
        args.add("--min_samples_split="+request.getMin_samples_split());
        args.add("--max_features="+request.getMax_features());
        args.add("--bootstrap="+request.isBootstrap());
//        args.add("--calculatedColumns="+ argsList);
        System.out.println(args);

        RuntimeTaskRequest runtimeTaskRequest=new RuntimeTaskRequest();
        // runtimeTaskRequest.setTaskType(TaskType.runtime.toString());
        // runtimeTaskRequest.setArgs(args);
        // runtimeTaskRequest.setBizId(request.getBusId());
        runtimeTaskRequest.setPyPath("F:/code/Online training/Random Forest-test.py");

        runtimeTaskRequest.setArgs(args);
//        System.out.println("Python脚本路径：" + runtimeTaskRequest.getPyPath());

        RuntimeTaskResponse taskResponse=runtimeTaskService.submitTask(runtimeTaskRequest);
        // response.setTaskCreateTime(new Timestamp(taskResponse.getTaskFinishTime()));
        response.setRes(taskResponse.getRes());
        return response;
    }
}
