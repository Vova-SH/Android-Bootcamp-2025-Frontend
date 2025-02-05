package ru.sicampus.bootcamp2025.domain.user;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.domain.entities.ItemUserEntity;
import ru.sicampus.bootcamp2025.domain.entities.Status;

public class GetUnoccupiedUsersUseCase {

    public final UserRepository repo;

    public GetUnoccupiedUsersUseCase(UserRepository repo) {
        this.repo = repo;
    }

    public void execute(@NonNull Consumer<Status<List<ItemUserEntity>>> callback) {
        repo.getUnoccupiedUsers(callback);
    }
}
