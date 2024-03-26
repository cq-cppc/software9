package com.cqupt.software_9.tool;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.util.*;

@Log4j2
public class PythonRun {
    private String environment = "D:\\Anaconda\\envs\\test\\python.exe";
    private String root = null;
    private String cache = null;
    private boolean autoRemoveCache = true;

    public String run(String path, List<String> args) throws Exception {
//        path = createNewPy(path);
        List<String> inputArgs = new LinkedList<>(Arrays.asList(environment, path));//设定命令行
        inputArgs.addAll(args);
        inputArgs.removeIf(Objects::isNull);//移除可能的 null 值

        System.out.println("===="+inputArgs);
        Process proc;
        String line;
        StringBuilder result = new StringBuilder();
        System.out.println("____"+inputArgs.toArray(new String[0]));
        System.out.println("____+"+JSONObject.toJSONString(inputArgs.toArray(new String[0])));
        proc = Runtime.getRuntime().exec(inputArgs.toArray(new String[0]));  //执行py文件
        BufferedReader in = getConsoleReader(proc.getInputStream());
        while ((line = in.readLine()) != null) {
            result.append(line).append("\n");
        }
        int exitValue = proc.waitFor();
        log.info("Python exitValue：" + exitValue);
        if (exitValue == 0) log.info("Python 代码结果：" + result);
        else {
            BufferedReader errorReader = getConsoleReader(proc.getErrorStream());
            while ((line = errorReader.readLine()) != null) {
                log.error(line);
            }
            throw new Exception("脚本运行出错详见日志");
        }
        in.close();
        return result.toString();
    }

    public String run1(String path, String request) throws Exception {
//        path = createNewPy(path);
        List<String> inputArgs = new LinkedList<>(Arrays.asList(environment, path));//设定命令行
        inputArgs.add(request);
        inputArgs.removeIf(Objects::isNull);//移除可能的 null 值

        System.out.println(inputArgs+"检验");
        Process proc;
        String line;
        StringBuilder result = new StringBuilder();
        System.out.println(inputArgs.toArray(new String[0]));
        System.out.println(JSONObject.toJSONString(inputArgs.toArray(new String[0])));
        proc = Runtime.getRuntime().exec(inputArgs.toArray(new String[0]));  //执行py文件
        BufferedReader in = getConsoleReader(proc.getInputStream());
        while ((line = in.readLine()) != null) {
            result.append(line).append("\n");
        }
        int exitValue = proc.waitFor();
        log.info("Python exitValue：" + exitValue);
        if (exitValue == 0) log.info("Python 代码结果：" + result);
        else {
            BufferedReader errorReader = getConsoleReader(proc.getErrorStream());
            while ((line = errorReader.readLine()) != null) {
                log.error(line);
            }
            throw new Exception("脚本运行出错详见日志");
        }
        in.close();
        return result.toString();
    }

    public String runScript(String path, List<String> args) throws Exception {
        List<String> inputArgs = new LinkedList<>(Arrays.asList(environment, path));
        inputArgs.addAll(args);
        inputArgs.removeIf(Objects::isNull);

        StringBuilder builder = new StringBuilder();
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(inputArgs.toArray(new String[0]));

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));

            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                //builder.append(System.lineSeparator());
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

    public String publicAl(String path, String tableName, String target, String[] fea,
                           String algorithmName, Map<String, String> algorithmAttributes) throws Exception {
//        path = createNewPy(path);
        String feaString = String.join(",", fea);
        List<String> inputArgs = new LinkedList<>(Arrays.asList(environment, path, tableName, target, feaString,algorithmName));//设定命令行

        for (Map.Entry<String, String> entry : algorithmAttributes.entrySet()) {
            inputArgs.add(entry.getKey() + "=" + entry.getValue());
        }
        inputArgs.removeIf(Objects::isNull);//移除可能的 null 值

        Process proc;
        String line;
        StringBuilder result = new StringBuilder();
        proc = Runtime.getRuntime().exec(inputArgs.toArray(new String[0]));  //执行py文件
        BufferedReader in = getConsoleReader(proc.getInputStream());
        while ((line = in.readLine()) != null) {
            result.append(line).append("\n");
        }
        int exitValue = proc.waitFor();
        log.info("Python exitValue：" + exitValue);
        if (exitValue == 0) log.info("Python 代码结果：" + result);
        else {
            BufferedReader errorReader = getConsoleReader(proc.getErrorStream());
            while ((line = errorReader.readLine()) != null) {
                log.error(line);
            }
            throw new Exception("脚本运行出错详见日志");
        }
        in.close();
        return result.toString();
    }
    private String createNewPy(String path) throws IOException {
        File file = new File(path);
        if (file.isFile()) {
            String result = loadTxt(file);
            if (root != null) {
                result = "import sys\n" +
                        "sys.path.append(\"" + root + "\")\n" + result;
            }
            String save = cache + file.getName();
            saveTxt(save, result);
            return save;
        }
        return null;
    }

    private static File saveTxt(String filename, String string) {
        File file = new File(filename);
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            out.write(string);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    private String loadTxt(File file) {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String str;
            while ((str = in.readLine()) != null) {
                result.append(str).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public boolean isAutoRemoveCache() {
        return autoRemoveCache;
    }

    public void setAutoRemoveCache(boolean autoRemoveCache) {
        this.autoRemoveCache = autoRemoveCache;
    }

    private BufferedReader getConsoleReader(InputStream inputStream) {
        return new BufferedReader(
                new InputStreamReader(inputStream)
        );
    }


}

