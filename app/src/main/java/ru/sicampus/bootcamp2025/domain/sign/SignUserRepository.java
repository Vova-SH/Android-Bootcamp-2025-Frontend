package ru.sicampus.bootcamp2025.domain.sign;

import androidx.annotation.NonNull;

import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.domain.entites.FullUserEntity;
import ru.sicampus.bootcamp2025.domain.entites.Status;

public interface SignUserRepository {
    void isExistUser(@NonNull String username, Consumer<Status<FullUserEntity>> callback);
    void createAccount(
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
    );
    void login(
            @NonNull String username,
            @NonNull String password,
            Consumer<Status<Void>> callback
    );

    void logout();
}
