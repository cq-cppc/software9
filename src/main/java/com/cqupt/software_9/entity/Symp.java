package com.cqupt.software_9.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "Symp")
public class Symp {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String code;
    private String name;
    private Boolean isGet;
    private String partName;
    private String partCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsGet() {
        return isGet;
    }

    public void setIsGet(Boolean get) {
        isGet = get;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartCode() {
        return partCode;
    }

    public void setPartCode(String partCode) {
        this.partCode = partCode;
    }
    public Symp(){

    }
    public Symp(  Integer id,String code,String name,Boolean isGet,String partName,String partCode){
        this.code=code;
        this.id=id;
        this.isGet=isGet;
        this.name=name;
        this.partCode=partCode;
        this.partName=partName;
    }
    @Override
    public String toString() {
        return "Symp{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", isGet=" + isGet +
                ", partName='" + partName + '\'' +
                ", partCode='" + partCode + '\'' +
                '}';
    }
}
