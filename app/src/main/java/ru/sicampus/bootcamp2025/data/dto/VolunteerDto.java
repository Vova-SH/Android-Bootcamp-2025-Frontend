package ru.sicampus.bootcamp2025.data.dto;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;


public class VolunteerDto {
    @Nullable
    @SerializedName("id")
    public String id;

    @Nullable
    @SerializedName("name")
    public String name;

    @Nullable
    @SerializedName("nickname")
    public String nickname;

    @Nullable
    @SerializedName("email")
    public String email;

    @Nullable
    @SerializedName("phone")
    public String phone;

    @Nullable
    @SerializedName("photo_url")
    public String photoUrl;
}
