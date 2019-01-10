package com.jigar.android.applicationjson.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by COMP11 on 21-Nov-18.
 */

public class LoginResponse {
    public LoginResponse(String message) {
        this.message = message;
    }

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }





}
