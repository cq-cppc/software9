package com.cqupt.software_9.controller;


import com.cqupt.software_9.common.Result;
import com.cqupt.software_9.service.Request.RuntimeTaskRequest;
import com.cqupt.software_9.service.Response.RuntimeTaskResponse;
import com.cqupt.software_9.service.RuntimeTaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;


@RestController
@RequestMapping("/scripts")
public class PythonScriptController {
    @Value("src/main/resources/Scripts/get_statics.py")
    private String scriptPyPath;

    @Resource
    private RuntimeTaskService runtimeTaskService;

    @GetMapping("/get_fill_rate")
    public static String pyfileUpload(@RequestParam String tablename) {
        StringBuilder builder = new StringBuilder();
        Process process = null;
        try {
            process = Runtime.getRuntime()
                    .exec("D:\\Anaconda\\envs\\test\\python.exe src/main/resources/Scripts/get_fill_rate.py "+tablename);  // 确保提供正确的Python脚本路径和Python解释器

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));

            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.lineSeparator());
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                // 如果Python脚本没有成功执行，你可能想要捕捉错误输出
                reader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "UTF-8"));
                while ((line = reader.readLine()) != null) {
                    System.err.println(line);
                }
                throw new RuntimeException("Execution of the Python script failed!");
            }
        } catch (Exception e) {
            e.printStackTrace(); // 打印错误堆栈信息
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
        return builder.toString().trim();  // 注意：.trim()移除了末尾的系统换行符
    }

    @GetMapping("/get_stastic")
    public Result getStastic(@RequestParam String tablename) throws Exception {
        RuntimeTaskRequest runtimeTaskRequest=new RuntimeTaskRequest();
        List<String> args=new LinkedList<>();
        args.add("--tablename="+tablename);
        runtimeTaskRequest.setPyPath(scriptPyPath);
        runtimeTaskRequest.setArgs(args);
        RuntimeTaskResponse taskResponse=runtimeTaskService.submitStastic(runtimeTaskRequest);
        return Result.success(taskResponse.getRes());
    }

}