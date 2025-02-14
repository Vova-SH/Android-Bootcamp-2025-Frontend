package ru.sicampus.bootcamp2025.domain;

import androidx.annotation.NonNull;

import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.domain.entites.FullUserEntity;
import ru.sicampus.bootcamp2025.domain.entites.FullVolunteerCenterEntity;
import ru.sicampus.bootcamp2025.domain.entites.Status;

public class GetVolunteerCenterByIdUseCase {
    private final UserRepository repo;

    public GetVolunteerCenterByIdUseCase(UserRepository repo) {
        this.repo = repo;
    }

    public void execute(@NonNull Integer id, @NonNull Consumer<Status<FullVolunteerCenterEntity>> callback) {
        repo.getVolunteerCenterById(id, callback);
    }
}
