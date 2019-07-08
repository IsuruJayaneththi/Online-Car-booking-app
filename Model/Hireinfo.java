package com.example.issa.pdm_project_2018.Model;

public class Hireinfo {

    private String Customername;
    private String Hireamount;
    private String Hiretime;
    private String Hiredate;
    private String Hiredistance;
    private String HireDuration;
    private String Phone;
    private String Name;

    public Hireinfo() {
    }

    public Hireinfo(String customername, String hireamount, String hiretime, String hiredate, String hiredistance, String hireDuration, String phone, String name) {
        Customername = customername;
        Hireamount = hireamount;
        Hiretime = hiretime;
        Hiredate = hiredate;
        Hiredistance = hiredistance;
        HireDuration = hireDuration;
        Phone = phone;
        Name = name;
    }

    public String getCustomername() {
        return Customername;
    }

    public void setCustomername(String customername) {
        Customername = customername;
    }

    public String getHireamount() {
        return Hireamount;
    }

    public void setHireamount(String hireamount) {
        Hireamount = hireamount;
    }

    public String getHiretime() {
        return Hiretime;
    }

    public void setHiretime(String hiretime) {
        Hiretime = hiretime;
    }

    public String getHiredate() {
        return Hiredate;
    }

    public void setHiredate(String hiredate) {
        Hiredate = hiredate;
    }

    public String getHiredistance() {
        return Hiredistance;
    }

    public void setHiredistance(String hiredistance) {
        Hiredistance = hiredistance;
    }

    public String getHireDuration() {
        return HireDuration;
    }

    public void setHireDuration(String hireDuration) {
        HireDuration = hireDuration;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
