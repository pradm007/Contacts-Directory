package com.example.jayesh.contactdirectory.network.model;

import com.google.gson.annotations.SerializedName;
/**
 * Created by root on 12/11/16.
 */

public class ContactResponse {

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }
}
