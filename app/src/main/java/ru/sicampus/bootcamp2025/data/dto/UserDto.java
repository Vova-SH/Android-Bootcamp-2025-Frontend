package ru.sicampus.bootcamp2025.data.dto;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;


public class UserDto {
    @Nullable
    @SerializedName("id")
    public String id;

    @Nullable
    @SerializedName("name")
    public String name;

    @Nullable
    @SerializedName("username")
    public String nickname;

    @Nullable
    @SerializedName("mail")
    public String email;

    @Nullable
    @SerializedName("photo_url")
    public String photoUrl;

    @SerializedName("active")
    public boolean isActive;

    @Nullable
    @SerializedName("center_id")
    public String centerId;

    @Nullable
    @SerializedName("center_name")
    public String centerName;
}
