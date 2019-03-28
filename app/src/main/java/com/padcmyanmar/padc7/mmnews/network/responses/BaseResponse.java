package com.padcmyanmar.padc7.mmnews.network.responses;

import com.google.gson.annotations.SerializedName;

public abstract class BaseResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public boolean isResponseSuccess() {
        return code == 200;
    }
}
