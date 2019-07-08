package com.example.issa.pdm_project_2018.Model;

import com.google.firebase.database.Exclude;

public class Service {

   private  String Vehicle_Name;
   private String Date;
   private String Job;
   private String Price,mkey;



    public String getVehicle_Name() {
        return Vehicle_Name;
    }

    public void setVehicle_Name(String vehicle_Name) {
        Vehicle_Name = vehicle_Name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String job) {
        Job = job;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    @Exclude
    public String getkey() {
        return mkey;
    }
    @Exclude
    public void setkey(String key) {
        mkey = key;
    }


}
