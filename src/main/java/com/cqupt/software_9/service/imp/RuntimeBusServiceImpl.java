package com.cqupt.software_9.service.imp;

import com.cqupt.software_9.entity.PatientHeartDisease;
import com.cqupt.software_9.service.Adapter.RuntimeBusServiceAdapter;
import com.cqupt.software_9.service.PatientHeartDiseaseService;
import com.cqupt.software_9.service.Request.RuntimeBusCreateRequest;
import com.cqupt.software_9.service.Request.RuntimeBusCreateRequestSingleHeart;
import com.cqupt.software_9.service.Request.RuntimeTaskRequest;
import com.cqupt.software_9.service.Response.RuntimeBusServiceResponse;
import com.cqupt.software_9.service.Response.RuntimeBusServiceResponseHearts;
import com.cqupt.software_9.service.Response.RuntimeBusServiceResponseSingleHeart;
import com.cqupt.software_9.service.Response.RuntimeTaskResponse;
import com.cqupt.software_9.service.RuntimeTaskService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@Log4j2
public class RuntimeBusServiceImpl extends RuntimeBusServiceAdapter {

    @Resource
    private RuntimeTaskService runtimeTaskService;
    @Resource
    private PatientHeartDiseaseService patientHeartDiseaseService;

 /*   @Resource(name = "dataTableDataSourceProperties")
    private DataSourceProperties dataTableDataSourceProperties;*/


    @Override
    public RuntimeBusServiceResponse submitBus(RuntimeBusCreateRequest request) throws Exception {
        System.out.println(request);
        RuntimeBusServiceResponse response=new RuntimeBusServiceResponse();
        BeanUtils.copyProperties(request,response);
        List<String> args=new LinkedList<>();
        //TableResponse tableResponse=tableService.findById(request.getTableId());
        args.add("--symptom1="+request.getSymptom1());
        args.add("--symptom2="+request.getSymptom2());
        args.add("--symptom3="+request.getSymptom3());
        args.add("--symptom4="+request.getSymptom4());
        args.add("--symptom5="+request.getSymptom5());


        RuntimeTaskRequest runtimeTaskRequest=new RuntimeTaskRequest();
        // runtimeTaskRequest.setTaskType(TaskType.runtime.toString());
        // runtimeTaskRequest.setArgs(args);
        // runtimeTaskRequest.setBizId(request.getBusId());
        runtimeTaskRequest.setPyPath("F:/Code/Disease-prediction-using-Machine-Learning-master/test4.py");
        runtimeTaskRequest.setArgs(args);
        System.out.println("Python脚本路径：" + runtimeTaskRequest.getPyPath());

        RuntimeTaskResponse taskResponse=runtimeTaskService.submitTask(runtimeTaskRequest);
        // response.setTaskCreateTime(new Timestamp(taskResponse.getTaskFinishTime()));
        response.setRes(taskResponse.getRes());
        return response;
    }

    @Override
    public RuntimeBusServiceResponseSingleHeart submitBus1(RuntimeBusCreateRequestSingleHeart request) throws Exception {
        System.out.println(request);
        RuntimeBusServiceResponseSingleHeart response=new RuntimeBusServiceResponseSingleHeart();
        BeanUtils.copyProperties(request,response);
        List<String> args=new LinkedList<>();
        //TableResponse tableResponse=tableService.findById(request.getTableId());
        args.add("--age="+request.getAge());
        args.add("--sex="+request.getSex());
        args.add("--cp="+request.getCp());
        args.add("--trestbps="+request.getTrestbps());
        args.add("--chol="+request.getChol());
        args.add("--fbs="+request.getFbs());
        args.add("--restecg="+request.getRestecg());
        args.add("--thalach="+request.getThalach());
        args.add("--exang="+request.getExang());
        args.add("--oldpeak="+request.getOldpeak());
        args.add("--slope="+request.getSlope());
        args.add("--ca="+request.getCa());
        args.add("--thal="+request.getThal());



        RuntimeTaskRequest runtimeTaskRequest=new RuntimeTaskRequest();
        runtimeTaskRequest.setPyPath("F:/Code/Heart-Disease-Prediction-master/heart_code-test1.1.1.PY");
        runtimeTaskRequest.setArgs(args);
        System.out.println("Python脚本路径：" + runtimeTaskRequest.getPyPath());

        RuntimeTaskResponse taskResponse=runtimeTaskService.submitTask(runtimeTaskRequest);
        // response.setTaskCreateTime(new Timestamp(taskResponse.getTaskFinishTime()));
        response.setRes(taskResponse.getRes());
        return response;
    }

    @Override
    public RuntimeBusServiceResponseSingleHeart submitBus2(int patientId) throws Exception {
        List<PatientHeartDisease> request =patientHeartDiseaseService.getHeartDiseaseByPatientId(patientId);
        RuntimeBusServiceResponseSingleHeart response=new RuntimeBusServiceResponseSingleHeart();
        BeanUtils.copyProperties(request,response);
        List<String> args=new LinkedList<>();
        //TableResponse tableResponse=tableService.findById(request.getTableId());
        for (PatientHeartDisease patient : request) {
            args.add("--age=" + patient.getAge());
            args.add("--sex="+patient.getSex());
            args.add("--cp="+patient.getCp());
            args.add("--trestbps="+patient.getTrestbps());
            args.add("--chol="+patient.getChol());
            args.add("--fbs="+patient.getFbs());
            args.add("--restecg="+patient.getRestecg());
            args.add("--thalach="+patient.getThalach());
            args.add("--exang="+patient.getExang());
            args.add("--oldpeak="+patient.getOldpeak());
            args.add("--slope="+patient.getSlope());
            args.add("--ca="+patient.getCa());
            args.add("--thal="+patient.getThal());
        }

        RuntimeTaskRequest runtimeTaskRequest=new RuntimeTaskRequest();
        runtimeTaskRequest.setPyPath("F:/Code/Heart-Disease-Prediction-master/heart_code-test1.1.1.PY");
        runtimeTaskRequest.setArgs(args);
        System.out.println("Python脚本路径：" + runtimeTaskRequest.getPyPath());

        RuntimeTaskResponse taskResponse=runtimeTaskService.submitTask(runtimeTaskRequest);
        // response.setTaskCreateTime(new Timestamp(taskResponse.getTaskFinishTime()));
        response.setRes(taskResponse.getRes());
        return response;
    }

    @Override
    public RuntimeBusServiceResponseHearts submitBus3(String tableName) throws Exception {

        RuntimeBusServiceResponseHearts response=new RuntimeBusServiceResponseHearts();

        List<String> args = new ArrayList<>();
        args.add("--tableName");
        args.add(tableName);
        System.out.println(args);
        RuntimeTaskRequest runtimeTaskRequest=new RuntimeTaskRequest();

        runtimeTaskRequest.setPyPath("F:/Code/Heart-Disease-Prediction-master/heart_code-test2.1.PY");
        runtimeTaskRequest.setArgs(args);
        System.out.println("Python脚本路径：" + runtimeTaskRequest.getPyPath());

        RuntimeTaskResponse taskResponse=runtimeTaskService.submitTask(runtimeTaskRequest);
        response.setRes(taskResponse.getRes());
        return response;
    }
    //批量调用ckd模型
    public RuntimeBusServiceResponseHearts submitBus4(String tableName) throws Exception {

        RuntimeBusServiceResponseHearts response=new RuntimeBusServiceResponseHearts();

        List<String> args = new ArrayList<>();
        args.add("--tableName");
        args.add(tableName);
        System.out.println(args);
        RuntimeTaskRequest runtimeTaskRequest=new RuntimeTaskRequest();

        runtimeTaskRequest.setPyPath("F:/Code/CKD-Prediction-master/mutli_prediction.py");
        runtimeTaskRequest.setArgs(args);
        System.out.println("Python脚本路径：" + runtimeTaskRequest.getPyPath());

        RuntimeTaskResponse taskResponse=runtimeTaskService.submitTask(runtimeTaskRequest);
        response.setRes(taskResponse.getRes());
        return response;
    }

}
