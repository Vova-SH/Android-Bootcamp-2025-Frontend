package ru.sicampus.bootcamp2025.domain.entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ItemUserEntity {
    @NonNull
    private final String id;

    @NonNull
    private final String name;

    @NonNull
    private final String email;

    @Nullable
    private final String photoUrl;

    private final boolean isActive;

    @Nullable private final String centerId;

    public ItemUserEntity(
            @NonNull String id,
            @NonNull String name,
            @NonNull String email,
            @Nullable String photoUrl,
            boolean isActive,
            @Nullable String centerId
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.photoUrl = photoUrl;
        this.isActive = isActive;
        this.centerId = centerId;
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
    public String getEmail() {
        return email;
    }

    @Nullable
    public String getPhotoUrl() {
        return photoUrl;
    }

    public boolean isActive() {
        return isActive;
    }

    @Nullable
    public String getCenterId() {
        return centerId;
    }
}
