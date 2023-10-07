package com.cqupt.software_9.service.imp;

import com.cqupt.software_9.dao.mysql.PatientHeartAllMapper;
import com.cqupt.software_9.entity.PatientHeartAll;
import com.cqupt.software_9.service.Adapter.PatientHeartAllServiceAdapter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PatientHeartAllServiceImpl extends PatientHeartAllServiceAdapter {
    @Resource
    private PatientHeartAllMapper patientHeartAllMapper;

    public List<PatientHeartAll> getAllPatientsHeartAll() {
        return patientHeartAllMapper.getAllPatientsHeartAll();
    }

    @Override
    public PatientHeartAll getPatientHeartAllById(Integer id) {
        return patientHeartAllMapper.selectById(id);
    }

    @Override
    public String addPatient(PatientHeartAll patientHeartAll) {
        patientHeartAllMapper.addPatient(patientHeartAll);
        return "新增成功";
    }

    @Override
    public void updatePatient(PatientHeartAll patientHeartAll) {
        patientHeartAllMapper.updatePatient(patientHeartAll);
    }

    @Override
    public String deletePatient(Integer id) {
//        patientHeartAllMapper.deletePatient(id);
        patientHeartAllMapper.delete(id);
        return "删除成功";
    }



}
