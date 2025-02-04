package ru.sicampus.bootcamp2025.domain;
import com.google.gson.annotations.SerializedName;

public class Person {
    @SerializedName("id")
    private long id;

    @SerializedName("login")
    private String login;

    @SerializedName("name")
    private String name;

    @SerializedName("photo")
    private String photo;

    @SerializedName("phone")
    private String phone;

    @SerializedName("info")
    private String info;

    @SerializedName("email")
    private String email;

    public Person(long id, String login, String name, String photo, String phone, String info, String email) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.photo = photo;
        this.phone = phone;
        this.info = info;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String position) {
        this.phone = phone;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
