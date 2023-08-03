package com.cqupt.software_9.service.Adapter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqupt.software_9.common.UploadResult;
import com.cqupt.software_9.entity.Diseases;
import com.cqupt.software_9.service.DiseasesService;
import com.cqupt.software_9.service.FileService;
import com.cqupt.software_9.service.Request.Query;
import com.cqupt.software_9.service.imp.DiseasesServiceImpl;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public class FileServiceAdapter implements FileService {


    @Override
    public UploadResult fileUpload(MultipartFile file, String newName, String disease) throws IOException {
        return null;
    }
}

