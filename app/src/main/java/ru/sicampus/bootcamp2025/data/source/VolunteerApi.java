package ru.sicampus.bootcamp2025.data.source;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import ru.sicampus.bootcamp2025.data.dto.VolunteerDto;
import ru.sicampus.bootcamp2025.data.utils.Container;

public interface VolunteerApi {

    @GET("server/v1/volunteer/{id}")
    Call<VolunteerDto> getById(@Path("id") String id);

    @PUT("server/v1/volunteer/update/{id}")
    Call<VolunteerDto> update(@Path("id") String id, @Body Container container);
}
