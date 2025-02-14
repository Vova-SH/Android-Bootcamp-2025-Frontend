package ru.sicampus.bootcamp2025.data.source;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.sicampus.bootcamp2025.data.dto.LoginDto;
import ru.sicampus.bootcamp2025.data.dto.UserDto;
import ru.sicampus.bootcamp2025.data.dto.VolunteerCenterDto;

public interface UserApi {
    @GET("api/user")
    Call<List<UserDto>> getAllUsers();
    @GET("api/user/{id}")
    Call<UserDto> getUserById(@Path("id") Integer id);
    @GET("api/user/username/{username}")
    Call<UserDto> isUserExist(@Path("username") String username);
    @POST("api/user/register")
    Call<Void> register(@Body LoginDto dto);
    @GET("api/user/login")
    Call<Void> login();
    @GET("api/volunteer_center/")
    Call<List<VolunteerCenterDto>> getAllVolunteerCenters();
    @GET("api/volunteer_center/{id}")
    Call<VolunteerCenterDto> getVolunteerCenterById(@Path("id") Integer id);
}
