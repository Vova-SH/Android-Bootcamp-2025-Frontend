package ru.sicampus.bootcamp2025.domain.entites;

import androidx.annotation.NonNull;

public class ItemUserEntity {
    @NonNull
    private final Integer id;

    @NonNull
    private final String lastName;

    @NonNull
    private final String firstName;

    public ItemUserEntity(@NonNull Integer id, @NonNull String lastName, @NonNull String firstName) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }
}
