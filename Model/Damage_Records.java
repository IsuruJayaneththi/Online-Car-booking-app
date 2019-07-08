package com.example.issa.pdm_project_2018.Model;

public class Damage_Records {

    private String DamageLocation;
    private String Description;
    private String Time;
    private String Date;
    private String Phone;
    private String Name;

    public Damage_Records() {
    }

    public Damage_Records(String damageLocation, String description, String time, String date, String phone, String name) {
        DamageLocation = damageLocation;
        Description = description;
        Time = time;
        Date = date;
        Phone = phone;
        Name = name;
    }

    public String getDamageLocation() {
        return DamageLocation;
    }

    public void setDamageLocation(String damageLocation) {
        DamageLocation = damageLocation;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
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
