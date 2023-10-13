package com.cqupt.software_9.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.software_9.entity.HeartManager;
import com.cqupt.software_9.entity.PatientHeartDisease;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PatientHeartDiseaseMapper extends BaseMapper<HeartManager> {

     List<PatientHeartDisease> getHeartDiseaseByPatientId(int patientId);



}
