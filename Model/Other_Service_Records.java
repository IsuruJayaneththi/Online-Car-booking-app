package com.example.issa.pdm_project_2018.Model;

public class Other_Service_Records {

    private String Description;
    private String Location;
    private String Amount;
    private String Billno;
    private String Time;
    private String Date;
    private String Phone;
    private String Name;

    public Other_Service_Records() {
    }

    public Other_Service_Records(String description, String location, String amount, String billno, String time, String date, String phone, String name) {
        Description = description;
        Location = location;
        Amount = amount;
        Billno = billno;
        Time = time;
        Date = date;
        Phone = phone;
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getBillno() {
        return Billno;
    }

    public void setBillno(String billno) {
        Billno = billno;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
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
