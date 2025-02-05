package ru.sicampus.bootcamp2025.domain.entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FullCenterEntity {
    @NonNull
    private final String id;

    @NonNull
    private final String centreName;

    @NonNull
    private final String address;

    @NonNull
    private final String phone;

    @Nullable
    private final List<ItemUserEntity> activeVolunteers;

    public FullCenterEntity(@NonNull String id,
                            @NonNull String centreName,
                            @NonNull String address,
                            @NonNull String phone,
                            @Nullable List<ItemUserEntity> activeVolunteers) {
        this.id = id;
        this.centreName = centreName;
        this.address = address;
        this.phone = phone;
        this.activeVolunteers = activeVolunteers;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getCentreName() {
        return centreName;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    @Nullable
    public List<ItemUserEntity> getActiveVolunteers() {
        return activeVolunteers;
    }
}