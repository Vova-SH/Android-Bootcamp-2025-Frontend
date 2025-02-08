package ru.sicampus.bootcamp2025.domain.sign;

import androidx.annotation.NonNull;

import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.domain.entities.Status;

public class CreateUserUseCase {

    private final SignUserRepository repo;

    public CreateUserUseCase(SignUserRepository repo) {
        this.repo = repo;
    }

    public void execute(
            @NonNull String login,
            @NonNull String name,
            @NonNull String email,
            @NonNull String password,
            Consumer<Status<Void>> callback
    ) {
            repo.createAccount(login, name, email, password, callback);
    }
}
