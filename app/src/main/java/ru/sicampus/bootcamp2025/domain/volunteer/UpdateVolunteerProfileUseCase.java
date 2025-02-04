package ru.sicampus.bootcamp2025.domain.volunteer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.domain.entities.FullVolunteerEntity;
import ru.sicampus.bootcamp2025.domain.entities.Status;

public class UpdateVolunteerProfileUseCase {

    public final VolunteerRepository repo;

    public UpdateVolunteerProfileUseCase(VolunteerRepository repo) {
        this.repo = repo;
    }

    public void execute(
            @NonNull String id,
            @NonNull String name,
            @NonNull String nickname,
            @NonNull String email,
            @Nullable String phone,
            @Nullable String photoUrl,
            @NonNull Consumer<Status<FullVolunteerEntity>> callback) {
        repo.update(id, name, nickname, email, phone, photoUrl, callback);
    }
}
