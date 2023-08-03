package com.cqupt.software_9.service.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomResponse {
    private Integer id;
    private String code;
    private String name;
    private String symp;
    private String dptment;
    private String prevent;
    private String part;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CustomResponse{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", symp='" + symp + '\'' +
                ", dptment='" + dptment + '\'' +
                ", prevent='" + prevent + '\'' +
                ", partName='" + part + '\'' +
                '}';
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

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
