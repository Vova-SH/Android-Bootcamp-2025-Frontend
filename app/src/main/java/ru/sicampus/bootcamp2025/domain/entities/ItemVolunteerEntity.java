package ru.sicampus.bootcamp2025.domain.entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ItemVolunteerEntity {
    @NonNull
    private final String id;

    @NonNull
    private final String name;

    @NonNull
    private final String email;

    @Nullable
    private final String photoUrl;

    public ItemVolunteerEntity(@NonNull String id, @NonNull String name, @NonNull String phone, @NonNull String email, @Nullable String photoUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    @Nullable
    public String getPhotoUrl() {
        return photoUrl;
    }

    @NonNull
    public String getEmail() {
        return email;
    }
}
