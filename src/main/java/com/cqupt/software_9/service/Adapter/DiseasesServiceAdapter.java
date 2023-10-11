package com.cqupt.software_9.service.Adapter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqupt.software_9.entity.Diseases;
import com.cqupt.software_9.service.DiseasesService;
import com.cqupt.software_9.service.Request.Query;
import com.cqupt.software_9.service.imp.DiseasesServiceImpl;

import java.util.List;

public class DiseasesServiceAdapter implements DiseasesService {
    private DiseasesServiceImpl diseasesServiceImpl;

    @Override
    public IPage<Diseases> selectDiseasesPage(Query query) {
        return diseasesServiceImpl.selectDiseasesPage(query);
    }

    @Override
    public List<String> getInfobycode(String code) {
        return null;
    }

    @Override
    public List<Diseases> getAllDiseases() {
        return null;
    }

    @Override
    public Diseases findByCode(String code) {
        return null;
    }

    @Override
    public List<Diseases> findBypart_name(String partName) {
        return null;
    }

    @Override
    public String findpartNamebyCode(String code) {
        return null;
    }

    @Override
    public String findnamebyCode(String code) {
        return null;
    }

    @Override
    public String findsympbyCode(String code) {
        return null;
    }

    @Override
    public String finddptmentbyCode(String code) {
        return null;
    }

    @Override
    public String findpreventbyCode(String code) {
        return null;
    }


}

