package com.cqupt.software_9.service;

import com.cqupt.software_9.entity.Patient;
import com.cqupt.software_9.entity.PatientHeartDisease;

import java.util.List;

public interface PatientHeartDiseaseService {

    public List<PatientHeartDisease> getHeartDiseaseByPatientId(int patientId);
}