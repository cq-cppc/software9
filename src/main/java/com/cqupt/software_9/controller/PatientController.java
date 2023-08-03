package com.cqupt.software_9.controller;

import com.cqupt.software_9.entity.Patient;
import com.cqupt.software_9.entity.PatientHeartDisease;
import com.cqupt.software_9.entity.User;
import com.cqupt.software_9.service.PatientHeartDiseaseService;
import com.cqupt.software_9.service.PatientService;
import com.cqupt.software_9.service.Request.Query;
import com.cqupt.software_9.service.Response.Result;
import com.cqupt.software_9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/Patient")

@RestController
public class PatientController {
    private final PatientService patientService;
    private PatientHeartDiseaseService patientHeartDiseaseService;

    @Autowired
    public PatientController(PatientService patientService, PatientHeartDiseaseService patientHeartDiseaseService)
    {
        this.patientService = patientService;
        this.patientHeartDiseaseService=patientHeartDiseaseService;
    }
//    @Autowired
//    public PatientController(PatientService patientService) {
//        this.patientService = patientService;
//    }
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