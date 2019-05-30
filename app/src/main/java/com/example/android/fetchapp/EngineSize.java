package com.example.android.fetchapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EngineSize {
    @SerializedName("CurrentTextValue")
    @Expose
    private Integer currentTextValue;

    public Integer getCurrentTextValue() {
        return currentTextValue;
    }

    public void setCurrentTextValue(Integer currentTextValue) {
        this.currentTextValue = currentTextValue;
    }
}
