package com.cqupt.software_9.service.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqupt.software_9.dao.mysql.PatientMapper;
import com.cqupt.software_9.dao.mysql.UserMapper;
import com.cqupt.software_9.entity.Patient;
import com.cqupt.software_9.entity.User;
import com.cqupt.software_9.service.Adapter.PatientServiceAdapter;
import com.cqupt.software_9.service.Adapter.UserServiceAdapter;
import com.cqupt.software_9.service.Request.Query;
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
