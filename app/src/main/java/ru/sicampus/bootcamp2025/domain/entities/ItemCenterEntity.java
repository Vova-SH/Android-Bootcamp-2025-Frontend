package ru.sicampus.bootcamp2025.domain.entities;

import androidx.annotation.NonNull;

public class ItemCenterEntity {
    @NonNull
    private final String id;

    @NonNull
    private final String centre_name;

    @NonNull
    private final String address;

    public ItemCenterEntity(@NonNull String id, @NonNull String centreName, @NonNull String address) {
        this.id = id;
        centre_name = centreName;
        this.address = address;
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
    public String getAddress() {
        return address;
    }
}
