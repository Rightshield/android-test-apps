package com.own.rightshield.retrofittest.interfaces;

import com.own.rightshield.retrofittest.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserInterface {

    @GET("/users/{name}")
    Call<User> getUser(@Path("name") String username);
}
