package ru.sicampus.bootcamp2025.domain.user;

import androidx.annotation.NonNull;

import java.util.function.Consumer;


import ru.sicampus.bootcamp2025.domain.entities.Status;

public class DeleteUserByIdUseCase {

    public final UserRepository repo;

    public DeleteUserByIdUseCase(UserRepository repo) {
        this.repo = repo;
    }

    public void execute(@NonNull String id, @NonNull Consumer<Status<Void>> callback) {
        repo.deleteUser(id, callback);
    }
}
