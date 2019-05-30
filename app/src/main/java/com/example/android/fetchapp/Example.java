package com.example.android.fetchapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {
    @SerializedName("CheckIndiaResponse")
    @Expose
    private CheckIndiaResponse checkIndiaResponse;

    public CheckIndiaResponse getCheckIndiaResponse() {
        return checkIndiaResponse;
    }

    public void setCheckIndiaResponse(CheckIndiaResponse checkIndiaResponse) {
        this.checkIndiaResponse = checkIndiaResponse;
    }
}
