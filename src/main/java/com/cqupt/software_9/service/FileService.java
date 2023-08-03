package com.cqupt.software_9.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqupt.software_9.common.UploadResult;
import com.cqupt.software_9.entity.Diseases;
import com.cqupt.software_9.service.Request.Query;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    public UploadResult fileUpload(MultipartFile file, String newName, String disease) throws IOException;
}

