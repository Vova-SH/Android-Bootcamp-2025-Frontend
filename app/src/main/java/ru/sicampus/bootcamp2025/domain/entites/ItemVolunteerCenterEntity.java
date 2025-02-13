package ru.sicampus.bootcamp2025.domain.entites;

import androidx.annotation.NonNull;

public class ItemVolunteerCenterEntity {
    @NonNull
    private final Integer id;

    @NonNull
    private final String address;
    @NonNull
    private final String latitude;
    @NonNull
    private final String longitude;

    public ItemVolunteerCenterEntity(@NonNull Integer id, @NonNull String address, @NonNull String latitude, @NonNull String longitude) {
        this.id = id;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    @NonNull
    public String getLatitude() {
        return latitude;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    @NonNull
    public String getLongitude() {
        return longitude;
    }
}
