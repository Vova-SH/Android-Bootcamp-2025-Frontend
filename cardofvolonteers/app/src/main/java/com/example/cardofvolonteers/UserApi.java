package com.example.cardofvolonteers;

import com.example.cardofvolonteers.Model.User;
import com.example.cardofvolonteers.Model.UsersResponce;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserApi {
    @GET("/api/users")
    Call<UsersResponce> getUsers();
}
