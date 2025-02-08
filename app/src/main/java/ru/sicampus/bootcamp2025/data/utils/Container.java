package ru.sicampus.bootcamp2025.data.utils;

public class Container {

    private final String name;

    private final String nickname;

    private final String email;

    private final String photoUrl;


    public Container(String name, String nickname, String email, String photoUrl) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
}
