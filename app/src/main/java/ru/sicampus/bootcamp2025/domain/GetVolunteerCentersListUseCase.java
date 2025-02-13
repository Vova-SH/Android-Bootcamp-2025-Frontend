package ru.sicampus.bootcamp2025.domain;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.domain.entites.ItemUserEntity;
import ru.sicampus.bootcamp2025.domain.entites.ItemVolunteerCenterEntity;
import ru.sicampus.bootcamp2025.domain.entites.Status;

public class GetVolunteerCentersListUseCase {
    private final UserRepository repo;

    public GetVolunteerCentersListUseCase(UserRepository repo) {
        this.repo = repo;
    }

    public void execute(@NonNull Consumer<Status<List<ItemVolunteerCenterEntity>>> callback) {
        repo.getAllVolunteerCenters(callback);
    }
}
