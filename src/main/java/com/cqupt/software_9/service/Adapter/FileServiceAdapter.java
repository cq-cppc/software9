package com.cqupt.software_9.service.Adapter;

import com.cqupt.software_9.common.UploadResult;
import com.cqupt.software_9.service.FileService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileServiceAdapter implements FileService {


    @Override
    public UploadResult fileUpload(MultipartFile file, String newName, String disease) throws IOException {
        return null;
    }
}

