package com.example.jayesh.contactdirectory.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by root on 12/11/16.
 */

public class Contacts {

    @SerializedName("status")
    private Integer status;

    @SerializedName("message")
    private String message;

    @SerializedName("user_data")
    private ArrayList<UserDataResponse> user_data;

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<UserDataResponse> getUser_data() {
        return user_data;
    }
}
