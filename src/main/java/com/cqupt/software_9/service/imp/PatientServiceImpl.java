package com.cqupt.software_9.service.imp;

import com.cqupt.software_9.mapper.PatientMapper;
import com.cqupt.software_9.entity.Patient;
import com.cqupt.software_9.service.Adapter.PatientServiceAdapter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PatientServiceImpl extends PatientServiceAdapter {
    @Resource
    private PatientMapper patientMapper;

    public List<Patient> getAllPatients() {
        return patientMapper.getAllPatients();
    }

}
