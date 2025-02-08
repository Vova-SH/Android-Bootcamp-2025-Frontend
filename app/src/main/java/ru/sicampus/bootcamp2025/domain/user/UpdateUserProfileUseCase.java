package ru.sicampus.bootcamp2025.domain.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.domain.entities.FullUserEntity;
import ru.sicampus.bootcamp2025.domain.entities.Status;

public class UpdateUserProfileUseCase {

    public final UserRepository repo;

    public UpdateUserProfileUseCase(UserRepository repo) {
        this.repo = repo;
    }

    public void execute(
            @NonNull String id,
            @NonNull String name,
            @NonNull String nickname,
            @NonNull String email,
            @Nullable String photoUrl,
            @NonNull Consumer<Status<FullUserEntity>> callback) {
        repo.updateUser(id, name, nickname, email, photoUrl, callback);
    }
}
