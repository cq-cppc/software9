package com.cqupt.software_9.controller;

import com.cqupt.software_9.common.UploadResult;
import com.cqupt.software_9.service.DataTableManagerService;
import com.cqupt.software_9.service.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RequestMapping("/File")
@RestController
public class FileController {

    @Resource
    private  FileService fileService;

    @Resource
    private DataTableManagerService dataTableManagerService;

    /**
     * 文件上传
     *
     * @param file
     * @param newName
     * @param disease  疾病名称
     * @return 上传结果
     */

    @PostMapping("/upload")
    public UploadResult uploadFile(@RequestPart("file") MultipartFile file,
                                   @RequestParam("newName") String newName,
                                   @RequestParam("disease") String disease) {
        try {
            UploadResult res =  fileService.fileUpload(file, newName,disease);
            dataTableManagerService.updateDataTable(newName,disease,"胡双",100,"66");
            return res;
        } catch (Exception e) {
            UploadResult result =new UploadResult();
            result.setE(e);
            return result;
        }
    }
}
