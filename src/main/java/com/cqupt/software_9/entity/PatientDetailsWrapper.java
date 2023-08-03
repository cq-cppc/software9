package com.cqupt.software_9.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;
@TableName(value = "PatientDetailsWrapper")
public class PatientDetailsWrapper {
    private Patient patient;
    private List<PatientHeartDisease> heartDiseases;

    public PatientDetailsWrapper(Patient patient, List<PatientHeartDisease> heartDiseases) {
        this.patient = patient;
        this.heartDiseases = heartDiseases;
    }

    public Patient getPatient() {
        return patient;
    }

    public List<PatientHeartDisease> getHeartDiseases() {
        return heartDiseases;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("id=").append(patient.getId()).append(", ");
        sb.append("patientId=").append(patient.getPatientId()).append(", ");
        sb.append("name='").append(patient.getName()).append("', ");
        sb.append("age=").append(patient.getAge()).append(", ");
        sb.append("gender='").append(patient.getGender()).append("', ");
        sb.append("address='").append(patient.getAddress()).append("', ");

        for (PatientHeartDisease heartDisease : heartDiseases) {
            sb.append("id=").append(heartDisease.getId()).append(", ");
            sb.append("patientId=").append(heartDisease.getPatientId()).append(", ");
            sb.append("age=").append(heartDisease.getAge()).append(", ");
            sb.append("sex=").append(heartDisease.getSex()).append(", ");
            sb.append("cp=").append(heartDisease.getCp()).append(", ");
            sb.append("trestbps=").append(heartDisease.getTrestbps()).append(", ");
            sb.append("chol=").append(heartDisease.getChol()).append(", ");
            sb.append("fbs=").append(heartDisease.getFbs()).append(", ");
            sb.append("restecg=").append(heartDisease.getRestecg()).append(", ");
            sb.append("thalach=").append(heartDisease.getThalach()).append(", ");
            sb.append("exang=").append(heartDisease.getExang()).append(", ");
            sb.append("oldpeak=").append(heartDisease.getOldpeak()).append(", ");
            sb.append("slope=").append(heartDisease.getSlope()).append(", ");
            sb.append("ca=").append(heartDisease.getCa()).append(", ");
            sb.append("thal=").append(heartDisease.getThal()).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length()); // 删除末尾多余的逗号和空格
        sb.append("}");
        return sb.toString();
    }
}
