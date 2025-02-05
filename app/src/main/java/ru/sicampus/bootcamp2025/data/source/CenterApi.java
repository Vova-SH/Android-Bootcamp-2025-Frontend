package ru.sicampus.bootcamp2025.data.source;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.sicampus.bootcamp2025.data.dto.CenterDto;

public interface CenterApi {

    @GET("server/v1/center")
    Call<List<CenterDto>> getAll();

    @GET("server/v1/center/{id}")
    Call<CenterDto> getById(@Path("id") String id);

     /* @PUT("server/v1/center/volunteer/{centerId}/{volunteerId}")
    Call<VolunteerDto> addVolunteer(@Path("centerId") String centerId, @Path("volunteerId") String volunteerId);

    @PUT("server/v1/center/volunteer/delete/{centerId}/{volunteerId}")
    Call<CenterDto> deleteCenter(@Path("centerId") String centerId, @Path("volunteerId") String volunteerId); */
}
