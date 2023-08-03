package com.cqupt.software_9.controller;



import com.cqupt.software_9.common.R;
import com.cqupt.software_9.common.UploadResult;
import com.cqupt.software_9.entity.dataTable;
import com.cqupt.software_9.service.DataTableManagerService;
import com.cqupt.software_9.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/DataTable")
public class DataTableManagerController {

    @Autowired
    private DataTableManagerService dataTableManagerService;

    @Autowired
    private FileService fileService;




    public DataTableManagerController(DataTableManagerService dataTableManagerService, FileService fileService) {
        this.dataTableManagerService = dataTableManagerService;
        this.fileService=fileService;
    }

    /**
     * 获取表管理表所有信息
     *
     *
     * @return
     */
    @GetMapping("/upall")
    public List<dataTable> upall() {
        return dataTableManagerService.upalldata();

    }
    /**
     * 获取表管理表表名
     *
     *
     * @return
     */
    @GetMapping("/name")
    public List<String> name() {
        return dataTableManagerService.upname();

    }
    /**
     * 根据id获取表管理表的所有信息
     *
     *
     * @return
     */
    @GetMapping("/upbyid/{id}")
    public dataTable up(@PathVariable int id) {
        System.out.println(id);
        return dataTableManagerService.upbyid(id);

    }
//    @GetMapping("/delete1/{id}")
//    public void a(@PathVariable int id){
//        dataTableManagerService.deletebyid(id);
//    }
//    @GetMapping("/delete2")
//    public void b(@RequestParam("tableName") String tableName){
//        dataTableManagerService.deletename(tableName);
//    }

    /**
     * 根据id删除表管理表和对应的表
     *
     *
     * @return
     */
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteData(@PathVariable int id) {
        try {
            // Step 1: Get the data to be deleted from the table management table
            dataTable dataToDelete = dataTableManagerService.upbyid(id);

            if (dataToDelete == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data not found with id: " + id);
            }

            // Get the table name from the retrieved data
            String tablename = dataToDelete.getTable_name();

            // Step 2: Delete the corresponding table
            // Perform the deletion operation on the table 'tableName' using your preferred method.
            // For example, you can use JDBC or JPA to execute a delete query on the corresponding table.
            dataTableManagerService.deletename(tablename);
            // Step 3: Delete the data from the table management table
            dataTableManagerService.deletebyid(id);
            List<dataTable> res=dataTableManagerService.upalldata();


            return ResponseEntity.ok("Data with id " + id + " and the corresponding table have been deleted."+res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting data: " + e.getMessage());
        }
    }
    /**
     * 更新数据
     *
     *
     * @return
     */
    @PostMapping("/updata")
    public ResponseEntity<String> updata(dataTable a){
        try{
            dataTableManagerService.updata(a);
            List<dataTable> res= dataTableManagerService.upalldata();
            return ResponseEntity.ok("更新成功"+res);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("更新失败"+e);
        }
    }

//    @GetMapping("/updata")
//    public void ab(String newName, String disease){
//        dataTableManagerService.updateDataTable(newName,disease);
//    }

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
    @GetMapping("/getInfoByTableName")
    public R<List<Map<String,Object>>> getInfoByTableName(@RequestParam("tableName") String tableName){
        tableName = tableName.replace("\"", "");
        List<Map<String, Object>> res = dataTableManagerService.getInfoByTableName(tableName);
        return new R<>(200, "成功", res);
    }



    @PostMapping("/update")
    public Boolean tablesUpdate(@RequestBody dataTable tables){
        return dataTableManagerService.updateById(tables);
    }
    @PostMapping("/insert")
    public  Boolean tableInsert(@RequestBody dataTable tables){
        return dataTableManagerService.save(tables);
    }
//    @GetMapping("/delete/{id}")
//    public boolean tablesDelete(@PathVariable int id){
//        QueryWrapper<dataTable> queryWrapper  =new QueryWrapper<>();
//        queryWrapper.eq("id",id);
//        return dataTableManagerService.remove(queryWrapper);
//    }


    /**
     * 文件上传
     *
     * @param file
     * @param newName
     * @return
     */

//    @PostMapping("/upload")
//    public UploadResult uploadFile(@RequestPart("file") MultipartFile file, @RequestParam("newName") String newName, @RequestParam("disease") String disease) {
//        try {
//            UploadResult res =  fileService.fileUpload(file, newName,disease);
//            dataTableManagerService.updateDataTable(newName,disease);
//            return res;
//        } catch (Exception e) {
//            UploadResult result =new UploadResult();
//            result.setE(e);
//            return result;
//        }
//    }


    @PostMapping("/upload")
    public UploadResult uploadFile(@RequestPart("file") MultipartFile file, @RequestParam("newName") String newName, @RequestParam("disease") String disease) throws IOException {
            UploadResult res =  fileService.fileUpload(file, newName,disease);
            dataTableManagerService.updateDataTable(newName,disease);
            return res;
    }



//    //改：文件上传，文件和datatable
//    public List<dataTable> insert(dataTable tables){
//        dataTableManagerService.insert(tables);
//        return dataTableManagerService.upalldata();
//    }

//    SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
//    @Value("${gorit.file.root.path1}")
//    private String filePath;
//    // 文件上传 （可以多文件上传）
//    @PostMapping("/upfile")
//    public Result fileUploads(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
//        // 得到格式化后的日期
//        String format = sdf.format(new Date());
//        // 获取上传的文件名称
//        String fileName = file.getOriginalFilename();
//        // 时间 和 日期拼接
//        String newFileName = format + "_" + fileName;
//        // 得到文件保存的位置以及新文件名
//        File dest = new File(filePath + newFileName);
//        try {
//            // 上传的文件被保存了
//            file.transferTo(dest);
//            // 打印日志
//
////            log.info("上传成功，当前上传的文件保存在 {}",filePath + newFileName);
//            // 自定义返回的统一的 JSON 格式的数据，可以直接返回这个字符串也是可以的。
//            return Result.succ("上传成功");
//        } catch (IOException e) {
////            log.error(e.toString());
//        }
//        // 待完成 —— 文件类型校验工作
//        return Result.fail("上传错误");
//    }

        /**
         * 查询索引
         *
         * @param tableName
         * @param targetcolumn
         * @return
         */
        @GetMapping("/test")
        public Integer a(@RequestParam("tableName") String tableName, @RequestParam("targetcolumn") String targetcolumn){
            return dataTableManagerService.findTargetColumnIndex(tableName,targetcolumn);
        }

}