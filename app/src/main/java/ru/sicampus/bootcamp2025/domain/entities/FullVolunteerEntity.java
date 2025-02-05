package ru.sicampus.bootcamp2025.domain.entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FullVolunteerEntity {
    @NonNull
    private final String id;

    @NonNull
    private final String name;

    @NonNull
    private final String nickname;

    @NonNull
    private final String email;

    @Nullable
    private final String phone;

    @Nullable
    private final String photoUrl;

    public FullVolunteerEntity(@NonNull String id,
                               @NonNull String name,
                               @NonNull String nickname,
                               @NonNull String email,
                               @Nullable String phone,
                               @Nullable String photoUrl) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
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
    public String getNickname() {
        return nickname;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    @Nullable
    public String getPhone() {
        return phone;
    }

    @Nullable
    public String getPhotoUrl() {
        return photoUrl;
    }
}
