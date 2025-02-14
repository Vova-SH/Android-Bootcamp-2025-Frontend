package ru.sicampus.bootcamp2025.domain.sign;

import androidx.annotation.NonNull;

import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.domain.entites.FullUserEntity;
import ru.sicampus.bootcamp2025.domain.entites.Status;

public class IsUserExistUseCase {
    private final SignUserRepository repo;

    public IsUserExistUseCase(SignUserRepository repo) {
        this.repo = repo;
    }

    public void execute(@NonNull String username, Consumer<Status<FullUserEntity>> callback) {
        repo.isExistUser(username, callback);
        /*repo.isExistUser(username, status -> {
            boolean isAvailable = status.getStatusCode() == 200 || status.getStatusCode() == 404;
            callback.accept(
                    new Status<>(
                            status.getStatusCode(),
                            isAvailable ? status.getStatusCode() == 200 : null,
                            status.getErrors()
                    )
            );
        });*/
    }
}
