package com.cqupt.software_9.controller;

import com.cqupt.software_9.common.R;
import com.cqupt.software_9.dao.ckd.CkdManagerMapper;
import com.cqupt.software_9.dao.ckd.CkdTableManagerMapper;
import com.cqupt.software_9.dao.data.DataManagerMapper;
import com.cqupt.software_9.dao.mysql.DatabaseManagerMapper;
import com.cqupt.software_9.entity.DataManager;
import com.cqupt.software_9.entity.DatabaseManager;
import com.cqupt.software_9.service.DataManagerService;
import com.cqupt.software_9.service.Request.Query;
import com.cqupt.software_9.service.TableManagerService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/DataManager")
public class DataManagerController {
    @Autowired
    private DataManagerService dataManagerService;

    @Autowired
    private TableManagerService tableManagerService;

    private DataManagerMapper dataManagerMapper;

    private CkdManagerMapper ckdManagerMapper;

    private DatabaseManagerMapper databaseManagerMapper;

    private CkdTableManagerMapper ckdTableManagerMapper;

    public DataManagerController(DataManagerMapper dataManagerMapper,CkdManagerMapper ckdManagerMapper,DatabaseManagerMapper databaseManagerMapper,CkdTableManagerMapper ckdTableManagerMapper) {
        this.dataManagerMapper = dataManagerMapper;
        this.ckdManagerMapper=ckdManagerMapper;
        this.databaseManagerMapper=databaseManagerMapper;
        this.ckdTableManagerMapper=ckdTableManagerMapper;
    }

    @GetMapping("/heart")
    public List<DataManager> getheartDataWithoutresult() {
        return dataManagerService.getDatawithoutresult();
    }

    //    分页查询heart
    @GetMapping("/data/heart")
    public PageInfo<DataManager> getheartdatawithoutresult(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize){
        return dataManagerService.getDatawithoutresult(page, pageSize);
    }

    @GetMapping("/ckd")
    public List<DataManager> getckdDataWithoutresult() {
        return ckdManagerMapper.getckdManagerwithoutresult();
    }

    @GetMapping("/database")
    public  List<DatabaseManager> getall(){return databaseManagerMapper.getall();}

    @PostMapping("/data")
    public List<DataManager> getDetail(@RequestBody Query Q) {
        String databasename = Q.getDatabasename();
        if (databasename.equals("dataandresult")) {
            return getheartDataWithoutresult();
        } else if (databasename.equals("ckd")) {
            return getckdDataWithoutresult();
        }
        throw new IllegalArgumentException("Invalid databasename: " + databasename);
    }

//    @GetMapping("/data")
//    public List<DataManager> getDetails(@RequestParam("databasename") String databasename) {
//        if (databasename.equals("dataandresult")) {
//            return getheartDataWithoutresult();
//        } else if (databasename.equals("ckd")) {
//            return getckdDataWithoutresult();
//        }
//        throw new IllegalArgumentException("Invalid databasename: " + databasename);
//    }

    @GetMapping("/getInfoByTableName")
    public R<List<Map<String,Object>>> getInfoByTableName(@RequestParam("basename") String basename, @RequestParam("tablename") String tablename){

            if (basename.equals("dataandresult"))
            {tablename = tablename.concat("_result");
                System.out.println(tablename);
//            tableName = tableName.replace("\"", "");
            List<Map<String, Object>> res = tableManagerService.getInfoByTableName(tablename);
            return new R<>(200, "成功", res);
            }else if(basename.equals("ckd"))
            {
                tablename = tablename.concat("_result");
                System.out.println(tablename);
                List<Map<String, Object>> res = ckdTableManagerMapper.getInfoByTableName(tablename);
                return new R<>(200, "成功", res);
            }


        throw new IllegalArgumentException("Invalid tableName: " + tablename);

    }


}
