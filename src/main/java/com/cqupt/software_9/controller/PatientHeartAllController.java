package com.cqupt.software_9.controller;

import com.cqupt.software_9.entity.PatientHeartAll;
import com.cqupt.software_9.service.PatientHeartAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RequestMapping("/PatientHeartAll")

@RestController
public class PatientHeartAllController {

    @Resource
    private PatientHeartAllService patientHeartAllService;

    /**
     * Author:陈鹏
     *时间：2023.6.25
     *查询心脏病病人所有信息
     *@return
     * */
    @GetMapping("/patient")
    public List<PatientHeartAll> getAllPatient() {
        return patientHeartAllService.getAllPatientsHeartAll();
    }

    @PostMapping("/field")
    public List<String> getAllPropertyNames() {
        return PatientHeartAll.getAllPropertyNames();
    }

    /**
     * 添加心脏病病人所有信息
     * @param
     * @return
     */
    @PostMapping("/add")
    public List<PatientHeartAll> addPatientHeartAll(@RequestBody PatientHeartAll patientHeartAll) {
         patientHeartAllService.addPatient(patientHeartAll);
        return patientHeartAllService.getAllPatientsHeartAll();
    }

    /**
     * 根据id删除信息
     * @PathVariable
     * @return
     */
    @GetMapping("/{id}")
    public PatientHeartAll getPatientHeartAllById(@PathVariable Integer id) {
        return patientHeartAllService.getPatientHeartAllById(id);
    }

//    @PutMapping("/{id}")
//    public void updatePatientHeartAll(@PathVariable Integer id, @RequestBody PatientHeartAll patientHeartAll) {
//        PatientHeartAll existingPatientHeartAll = patientHeartAllService.getPatientHeartAllById(id);
//        if (existingPatientHeartAll != null) {
//            existingPatientHeartAll.setPatientId(patientHeartAll.getPatientId());
//            existingPatientHeartAll.setName(patientHeartAll.getName());
//            existingPatientHeartAll.setAge(patientHeartAll.getAge());
//            existingPatientHeartAll.setGender(patientHeartAll.getGender());
//            existingPatientHeartAll.setAddress(patientHeartAll.getAddress());
//            existingPatientHeartAll.setSex(patientHeartAll.getSex());
//            existingPatientHeartAll.setCp(patientHeartAll.getCp());
//            existingPatientHeartAll.setTrestbps(patientHeartAll.getTrestbps());
//            existingPatientHeartAll.setChol(patientHeartAll.getChol());
//            existingPatientHeartAll.setFbs(patientHeartAll.getFbs());
//            existingPatientHeartAll.setRestecg(patientHeartAll.getRestecg());
//            existingPatientHeartAll.setThalach(patientHeartAll.getThalach());
//            existingPatientHeartAll.setExang(patientHeartAll.getExang());
//            existingPatientHeartAll.setOldpeak(patientHeartAll.getOldpeak());
//            existingPatientHeartAll.setSlope(patientHeartAll.getSlope());
//            existingPatientHeartAll.setCa(patientHeartAll.getCa());
//            existingPatientHeartAll.setThal(patientHeartAll.getThal());
//            patientHeartAllService.updatePatient(existingPatientHeartAll);
//        }
//    }


//    @DeleteMapping("/delete/{id}")
//    public List<PatientHeartAll> deletePatientHeartAll(@PathVariable Integer id) {
//         patientHeartAllService.deletePatient(id);
//        return patientHeartAllService.getAllPatientsHeartAll();
//    }

    @RequestMapping("/delete/{id}")
    public List<PatientHeartAll> delete(@PathVariable Integer id) throws IOException {
        patientHeartAllService.deletePatient(id);
        System.out.println("delete方法执行");
        return patientHeartAllService.getAllPatientsHeartAll();
    }

}


