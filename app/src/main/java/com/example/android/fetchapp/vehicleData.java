package com.example.android.fetchapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class vehicleData {
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("RegistrationYear")
    @Expose
    private Integer registrationYear;
    @SerializedName("CarMake")
    @Expose
    private CarMake carMake;
    @SerializedName("CarModel")
    @Expose
    private String carModel;
    @SerializedName("EngineSize")
    @Expose
    private EngineSize engineSize;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRegistrationYear() {
        return registrationYear;
    }

    public void setRegistrationYear(Integer registrationYear) {
        this.registrationYear = registrationYear;
    }

    public CarMake getCarMake() {
        return carMake;
    }

    public void setCarMake(CarMake carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public EngineSize getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(EngineSize engineSize) {
        this.engineSize = engineSize;
    }

}
