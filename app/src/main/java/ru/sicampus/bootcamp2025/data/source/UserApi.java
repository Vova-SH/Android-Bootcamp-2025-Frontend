package ru.sicampus.bootcamp2025.data.source;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import ru.sicampus.bootcamp2025.data.dto.AccountDto;
import ru.sicampus.bootcamp2025.data.dto.UserDto;
import ru.sicampus.bootcamp2025.data.utils.Container;

public interface UserApi {

    @GET("/api/user/{id}")
    Call<UserDto> getById(@Path("id") String id);

    @PUT("/api/user/update/{id}")
    Call<UserDto> update(@Path("id") String id, @Body Container container);

    @DELETE("/api/user/{id}")
    Call<Void> delete(@Path("id") String id);

    @GET("/api/user/username/{username}")
    Call<Void> isExist(@Path("username") String login);

    @POST("/api/user/register")
    Call<Void> register(@Body AccountDto dto);

    @GET("/api/user/login")
    Call<Void> login();
}
