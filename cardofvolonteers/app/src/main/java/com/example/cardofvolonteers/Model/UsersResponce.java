package com.example.cardofvolonteers.Model;

import java.util.List;

public class UsersResponce {
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public UsersResponce(List<User> users) {
        this.users = users;
    }
}
