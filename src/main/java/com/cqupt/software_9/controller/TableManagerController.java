package com.cqupt.software_9.controller;

import com.cqupt.software_9.common.R;
import com.cqupt.software_9.dao.mysql.TableManagerMapper;
import com.cqupt.software_9.service.PageService;
import com.cqupt.software_9.service.TableManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/TableManager")
public class TableManagerController {
    @Autowired
    private TableManagerService tableManagerService;
    @Autowired
    private PageService pageService;

    private TableManagerMapper tableManagerMapper;

    public TableManagerController(TableManagerMapper tableManagerMapper) {
        this.tableManagerMapper = tableManagerMapper;
    }




    /**
     * 根据表名获取到前面1000行信息
     *
     * @param tableName
     * @return
     */
//    @PostMapping("/getInfoByTableName")
//    public R<List<Map<String,Object>>> getInfoByTableName(@RequestBody String tableName){
//        System.out.println(tableName);
//        List<Map<String, Object>> res = pageService.getInfoByTableName(tableName);
//        return new R<>(200, "成功", res);
//    }
    @PostMapping("/getInfoByTableName")
    public R<List<Map<String,Object>>> getInfoByTableName(@RequestBody String tableName){
        tableName = tableName.replace("\"", "");
        List<Map<String, Object>> res = tableManagerService.getInfoByTableName(tableName);
        return new R<>(200, "成功", res);
    }
    /**
     * 获取heart表名
     *
     * @
     * @return
     */
    @GetMapping("/findTablesHeartWithoutResult")
    public List<String> findTablesHeartWithoutResult(){
        return tableManagerService.findTablesHeartWithoutResult();
    }
    /**
     * 获取heart结果表名
     *
     * @
     * @return
     */
    @GetMapping("/findTablesWithHeartAndResult")
    public List<String> findTablesWithHeartAndResult(){
        return tableManagerService.findTablesWithHeartAndResult();
    }
    /**
     * 获取ckd表名
     *
     * @
     * @return
     */
    @GetMapping("/findTablesCkdWithoutResult")
    public List<String> findTablesCkdWithoutResult(){
        return tableManagerService.findTablesCkdWithoutResult();
    }
    /**
     * 获取ckd结果表名
     *
     * @
     * @return
     */
    @GetMapping("/findTablesWithCkdAndResult")
    public List<String> findTablesWithCkdAndResult(){
        return tableManagerService.findTablesWithCkdAndResult();
    }

    /**
     * 获取heart的所有表名+count
     *
     * /TableManager
     * @return
     */
    @GetMapping("/findTablesHeartWithoutResultCount")
    public List<Map<String, Object>> findTablesHeartWithoutResultWithCount() {
        return tableManagerService.findTablesHeartWithoutResultWithCount();
    }
    /**
     * 获取ckd的所有表名+count
     *
     * /TableManager
     * @return
     */
    @GetMapping("/findTablesckdWithoutResultCount")
    public List<Map<String, Object>> findTablesCkdWithoutResultWithCount() {
        return tableManagerService.findTablesckdWithoutResultWithCount();
    }



    /**
     *
     *
     *
     *
     */
    @GetMapping("/statistic/{tableName}")
    public List<Map<String, BigInteger>> countPredictedTargetValuesByTableName(@PathVariable String tableName) {
        return tableManagerMapper.countPredictedTargetValuesByTableName(tableName);
    }


    /**
     *统计heart结果表中的1和0的个数
     *
     *
     *
     */
    @GetMapping("/result-heart-statistics")
    public List<Map<String, List<Map<String, BigInteger>>>> getResultStatistics() {
        List<String> tableNames = tableManagerService.findTablesWithHeartAndResult();
        List<Map<String, List<Map<String, BigInteger>>>> result = new ArrayList<>();

        for (String tableName : tableNames) {
            Map<String, List<Map<String, BigInteger>>> tableResult = new HashMap<>();
            List<Map<String, BigInteger>> statistics = tableManagerMapper.countPredictedTargetValuesByTableName(tableName);
            tableResult.put(tableName, statistics);
            result.add(tableResult);
        }

        return result;
    }

    @GetMapping("/result-ckd-statistics")
    public List<Map<String, List<Map<String, BigInteger>>>> getckdResultStatistics() {
        List<String> tableNames = tableManagerService.findTablesWithCkdAndResult();
        List<Map<String, List<Map<String, BigInteger>>>> result = new ArrayList<>();

        for (String tableName : tableNames) {
            Map<String, List<Map<String, BigInteger>>> tableResult = new HashMap<>();
            List<Map<String, BigInteger>> statistics = tableManagerMapper.countPredictedTargetValuesByTableName(tableName);
            tableResult.put(tableName, statistics);
            result.add(tableResult);
        }

        return result;
    }

}
