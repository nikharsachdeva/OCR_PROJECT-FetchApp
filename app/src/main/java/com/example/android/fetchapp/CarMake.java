package com.example.android.fetchapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarMake {

    @SerializedName("CurrentTextValue")
    @Expose
    private String currentTextValue;

    public String getCurrentTextValue() {
        return currentTextValue;
    }

    public void setCurrentTextValue(String currentTextValue) {
        this.currentTextValue = currentTextValue;
    }

}
