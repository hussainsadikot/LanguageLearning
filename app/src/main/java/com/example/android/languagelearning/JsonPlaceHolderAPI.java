package com.example.android.languagelearning;

import com.example.android.languagelearning.Model.Users;
import com.example.android.languagelearning.Model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface JsonPlaceHolderAPI {
    @GET("login")
    Call<List<Users>> getUser();

    @GET
    Call<List<Users>>getUser(@Url String url);

    @POST("register")
    Call<Users> registerUser(@Body Users users);

}
