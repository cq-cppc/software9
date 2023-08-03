package com.cqupt.software_9.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.HashMap;
import java.util.Map;

@TableName(value = "PatientHeartDisease")
public class PatientHeartDisease {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer patientId;
    private Integer age;
    private Integer sex;
    private Integer cp;
    private Integer trestbps;
    private Integer chol;
    private Integer fbs;
    private Integer restecg;
    private Integer thalach;
    private Integer exang;
    private float oldpeak;
    private Integer slope;
    private float ca;
    private float thal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    public Integer getTrestbps() {
        return trestbps;
    }

    public void setTrestbps(Integer trestbps) {
        this.trestbps = trestbps;
    }

    public Integer getChol() {
        return chol;
    }

    public void setChol(Integer chol) {
        this.chol = chol;
    }

    public Integer getFbs() {
        return fbs;
    }

    public void setFbs(Integer fbs) {
        this.fbs = fbs;
    }

    public Integer getRestecg() {
        return restecg;
    }

    public void setRestecg(Integer restecg) {
        this.restecg = restecg;
    }

    public Integer getThalach() {
        return thalach;
    }

    public void setThalach(Integer thalach) {
        this.thalach = thalach;
    }

    public Integer getExang() {
        return exang;
    }

    public void setExang(Integer exang) {
        this.exang = exang;
    }

    public float getOldpeak() {
        return oldpeak;
    }

    public void setOldpeak(float oldpeak) {
        this.oldpeak = oldpeak;
    }

    public Integer getSlope() {
        return slope;
    }

    public void setSlope(Integer slope) {
        this.slope = slope;
    }

    public float getCa() {
        return ca;
    }

    public void setCa(float ca) {
        this.ca = ca;
    }

    public float getThal() {
        return thal;
    }

    public void setThal(float thal) {
        this.thal = thal;
    }

    @Override
    public String toString() {
        return "PatientHeartDisease{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", age=" + age +
                ", sex=" + sex +
                ", cp=" + cp +
                ", trestbps=" + trestbps +
                ", chol=" + chol +
                ", fbs=" + fbs +
                ", restecg=" + restecg +
                ", thalach=" + thalach +
                ", exang=" + exang +
                ", oldpeak=" + oldpeak +
                ", slope=" + slope +
                ", ca=" + ca +
                ", thal=" + thal +
                '}';
    }
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("patientId", patientId);
        map.put("age", age);
        map.put("sex", sex);
        map.put("cp", cp);
        map.put("trestbps", trestbps);
        map.put("chol", chol);
        map.put("fbs", fbs);
        map.put("restecg", restecg);
        map.put("thalach", thalach);
        map.put("exang", exang);
        map.put("oldpeak", oldpeak);
        map.put("slope", slope);
        map.put("ca", ca);
        map.put("thal", thal);
        // Add any additional fields you have in the PatientHeartDisease class
        return map;
    }

}
