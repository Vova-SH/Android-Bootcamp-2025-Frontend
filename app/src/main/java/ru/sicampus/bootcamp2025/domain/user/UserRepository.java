package ru.sicampus.bootcamp2025.domain.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.domain.entities.FullUserEntity;
import ru.sicampus.bootcamp2025.domain.entities.ItemUserEntity;
import ru.sicampus.bootcamp2025.domain.entities.Status;

public interface UserRepository {

    void getUnoccupiedUsers(@NonNull Consumer<Status<List<ItemUserEntity>>> callback);

    void getUser(@NonNull String id, @NonNull Consumer<Status<FullUserEntity>> callback);

    void getActiveUsers(@NonNull Consumer<Status<List<ItemUserEntity>>> callback);

    void updateUser(
            @NonNull String id,
            @NonNull String name,
            @NonNull String nickname,
            @NonNull String email,
            @Nullable String phone,
            @Nullable String photoUrl,
            @NonNull Consumer<Status<FullUserEntity>> callback
    );

    void deleteUser(@NonNull String id, @NonNull Consumer<Status<Void>> callback);
}
