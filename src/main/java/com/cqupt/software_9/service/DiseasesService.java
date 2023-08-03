package com.cqupt.software_9.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqupt.software_9.entity.Diseases;

import com.cqupt.software_9.service.Request.Query;

import java.util.List;

public interface DiseasesService {

    IPage<Diseases> selectDiseasesPage(Query query);

    List<String> getInfobycode(String code);

    List<Diseases> getAllDiseases();
    Diseases findByCode(String code);
    List<Diseases> findBypart_name(String partName);
    String findpartNamebyCode(String code);
    String findnamebyCode(String code);
    String findsympbyCode(String code);
    String finddptmentbyCode(String code);
    String findpreventbyCode(String code);
}