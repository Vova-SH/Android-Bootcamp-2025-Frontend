package ru.sicampus.bootcamp2025.domain.entities;

import androidx.annotation.NonNull;

public class ItemVolunteerCenterEntity {
    @NonNull
    private final String id;

    @NonNull
    private final String centre_name;

    @NonNull
    private final String phone;

    public ItemVolunteerCenterEntity(@NonNull String id, @NonNull String centreName, @NonNull String phone) {
        this.id = id;
        centre_name = centreName;
        this.phone = phone;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getCentre_name() {
        return centre_name;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }
}
