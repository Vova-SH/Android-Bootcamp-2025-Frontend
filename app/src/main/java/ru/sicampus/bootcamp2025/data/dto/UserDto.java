package ru.sicampus.bootcamp2025.data.dto;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class UserDto {
    @Nullable
    @SerializedName("id")
    public Integer id;
    @Nullable
    @SerializedName("firstName")
    public String firstName;
    @Nullable
    @SerializedName("lastName")
    public String lastName;
    @Nullable
    @SerializedName("username")
    public String username;
    @Nullable
    @SerializedName("password")
    public String password;
    @Nullable
    @SerializedName("contactInfo")
    public String contactInfo;
    @Nullable
    @SerializedName("biography")
    public String biography;
    @Nullable
    @SerializedName("photo")
    public String photo;
    @Nullable
    @SerializedName("role")
    public Integer role;
    @Nullable
    @SerializedName("status")
    public Integer status;
}
