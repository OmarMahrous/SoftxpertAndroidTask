package com.skyhosting.softxpertandroidtask.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarsResponse extends ErrorResponse {

    @SerializedName("status")
    private Integer status;
    @SerializedName("data")
    private List<Car> cars = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Car> getData() {
        return cars;
    }

    public void setData(List<Car> cars) {
        this.cars = cars;
    }

}
