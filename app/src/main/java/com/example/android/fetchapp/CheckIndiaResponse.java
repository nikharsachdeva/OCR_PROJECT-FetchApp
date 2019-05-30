package com.example.android.fetchapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckIndiaResponse {

    @SerializedName("CheckIndiaResult")
    @Expose
    private CheckIndiaResult checkIndiaResult;

    public CheckIndiaResult getCheckIndiaResult() {
        return checkIndiaResult;
    }

    public void setCheckIndiaResult(CheckIndiaResult checkIndiaResult) {
        this.checkIndiaResult = checkIndiaResult;
    }
}
