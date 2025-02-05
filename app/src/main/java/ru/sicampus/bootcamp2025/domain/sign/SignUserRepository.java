package ru.sicampus.bootcamp2025.domain.sign;

import androidx.annotation.NonNull;

import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.domain.entities.Status;

public interface SignUserRepository {
    void isExistUser(@NonNull String login, Consumer<Status<Void>> callback);
    void createAccount(
            @NonNull String login,
            @NonNull String name,
            @NonNull String email,
            @NonNull String password,
            Consumer<Status<Void>> callback
    );
    void login(
            @NonNull String login,
            @NonNull String password,
            Consumer<Status<Void>> callback
    );

    void logout();
}
