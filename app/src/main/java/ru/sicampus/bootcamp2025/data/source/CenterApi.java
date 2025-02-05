package ru.sicampus.bootcamp2025.data.source;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.sicampus.bootcamp2025.data.dto.CenterDto;

public interface CenterApi {

    @GET("/api/center")
    Call<List<CenterDto>> getAll();

    @GET("/api/center/{id}")
    Call<CenterDto> getById(@Path("id") String id);

     /* @PUT("/api/center/user/{centerId}/{userId}")
    Call<UserDto> addVolunteer(@Path("centerId") String centerId, @Path("userId") String userId);

    @PUT("/api/center/user/delete/{centerId}/{userId}")
    Call<CenterDto> deleteCenter(@Path("centerId") String centerId, @Path("userId") String userId); */
}
