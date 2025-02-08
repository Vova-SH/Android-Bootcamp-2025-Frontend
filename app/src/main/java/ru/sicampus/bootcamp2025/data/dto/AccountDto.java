package ru.sicampus.bootcamp2025.data.dto;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class AccountDto {

    @Nullable
    @SerializedName("name")
    public String name;

    @Nullable
    @SerializedName("nickname")
    public String nickname;

    @Nullable
    @SerializedName("email")
    public String email;

    @Nullable
    @SerializedName("password")
    public  String password;

    public AccountDto(
            @Nullable String name,
            @Nullable String nickname,
            @Nullable String email,
            @Nullable String password
    ) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public String getNickname() {
        return nickname;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    @Nullable
    public String getPassword() {
        return password;
    }
}
