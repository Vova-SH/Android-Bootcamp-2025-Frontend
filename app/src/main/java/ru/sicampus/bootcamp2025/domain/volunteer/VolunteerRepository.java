package ru.sicampus.bootcamp2025.domain.volunteer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.domain.entities.FullVolunteerEntity;
import ru.sicampus.bootcamp2025.domain.entities.ItemVolunteerEntity;
import ru.sicampus.bootcamp2025.domain.entities.Status;

public interface VolunteerRepository {

    void getUnoccupiedVolunteers(@NonNull Consumer<Status<List<ItemVolunteerEntity>>> callback);

    void getVolunteer(@NonNull String id, @NonNull Consumer<Status<FullVolunteerEntity>> callback);

    void getActiveVolunteers(@NonNull Consumer<Status<List<ItemVolunteerEntity>>> callback);

    void update(
            @NonNull String id,
            @NonNull String name,
            @NonNull String nickname,
            @NonNull String email,
            @Nullable String phone,
            @Nullable String photoUrl,
            @NonNull Consumer<Status<FullVolunteerEntity>> callback
    );
}
