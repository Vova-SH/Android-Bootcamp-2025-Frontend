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


    public FullCenterEntity(@NonNull String id,
                            @NonNull String centreName,
                            @NonNull String address,
                            @NonNull String phone) {
        this.id = id;
        this.centreName = centreName;
        this.address = address;
        this.phone = phone;
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

}