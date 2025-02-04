package ru.sicampus.bootcamp2025.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.data.dto.VolunteerDto;
import ru.sicampus.bootcamp2025.data.network.RetrofitFactory;
import ru.sicampus.bootcamp2025.data.source.VolunteerApi;
import ru.sicampus.bootcamp2025.data.utils.CallToConsumer;
import ru.sicampus.bootcamp2025.data.utils.Container;
import ru.sicampus.bootcamp2025.domain.entities.FullVolunteerEntity;
import ru.sicampus.bootcamp2025.domain.entities.ItemVolunteerEntity;
import ru.sicampus.bootcamp2025.domain.entities.Status;
import ru.sicampus.bootcamp2025.domain.volunteer.VolunteerRepository;

public class VolunteerRepositoryImpl implements VolunteerRepository {

    private static VolunteerRepositoryImpl INSTANCE;
    private final VolunteerApi volunteerApi = RetrofitFactory.getInstance().getVolunteerApi();

    public static synchronized VolunteerRepositoryImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new VolunteerRepositoryImpl();
        }
        return INSTANCE;
    }

    private VolunteerRepositoryImpl() {
    }

    @Override
    public void getUnoccupiedVolunteers(@NonNull Consumer<Status<List<ItemVolunteerEntity>>> callback) {

    }

    @Override
    public void getVolunteer(@NonNull String id, @NonNull Consumer<Status<FullVolunteerEntity>> callback) {
        volunteerApi.getById(id).enqueue(new CallToConsumer<>(
                callback,
                volunteer -> {
                    final String resId = volunteer.id;
                    final String name = volunteer.name;
                    final String nickname = volunteer.nickname;
                    final String email = volunteer.email;
                    if (resId != null && name != null && nickname != null && email != null) {
                        return new FullVolunteerEntity(
                                resId,
                                name,
                                nickname,
                                email,
                                volunteer.phone,
                                volunteer.photoUrl
                        );
                    } else {
                        return null;
                    }
                }
        ));
    }

    @Override
    public void getActiveVolunteers(@NonNull Consumer<Status<List<ItemVolunteerEntity>>> callback) {

    }

    @Override
    public void update(
            @NonNull String id,
            @NonNull String name,
            @NonNull String nickname,
            @NonNull String email,
            @Nullable String phone,
            @Nullable String photoUrl,
            @NonNull Consumer<Status<FullVolunteerEntity>> callback) {
        volunteerApi.update(id, new Container(name, nickname, email, phone, photoUrl)).enqueue(new CallToConsumer<>(
                callback,
                volunteerDto -> {
                    final String newId = volunteerDto.id;
                    final String newName = volunteerDto.name;
                    final String newNickname = volunteerDto.nickname;
                    final String newEmail = volunteerDto.email;
                    if (newId != null && newName != null && newNickname != null && newEmail != null) {
                        return new FullVolunteerEntity(
                                newId,
                                newName,
                                newNickname,
                                newEmail,
                                volunteerDto.phone,
                                volunteerDto.photoUrl
                        );
                    } else {
                        return null;
                    }
                }
        ));
    }
}
