package ru.sicampus.bootcamp2025.data.dto;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ru.sicampus.bootcamp2025.domain.entites.FullUserEntity;

public class VolunteerCenterDto {
    @Nullable
    @SerializedName("id")
    public Integer id;
    @Nullable
    @SerializedName("address")
    public String address;
    @Nullable
    @SerializedName("latitude")
    public String latitude;
    @Nullable
    @SerializedName("longitude")
    public String longitude;
    @Nullable
    @SerializedName("photo")
    public String photo;
    @Nullable
    @SerializedName("description")
    public String description;
    @Nullable
    @SerializedName("volunteer")
    public List<FullUserEntity> users;
}
