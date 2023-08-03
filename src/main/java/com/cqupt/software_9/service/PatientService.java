package com.cqupt.software_9.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqupt.software_9.entity.Patient;
import com.cqupt.software_9.entity.User;
import com.cqupt.software_9.service.Request.Query;

import java.util.List;

public interface PatientService {

    public List<Patient> getAllPatients();
}