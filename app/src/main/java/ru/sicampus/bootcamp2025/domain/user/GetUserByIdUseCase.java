package ru.sicampus.bootcamp2025.domain.user;

import androidx.annotation.NonNull;

import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.domain.entities.FullUserEntity;
import ru.sicampus.bootcamp2025.domain.entities.Status;

public class GetUserByIdUseCase {

    private final UserRepository repo;

    public GetUserByIdUseCase(UserRepository repo) {
        this.repo = repo;
    }

    public void execute(@NonNull String id, @NonNull Consumer<Status<FullUserEntity>> callback){
        repo.getUser(id, callback);
    }
}
