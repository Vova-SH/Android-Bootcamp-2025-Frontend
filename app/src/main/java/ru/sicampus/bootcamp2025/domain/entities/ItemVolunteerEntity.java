package ru.sicampus.bootcamp2025.domain.entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ItemVolunteerEntity {
    @NonNull
    private final String id;

    @NonNull
    private final String name;

    @NonNull
    private final String phone;

    @Nullable
    private final String photoUrl;

    public ItemVolunteerEntity(@NonNull String id, @NonNull String name, @NonNull String phone, @Nullable String photoUrl) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.photoUrl = photoUrl;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    @Nullable
    public String getPhotoUrl() {
        return photoUrl;
    }
}
