package com.cqupt.software_9.service;

import com.cqupt.software_9.entity.Patient;
import com.cqupt.software_9.entity.PatientHeartAll;

import java.util.List;

public interface PatientHeartAllService {

    public List<PatientHeartAll> getAllPatientsHeartAll();
    PatientHeartAll getPatientHeartAllById(Integer id);
    String addPatient(PatientHeartAll patientHeartAll);
    void updatePatient(PatientHeartAll patientHeartAll);


    String deletePatient(Integer id);


}