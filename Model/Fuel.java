package com.example.issa.pdm_project_2018.Model;

import com.google.firebase.database.Exclude;

public class Fuel {
    private String Name,Distance,Fuel_Amount,Economy;
    private String mkey;

    public Fuel()
    {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDistance() {
        return Distance;
    }

    public void setDistance(String distance) {
        Distance = distance;
    }

    public String getFuel_Amount() {
        return Fuel_Amount;
    }

    public void setFuel_Amount(String fuel_Amount) {
        Fuel_Amount = fuel_Amount;
    }

    public String getEconomy() {
        return Economy;
    }

    public void setEconomy(String economy) {
        Economy = economy;
    }

    public String getMkey() {
        return mkey;
    }

    public void setMkey(String mkey) {
        this.mkey = mkey;
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
