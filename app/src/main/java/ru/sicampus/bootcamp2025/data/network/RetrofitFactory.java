package ru.sicampus.bootcamp2025.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.sicampus.bootcamp2025.data.source.CenterApi;
import ru.sicampus.bootcamp2025.data.source.VolunteerApi;

public class RetrofitFactory {

    private static RetrofitFactory INSTANCE;

    private RetrofitFactory() {
    }

    public static synchronized RetrofitFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RetrofitFactory();
        }
        return INSTANCE;
    }

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public CenterApi getCenterApi() {
        return retrofit.create(CenterApi.class);
    }

    public VolunteerApi getVolunteerApi() {
        return retrofit.create(VolunteerApi.class);
    }
}
