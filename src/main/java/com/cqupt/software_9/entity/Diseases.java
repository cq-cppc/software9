package com.cqupt.software_9.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "Diseases")

public class Diseases {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String code;
    private String name;
    private String symp;
    private String dptment;
    private String prevent;
    private String partName;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode(){return code;}
    public void setCode(String code){this.code=code;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymp() {
        return symp;
    }

    public void setSymp(String symp) {
        this.symp = symp;
    }

    public String getDptment() {
        return dptment;
    }

    public void setDptment(String dptment) {
        this.dptment = dptment;
    }

    public String getPrevent() {
        return prevent;
    }

    public void setPrevent(String prevent) {
        this.prevent = prevent;
    }

    public String getPartName(){return partName;}

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Diseases() {
    }

    public Diseases(String code, String name, String symp, String dptment,  String prevent,String parName ) {
        this.code = code;
        this.name = name;
        this.symp = symp;
        this.dptment = dptment;
        this.prevent = prevent;
        this.partName=partName;
    }

    @Override
    public String toString() {
        return "Diseases{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", symp='" + symp + '\'' +
                ", dptment='" + dptment + '\'' +
                ", prevent='" + prevent + '\'' +
                ", partName='" + partName + '\'' +
                '}';
    }
}