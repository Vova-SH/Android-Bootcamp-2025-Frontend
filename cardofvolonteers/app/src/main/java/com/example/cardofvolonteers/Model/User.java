package com.example.cardofvolonteers.Model;

public class User {
    private int id;
    private String name;
    private String phone_number;
    private String email;
    private String about;
    private String password;
    private boolean statusWork;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }

    public String getAbout() {
        return about;
    }

    public String getPassword() {
        return password;
    }

    public boolean getStatusWork() {
        return statusWork;
    }

    public String getCenterName() {
        return centerName;
    }

    public User(int id, String name, String phone_number, String email, String about, String password, boolean statusWork, String centerName) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.about = about;
        this.password = password;
        this.statusWork = statusWork;
        this.centerName = centerName;
    }

    private String centerName;


}
