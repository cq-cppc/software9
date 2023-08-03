package com.cqupt.software_9.controller;

import com.cqupt.software_9.entity.Patient;
import com.cqupt.software_9.entity.PatientHeartDisease;
import com.cqupt.software_9.service.HeartManagerService;
import com.cqupt.software_9.service.PatientHeartDiseaseService;
import com.cqupt.software_9.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/PatientHeart")

@RestController
public class PatientHeartDiseaseController {
    private PatientHeartDiseaseService patientHeartDiseaseService;



    public PatientHeartDiseaseController(PatientHeartDiseaseService patientHeartDiseaseService)
    {
        this.patientHeartDiseaseService=patientHeartDiseaseService;
    }


    @GetMapping("/patients/{patientId}/heartdiseases")
    public List<PatientHeartDisease> getHeartDiseaseByPatientId(@PathVariable int patientId) {
        return patientHeartDiseaseService.getHeartDiseaseByPatientId(patientId);
    }



}