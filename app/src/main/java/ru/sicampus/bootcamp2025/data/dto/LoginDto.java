package ru.sicampus.bootcamp2025.data.dto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class LoginDto {
    @Nullable
    @SerializedName("firstName")
    public String firstName;
    @Nullable
    @SerializedName("lastName")
    public String lastName;
    @Nullable
    @SerializedName("username")
    public String username;
    @Nullable
    @SerializedName("password")
    public String password;
    @Nullable
    @SerializedName("contactInfo")
    public String contactInfo;
    @Nullable
    @SerializedName("biography")
    public String biography;
    @Nullable
    @SerializedName("photo")
    public String photo;

    @Nullable
    @SerializedName("role")
    public Integer role;
    @Nullable
    @SerializedName("status")
    public Integer status;

    public LoginDto(@Nullable String firstName,
                    @Nullable String lastName,
                    @Nullable String username,
                    @Nullable String password,
                    @Nullable String contactInfo,
                    @Nullable String biography,
                    @Nullable String photo,
                    @Nullable Integer role,
                    @Nullable Integer status) {
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

    @Nullable
    public String getFirstName() {
        return firstName;
    }

    @Nullable
    public String getLastName() {
        return lastName;
    }

    @Nullable
    public String getusername() {
        return username;
    }

    @Nullable
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

    @Nullable
    public Integer getRole() {
        return role;
    }

    @Nullable
    public Integer getStatus() {
        return status;
    }
}
