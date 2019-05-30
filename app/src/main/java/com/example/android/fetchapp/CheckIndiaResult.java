package com.example.android.fetchapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckIndiaResult {

    @SerializedName("vehicleJson")
    @Expose
    private String vehicleJson;
    @SerializedName("vehicleData")
    @Expose
    private vehicleData vehicleData;

    public String getVehicleJson() {
        return vehicleJson;
    }

    public void setVehicleJson(String vehicleJson) {
        this.vehicleJson = vehicleJson;
    }

    public vehicleData getVehicleData() {
        return vehicleData;
    }

    public void setVehicleData(vehicleData vehicleData) {
        this.vehicleData = vehicleData;
    }
}
