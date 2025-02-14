package ru.sicampus.bootcamp2025.domain.entites;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FullUserEntity {
    @NonNull
    private final Integer id;
    @NonNull
    private final String firstName;
    @NonNull
    private final String lastName;
    @NonNull
    private final String username;
    @NonNull
    private final String password;
    @Nullable
    private final String contactInfo;
    @Nullable
    private final String biography;
    @Nullable
    private final String photo;
    @NonNull
    private final Integer role;
    @Nullable
    private final Integer status;

    public FullUserEntity(
            @NonNull Integer id,
            @NonNull String firstName,
            @NonNull String lastName,
            @NonNull String username,
            @NonNull String password,
            @Nullable String contactInfo,
            @Nullable String biography,
            @Nullable String photo,
            @NonNull Integer role,
            @Nullable Integer status
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.contactInfo = contactInfo;
        this.biography = biography;
        this.photo = photo;
        this.role = role;
        this.status = status;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    @Nullable
    public String getContactInfo() {
        return contactInfo;
    }

    @Nullable
    public String getBiography() {
        return biography;
    }

    @Nullable
    public String getPhoto() {
        return photo;
    }

    @NonNull
    public Integer getRole() {
        return role;
    }

    @Nullable
    public Integer getStatus() {
        return status;
    }
}
