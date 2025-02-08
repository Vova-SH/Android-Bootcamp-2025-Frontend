package ru.sicampus.bootcamp2025.domain.entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FullUserEntity {
    @NonNull
    private final String id;

    @NonNull
    private final String name;

    @NonNull
    private final String nickname;

    @NonNull
    private final String email;

    @Nullable
    private final String photoUrl;

    private final boolean isActive;

    @Nullable private final String centerId;

    @Nullable private final String centerName;


    public FullUserEntity(
            @NonNull String id,
            @NonNull String name,
            @NonNull String nickname,
            @NonNull String email,
            @Nullable String photoUrl,
            boolean isActive,
            @Nullable String centerId,
            @Nullable String centerName) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.photoUrl = photoUrl;
        this.isActive = isActive;
        this.centerId = centerId;
        this.centerName = centerName;
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
    public String getNickname() {
        return nickname;
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

    @Nullable
    public String getCenterName() {
        return centerName;
    }
}
