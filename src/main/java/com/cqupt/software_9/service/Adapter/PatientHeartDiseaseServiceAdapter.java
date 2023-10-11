package com.cqupt.software_9.service.Adapter;


import com.cqupt.software_9.entity.PatientHeartDisease;
import com.cqupt.software_9.service.PatientHeartDiseaseService;
import com.cqupt.software_9.service.imp.PatientHeartDiseaseServiceImpl;

import java.util.List;

public class PatientHeartDiseaseServiceAdapter implements PatientHeartDiseaseService {
     private PatientHeartDiseaseServiceImpl patientHeartDiseaseServiceimpl;

    @Override
    public List<PatientHeartDisease> getHeartDiseaseByPatientId(int patientId) {
        return patientHeartDiseaseServiceimpl.getHeartDiseaseByPatientId(patientId);
    }
}
