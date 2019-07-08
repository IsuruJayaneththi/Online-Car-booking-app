package com.example.issa.pdm_project_2018.Model;

public class Rating {
    private String userPhone; //both key and value
    private String carId;
    private String rateValue;
    private String comment;

    public Rating() {
    }

    public Rating(String userPhone, String carId, String rateValue, String comment) {
        this.userPhone = userPhone;
        this.carId = carId;
        this.rateValue = rateValue;
        this.comment = comment;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getRateValue() {
        return rateValue;
    }

    public void setRateValue(String rateValue) {
        this.rateValue = rateValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
