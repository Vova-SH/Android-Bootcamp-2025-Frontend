package ru.sicampus.bootcamp2025.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.data.dto.AccountDto;
import ru.sicampus.bootcamp2025.data.network.RetrofitFactory;
import ru.sicampus.bootcamp2025.data.source.CredentialsDataSource;
import ru.sicampus.bootcamp2025.data.source.UserApi;
import ru.sicampus.bootcamp2025.data.utils.CallToConsumer;
import ru.sicampus.bootcamp2025.data.utils.Container;
import ru.sicampus.bootcamp2025.data.utils.mapper.UserMapper;
import ru.sicampus.bootcamp2025.domain.entities.FullUserEntity;
import ru.sicampus.bootcamp2025.domain.entities.ItemUserEntity;
import ru.sicampus.bootcamp2025.domain.entities.Status;
import ru.sicampus.bootcamp2025.domain.sign.SignUserRepository;
import ru.sicampus.bootcamp2025.domain.user.UserRepository;

public class UserRepositoryImpl implements UserRepository, SignUserRepository {

    private static UserRepositoryImpl INSTANCE;
    private UserApi userApi = RetrofitFactory.getInstance().getUserApi();

    private final CredentialsDataSource credentialsDataSource = CredentialsDataSource.getInstance();

    public static synchronized UserRepositoryImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRepositoryImpl();
        }
        return INSTANCE;
    }

    private UserRepositoryImpl() {
    }

    @Override
    public void getUnoccupiedUsers(@NonNull Consumer<Status<List<ItemUserEntity>>> callback) {

    }

    @Override
    public void getUser(@NonNull String id, @NonNull Consumer<Status<FullUserEntity>> callback) {
        userApi.getById(id).enqueue(new CallToConsumer<>(
                callback,
                UserMapper::toFullUserEntity
        ));
    }

    @Override
    public void getActiveUsers(@NonNull Consumer<Status<List<ItemUserEntity>>> callback) {

    }

    @Override
    public void updateUser(
            @NonNull String id,
            @NonNull String name,
            @NonNull String nickname,
            @NonNull String email,
            @Nullable String phone,
            @Nullable String photoUrl,
            @NonNull Consumer<Status<FullUserEntity>> callback) {
        userApi.update(id, new Container(name, nickname, email, phone, photoUrl)).enqueue(new CallToConsumer<>(
                callback,
                UserMapper::toFullUserEntity
        ));
    }

    @Override
    public void deleteUser(@NonNull String id, Consumer<Status<Void>> callback) {
        userApi.delete(id).enqueue(new CallToConsumer<>(
                callback,
                unused -> null
        ));
    }

    @Override
    public void isExistUser(@NonNull String login, Consumer<Status<Void>> callback) {
        userApi.isExist(login).enqueue(new CallToConsumer<>(
                callback,
                dto -> null
        ));
    }

    @Override
    public void createAccount(@NonNull String login, @NonNull String name, @NonNull String email, @NonNull String password, Consumer<Status<Void>> callback) {
        userApi.register(new AccountDto(
                name,
                login,
                email,
                password
        )).enqueue(new CallToConsumer<>(
                callback,
                dto -> null
        ));
    }

    @Override
    public void login(@NonNull String login, @NonNull String password, Consumer<Status<Void>> callback) {
        credentialsDataSource.updateLogin(login, password);
        userApi = RetrofitFactory.getInstance().getUserApi();
        userApi.login().enqueue(new CallToConsumer<>(
                callback,
                dto -> null
        ));
    }


    @Override
    public void logout() {
        credentialsDataSource.logout();
    }
}