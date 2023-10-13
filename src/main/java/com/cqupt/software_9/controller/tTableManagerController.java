package com.cqupt.software_9.controller;


import com.cqupt.software_9.common.R;
import com.cqupt.software_9.common.TableManagerDTO;
import com.cqupt.software_9.common.UploadResult;
import com.cqupt.software_9.mapper.dataTableManagerMapper;
import com.cqupt.software_9.entity.dataTable;
import com.cqupt.software_9.entity.tTableField;
import com.cqupt.software_9.entity.tTableFields;
import com.cqupt.software_9.service.DataTableManagerService;
import com.cqupt.software_9.service.tTableManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tTableManager")
public class tTableManagerController {

    @Autowired
    private tTableManagerService tTableManagerService;

    @Autowired
    private DataTableManagerService dataTableManagerService;

    @Autowired
    private dataTableManagerMapper dataTableManagerMapper;

    /**
     * 上传文件的字段分类
     * @param tableManagerDTO
     * @return
     */
    @PostMapping("/insertTableManager")
    public UploadResult insertTableManager(@RequestBody TableManagerDTO tableManagerDTO) {
        try {
            tTableManagerService.insertTableManager(tableManagerDTO);
            List<dataTable> d=dataTableManagerService.upalldata();
            UploadResult res =new UploadResult();
            res.setRes(d);
            return res;
        } catch (Exception e) {
            UploadResult res = new UploadResult();
            res.setCode(500);
            res.setE(e);
            dataTableManagerService.deletename(tableManagerDTO.getTableName());
            dataTableManagerMapper.deletetablename(tableManagerDTO.getTableName());
            return res;
        }
    }

    /**
     * 获得全表名
     * @return
     */
    @GetMapping("/getTableNames/{uid}")
    public R getTableNames(@PathVariable Integer uid){
        List<String> tableNames = tTableManagerService.getTableNames(uid);

        return new R<>(200,"查询成功",tableNames);
    }

    /**
     * 获得字段和属性值
     * @param table_name
     * @return
     */
    @GetMapping("/getFiledNamesByTableName")
    public R getFiledNamesByTableName(@RequestParam("table_name") String table_name){
        List<tTableField> fields = tTableManagerService.getFiledNamesByTableName(table_name);
        for(tTableField field : fields){
            if(field.getIs_demography() == null || field.getIs_demography().equals("")){
                field.setIs_demography("0");
            }
            if(field.getIs_sociology() == null || field.getIs_sociology().equals("")){
                field.setIs_sociology("0");
            }
            if(field.getIs_physiological() == null || field.getIs_physiological().equals("")){
                field.setIs_physiological("0");
            }
            if(field.getIs_label() == null || field.getIs_label().equals("")){
                field.setIs_label("0");
            }
        }
        String disease = dataTableManagerService.getdiseasebyname(table_name);

        tTableFields field = new tTableFields();
        System.out.println(disease);
        field.setField(fields);
        field.setDisease(disease);
        return new R<>(200,"查询成功",field);
    }

    @PostMapping("/updateFieldValues")
    public R updateFieldValues(@RequestParam("table_name") String table_name, @RequestBody tTableField field){
        boolean b  = tTableManagerService.updateFieldValues(table_name, field);

        if (b == true) {
            return new R<>(200 , "更新字段成功", null);
        }else {
            return new R<>(500,"更新失败",null);
        }
    }
}
