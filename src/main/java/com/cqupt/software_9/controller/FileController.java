package com.cqupt.software_9.controller;

import com.cqupt.software_9.common.UploadResult;
import com.cqupt.software_9.service.DataTableManagerService;
import com.cqupt.software_9.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/File")
@RestController
public class FileController {

    private final FileService fileService;

    private DataTableManagerService dataTableManagerService;

    @Autowired
    public FileController(FileService fileService,DataTableManagerService dataTableManagerService) {
        this.fileService = fileService;
        this.dataTableManagerService=dataTableManagerService;
    }

    /**
     * 文件上传
     *
     * @param file
     * @param newName
     * @return
     */

    @PostMapping("/upload")
    public UploadResult uploadFile(@RequestPart("file") MultipartFile file, @RequestParam("newName") String newName,@RequestParam("disease") String disease) {
        try {
            UploadResult res =  fileService.fileUpload(file, newName,disease);
            dataTableManagerService.updateDataTable(newName,disease);
            return res;
        } catch (Exception e) {
            UploadResult result =new UploadResult();
            result.setE(e);
            return result;
        }
    }
}
