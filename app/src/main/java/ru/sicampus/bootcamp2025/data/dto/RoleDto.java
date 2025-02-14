package ru.sicampus.bootcamp2025.data.dto;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class RoleDto {
    @Nullable
    @SerializedName("id")
    public Integer id;
    @Nullable
    @SerializedName("role_name")
    public String roleName;
}
