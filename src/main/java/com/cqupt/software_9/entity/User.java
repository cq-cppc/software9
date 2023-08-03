package com.cqupt.software_9.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "User")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String sex;
    private Integer age;
    private String Phonenumber;
    private Integer isadmin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getsex() {
        return sex;
    }

    public void setsex(String sex) {
        this.sex = sex;
    }

    public Integer getage() {
        return age;
    }

    public void setage(Integer age) {
        this.age = age;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(String Phonenumber) {
        this.Phonenumber = Phonenumber;
    }

    public Integer getIsadmin(){
        return isadmin;
    }

    public void setIsadmin(Integer isadmin){
        this.isadmin=isadmin;
    }

    public User() {
    }

    public User(String name, String username, String password, String sex, Integer age, String Phonenumber ,Integer isadmin) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.age = age;
        this.Phonenumber = Phonenumber;
        this.isadmin = isadmin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", Phonenumber='" + Phonenumber + '\'' +
                ", isadmin='" + isadmin + '\'' +
                '}';
    }
}