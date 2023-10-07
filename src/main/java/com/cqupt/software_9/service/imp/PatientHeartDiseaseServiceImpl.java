package com.cqupt.software_9.service.imp;

import com.cqupt.software_9.dao.mysql.PatientHeartDiseaseMapper;
import com.cqupt.software_9.entity.PatientHeartDisease;
import com.cqupt.software_9.service.Adapter.PatientHeartDiseaseServiceAdapter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PatientHeartDiseaseServiceImpl extends PatientHeartDiseaseServiceAdapter {
    @Resource
    private PatientHeartDiseaseMapper patientHeartDiseaseMapper;

    public List<PatientHeartDisease> getHeartDiseaseByPatientId(int patientId) {
        return patientHeartDiseaseMapper.getHeartDiseaseByPatientId(patientId);
    }

}
