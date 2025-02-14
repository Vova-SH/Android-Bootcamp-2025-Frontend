package ru.sicampus.bootcamp2025.data;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.data.dto.LoginDto;
import ru.sicampus.bootcamp2025.data.dto.UserDto;
import ru.sicampus.bootcamp2025.data.dto.VolunteerCenterDto;
import ru.sicampus.bootcamp2025.data.network.RetrofitFactory;
import ru.sicampus.bootcamp2025.data.source.CredentialsDataSource;
import ru.sicampus.bootcamp2025.data.source.UserApi;
import ru.sicampus.bootcamp2025.data.utils.CallToConsumer;
import ru.sicampus.bootcamp2025.domain.UserRepository;
import ru.sicampus.bootcamp2025.domain.entites.FullUserEntity;
import ru.sicampus.bootcamp2025.domain.entites.FullVolunteerCenterEntity;
import ru.sicampus.bootcamp2025.domain.entites.ItemUserEntity;
import ru.sicampus.bootcamp2025.domain.entites.ItemVolunteerCenterEntity;
import ru.sicampus.bootcamp2025.domain.entites.Status;
import ru.sicampus.bootcamp2025.domain.sign.SignUserRepository;

public class UserRepositoryImpl implements UserRepository, SignUserRepository {
    private static UserRepositoryImpl INSTANCE;
    private UserApi userApi = RetrofitFactory.getInstance().getUserApi();
    private final CredentialsDataSource credentialsDataSource = CredentialsDataSource.getInstance();

    private UserRepositoryImpl() {}

    public static synchronized UserRepositoryImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRepositoryImpl();
        }
        return INSTANCE;
    }

    @Override
    public void getAllUsers(@NonNull Consumer<Status<List<ItemUserEntity>>> callback) {
        userApi.getAllUsers().enqueue(new CallToConsumer<>(
                callback,
                usersDto -> {
                    ArrayList<ItemUserEntity> result = new ArrayList<>(usersDto.size());
                    for (UserDto user : usersDto) {
                        final Integer id = user.id;
                        final String firstName = user.firstName;
                        final String lastName = user.lastName;
                        if (id != null && lastName != null) {
                            result.add(new ItemUserEntity(id, lastName, firstName));
                        }
                    }
                    return result;
                }
        ));
    }

    @Override
    public void getUser(@NonNull Integer id, @NonNull Consumer<Status<FullUserEntity>> callback) {
        userApi.getUserById(id).enqueue(new CallToConsumer<>(
                callback,
                user -> {
                    final Integer resultId = user.id;
                    final String firstName = user.firstName;
                    final String lastName = user.lastName;
                    final String username = user.username;
                    final String password = user.password;
                    final String contactInfo = user.contactInfo;
                    final String biography = user.biography;
                    final String photo = user.photo;
                    final Integer role = user.role;
                    final Integer status = user.status;
                    if (resultId != null && username != null) {
                        return new FullUserEntity(
                                resultId,
                                firstName,
                                lastName,
                                username,
                                password,
                                contactInfo,
                                biography,
                                photo,
                                role,
                                status
                        );
                    } else {
                        return null;
                    }
                }
        ));

    }

    @Override
    public void getAllVolunteerCenters(@NonNull Consumer<Status<List<ItemVolunteerCenterEntity>>> callback) {
        userApi.getAllVolunteerCenters().enqueue(new CallToConsumer<>(
                callback,
                volunteerCentersDto -> {
                    ArrayList<ItemVolunteerCenterEntity> result = new ArrayList<>(volunteerCentersDto.size());
                    for (VolunteerCenterDto volunteerCenter : volunteerCentersDto) {
                        final Integer id = volunteerCenter.id;
                        final String address = volunteerCenter.address;
                        final String latitude = volunteerCenter.latitude;
                        final String longitude = volunteerCenter.longitude;
                        if (id != null && latitude != null && longitude != null) {
                            result.add(new ItemVolunteerCenterEntity(id, address, latitude, longitude));
                        }
                    }
                    return result;
                }
        ));
    }

    @Override
    public void getVolunteerCenterById(@NonNull Integer id, @NonNull Consumer<Status<FullVolunteerCenterEntity>> callback) {
        userApi.getVolunteerCenterById(id).enqueue(new CallToConsumer<>(
                callback,
                volunteerCenter -> {
                    final Integer resultId = volunteerCenter.id;
                    final String address = volunteerCenter.address;
                    final String latitude = volunteerCenter.latitude;
                    final String longitude = volunteerCenter.longitude;
                    final String photo = volunteerCenter.photo;
                    final String description = volunteerCenter.description;
                    final List<FullUserEntity> users = volunteerCenter.users;
                    if (resultId != null && address != null && latitude != null && longitude != null) {
                        return new FullVolunteerCenterEntity(
                                resultId,
                                address,
                                latitude,
                                longitude,
                                photo,
                                description,
                                users
                        );
                    } else {
                        return null;
                    }
                }
        ));
    }

    @Override
    public void isExistUser(@NonNull String username, Consumer<Status<FullUserEntity>> callback) {
        /*userApi.isUserExist(username).enqueue(new CallToConsumer<>(
                callback,
                dto -> null
        ));*/

        userApi.isUserExist(username).enqueue(new CallToConsumer<>(
                callback,
                user -> {
                    if (user != null) {
                        final Integer resultId = user.id;
                        final String firstName = user.firstName;
                        final String lastName = user.lastName;
                        //final String username = user.username;
                        final String password = user.password;
                        final String contactInfo = user.contactInfo;
                        final String biography = user.biography;
                        final String photo = user.photo;
                        final Integer role = user.role;
                        final Integer status = user.status;
                        if (resultId != null) {
                            return new FullUserEntity(
                                    resultId,
                                    firstName,
                                    lastName,
                                    username,
                                    password,
                                    contactInfo,
                                    biography,
                                    photo,
                                    role,
                                    status
                            );
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                }
        ));
    }

    @Override
    public void createAccount(@NonNull String firstName, @NonNull String lastName, @NonNull String username, @NonNull String password, @NonNull String contactInfo, @NonNull String biography, @NonNull String photo, @NonNull Integer role, @NonNull Integer status, Consumer<Status<Void>> callback) {
        userApi.register(new LoginDto(firstName, lastName, username, password, contactInfo, biography, photo, role, status)).enqueue(new CallToConsumer<>(
                callback,
                dto -> null
        ));
    }

    @Override
    public void login(@NonNull String username, @NonNull String password, Consumer<Status<Void>> callback) {
        credentialsDataSource.updateLogin(username, password);
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
