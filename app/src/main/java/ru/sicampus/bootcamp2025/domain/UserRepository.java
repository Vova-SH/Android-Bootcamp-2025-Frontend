package ru.sicampus.bootcamp2025.domain;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.domain.entites.FullUserEntity;
import ru.sicampus.bootcamp2025.domain.entites.FullVolunteerCenterEntity;
import ru.sicampus.bootcamp2025.domain.entites.ItemUserEntity;
import ru.sicampus.bootcamp2025.domain.entites.ItemVolunteerCenterEntity;
import ru.sicampus.bootcamp2025.domain.entites.Status;

public interface UserRepository {
    void getAllUsers(@NonNull Consumer<Status<List<ItemUserEntity>>> callback);

    void getUser(@NonNull Integer id, @NonNull Consumer<Status<FullUserEntity>> callback);
    void getAllVolunteerCenters(@NonNull Consumer<Status<List<ItemVolunteerCenterEntity>>> callback);
    void getVolunteerCenterById(@NonNull Integer id, @NonNull Consumer<Status<FullVolunteerCenterEntity>> callback);
    /*void isExist(@NonNull String username, @NonNull Consumer<Status<Void>> callback);
    void register(@NonNull String id, @NonNull Consumer<Status<FullUserEntity>> callback);

    void login(@NonNull String username, @NonNull String password, @NonNull Consumer<Status<Void>> callback);*/
}
