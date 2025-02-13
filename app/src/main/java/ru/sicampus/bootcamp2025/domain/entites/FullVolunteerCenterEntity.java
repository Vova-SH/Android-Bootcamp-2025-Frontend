package ru.sicampus.bootcamp2025.domain.entites;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FullVolunteerCenterEntity {
    @NonNull
    private final Integer id;
    @NonNull
    private final String address;
    @NonNull
    private final String latitude;
    @NonNull
    private final String longitude;
    @Nullable
    private final String photo;
    @Nullable
    private final String description;
    @Nullable
    private final List<FullUserEntity> users = new ArrayList<>();

    public FullVolunteerCenterEntity(@NonNull Integer id, @NonNull String address, @NonNull String latitude, @NonNull String longitude, @Nullable String photo, @Nullable String description, @Nullable List<FullUserEntity> users) {
        this.id = id;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.photo = photo;
        this.description = description;
        this.users.clear();
        this.users.addAll(users);
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    @NonNull
    public String getLatitude() {
        return latitude;
    }

    @NonNull
    public String getLongitude() {
        return longitude;
    }

    @Nullable
    public String getPhoto() {
        return photo;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    @Nullable
    public List<FullUserEntity> getUsers() {
        return users;
    }
}
