package com.cqupt.software_9.controller;

import com.cqupt.software_9.entity.Patient;
import com.cqupt.software_9.entity.PatientHeartDisease;
import com.cqupt.software_9.service.PatientHeartDiseaseService;
import com.cqupt.software_9.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/Patient")

@RestController
public class PatientController {
    @Resource
    private  PatientService patientService;

    @Resource
    private PatientHeartDiseaseService patientHeartDiseaseService;

    /**
     * Author:陈鹏
     *时间：2023.6.23
     *查询病人基础信息
     *@return
     * */
    @GetMapping("/patient")
    public List<Patient> getAllPatient() {
        return patientService.getAllPatients();
    }


    /**
     * Author:陈鹏
     *时间：2023.6.23
     *查询病人完整信息
     *@return
     * */
    @GetMapping("/patients")
    public List<Map<String, Object>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Patient patient : patients) {
            List<PatientHeartDisease> heartDiseases = patientHeartDiseaseService.getHeartDiseaseByPatientId(patient.getPatientId());
            Map<String, Object> mergedData = new HashMap<>();

            mergedData.putAll(patient.toMap()); // Add patient fields

            if (!heartDiseases.isEmpty()) {
                PatientHeartDisease heartDisease = heartDiseases.get(0); // Assuming only one heart disease per patient
                mergedData.putAll(heartDisease.toMap()); // Add heartDisease fields
            }

            result.add(mergedData);
        }

        System.out.println(result);
        return result;
    }

}