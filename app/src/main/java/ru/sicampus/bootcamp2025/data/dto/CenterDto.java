package ru.sicampus.bootcamp2025.data.dto;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ru.sicampus.bootcamp2025.domain.entities.ItemUserEntity;

public class CenterDto {

    @Nullable
    @SerializedName("id")
    public String id;

    @Nullable
    @SerializedName("centre_name")
    public String centreName;

    @Nullable
    @SerializedName("address")
    public String address;

    @Nullable
    @SerializedName("phone")
    public String phone;

    @Nullable
    @SerializedName("active_volunteers")
    public List<ItemUserEntity> activeVolunteers;
}
