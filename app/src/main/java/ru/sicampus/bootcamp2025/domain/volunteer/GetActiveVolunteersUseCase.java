package ru.sicampus.bootcamp2025.domain.volunteer;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.function.Consumer;

import ru.sicampus.bootcamp2025.domain.entities.ItemVolunteerEntity;
import ru.sicampus.bootcamp2025.domain.entities.Status;

public class GetActiveVolunteersUseCase {

    public final VolunteerRepository repo;


    public GetActiveVolunteersUseCase(VolunteerRepository repo) {
        this.repo = repo;
    }

    public void execute(@NonNull Consumer<Status<List<ItemVolunteerEntity>>> callback) {
        repo.getActiveVolunteers(callback);
    }
}
