package ru.sicampus.bootcamp2025.domain.sign;

import androidx.annotation.NonNull;

import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.domain.entites.Status;

public class CreateUserUseCase {
    private final SignUserRepository repo;

    public CreateUserUseCase(SignUserRepository repo) {
        this.repo = repo;
    }

    public void execute(
            @NonNull String firstName,
            @NonNull String lastName,
            @NonNull String username,
            @NonNull String password,
            @NonNull String contactInfo,
            @NonNull String biography,
            @NonNull String photo,
            @NonNull Integer role,
            @NonNull Integer status,
            Consumer<Status<Void>> callback
    ) {
        repo.createAccount(firstName, lastName, username, password, contactInfo, biography, photo, role, status, callback);
    }
}
