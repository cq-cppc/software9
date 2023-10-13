package com.cqupt.software_9.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.software_9.entity.PatientHeartAll;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PatientHeartAllMapper extends BaseMapper<PatientHeartAll> {
    List<PatientHeartAll> getAllPatientsHeartAll();
    PatientHeartAll getPatientHeartAllById(Integer id);
    void addPatient(PatientHeartAll patientHeartAll);
    void updatePatient(PatientHeartAll patientHeartAll);
    void deletePatient(Integer id);
    @Delete("delete from patient_heart_all where id=#{id}")
    public int delete(int id);

}