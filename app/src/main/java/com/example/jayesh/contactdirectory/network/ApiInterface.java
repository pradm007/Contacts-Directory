package com.example.jayesh.contactdirectory.network;

import com.example.jayesh.contactdirectory.network.model.Contacts;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by root on 12/11/16.
 */

public interface ApiInterface {
    @GET("/Mobileapi/Directory")
    Call<Contacts> getContactList(@Query("tenant_id") Integer tenant_id,
                                  @Query("type") Integer type, @Query("user_id") Integer user_id);
}
